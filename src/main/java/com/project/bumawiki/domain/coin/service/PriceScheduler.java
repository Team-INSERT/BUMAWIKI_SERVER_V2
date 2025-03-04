package com.project.bumawiki.domain.coin.service;

import static com.project.bumawiki.domain.coin.domain.type.TradeStatus.*;
import static com.project.bumawiki.global.util.RandomUtil.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.bumawiki.domain.coin.domain.CoinAccount;
import com.project.bumawiki.domain.coin.domain.Price;
import com.project.bumawiki.domain.coin.domain.Trade;
import com.project.bumawiki.domain.coin.domain.repository.CoinAccountRepository;
import com.project.bumawiki.domain.coin.domain.repository.PriceRepository;
import com.project.bumawiki.domain.coin.domain.repository.TradeRepository;
import com.project.bumawiki.domain.coin.domain.type.TradeStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PriceScheduler {
	private final PriceRepository priceRepository;
	private final TradeRepository tradeRepository;
	private final CoinAccountRepository coinAccountRepository;

	@Scheduled(fixedRate = 30000)
	void changePrice() {
		Long CHANGE_MONEY_RANGE = 140000L;

		Price recentPrice = priceRepository.getRecentPrice();

		List<Trade> trades = tradeRepository.findByCreatedAtGreaterThan(recentPrice.getStartedTime());
		trades = trades.stream()
			.filter(trade -> !List.of(DELISTING, NONE, CANCELLED).contains(trade.getTradeStatus()))
			.toList();

		Map<TradeStatus, List<Trade>> tradeMap = trades.stream().collect(
			java.util.stream.Collectors.groupingBy(
				trade -> switch (trade.getTradeStatus()) {
					case BOUGHT, BUYING -> BOUGHT;
					case SELLING, SOLD -> SOLD;
					default -> NONE;
				}
			)
		);

		double boughtRatio = 0.5;
		double soldRatio = 0.5;

		if (!trades.isEmpty()) {
			boughtRatio = tradeMap.getOrDefault(BOUGHT, List.of()).size() / (double)trades.size();
			soldRatio = tradeMap.getOrDefault(SOLD, List.of()).size() / (double)trades.size();
		}

		Long max = recentPrice.getPrice() + (CHANGE_MONEY_RANGE * (long)boughtRatio);
		Long min = recentPrice.getPrice() - (CHANGE_MONEY_RANGE * (long)soldRatio);

		SecureRandom random = getRandomInstance();
		long totalRandomPrice = 0L;
		int failcount = 0;

		for (int i = 0; i < 10; i++) {
			Long randomPrice = random.nextLong(max - min + 1L) + min;
			System.out.println("randomPrice = " + randomPrice);
			if (randomPrice < 0) {
				failcount++;
			}
			totalRandomPrice += randomPrice;
		}

		Long averageRandomPrice = totalRandomPrice / 10;
		System.out.println("averageRandomPrice = " + averageRandomPrice);
		Price newPrice;

		if (failcount > 3) {
			restartCoin();
			newPrice = new Price(350000L);
		} else {
			newPrice = new Price(averageRandomPrice);
		}
		System.out.println("newPrice = " + newPrice);
		System.out.println("\n\n");

		priceRepository.save(newPrice);
		processBuyingTrade(newPrice);
		processSellingTrade(newPrice);
	}

	private void restartCoin() {
		List<CoinAccount> coinAccounts = coinAccountRepository.findAllByCoinGreaterThan0();

		for (CoinAccount coinAccount : coinAccounts) {
			Trade trade = new Trade(
				0L,
				coinAccount.getCoin(),
				0L,
				TradeStatus.DELISTING,
				coinAccount.getId()
			);
			coinAccount.sellCoin(0L, coinAccount.getCoin());

			tradeRepository.save(trade);
			coinAccountRepository.save(coinAccount);
		}
	}

	private void processSellingTrade(Price newPrice) {
		List<Trade> sellingTrades = tradeRepository.findAllByTradeStatus(TradeStatus.SELLING);

		for (Trade sellingTrade : sellingTrades) {
			if (sellingTrade.getCoinPrice() <= newPrice.getPrice()) {
				CoinAccount tradingAccount = coinAccountRepository.getById(sellingTrade.getCoinAccountId());

				tradingAccount.sellCoin(sellingTrade.getCoinPrice(), sellingTrade.getCoinCount());
				sellingTrade.updateTradeStatus(TradeStatus.SOLD);
				tradeRepository.save(sellingTrade);
			}
		}
	}

	private void processBuyingTrade(Price newPrice) {
		List<Trade> buyingTrades = tradeRepository.findAllByTradeStatus(TradeStatus.BUYING);

		for (Trade buyingTrade : buyingTrades) {
			if (buyingTrade.getCoinPrice() >= newPrice.getPrice()) {
				CoinAccount tradingAccount = coinAccountRepository.getById(buyingTrade.getCoinAccountId());

				tradingAccount.buyCoin(buyingTrade.getCoinPrice(), buyingTrade.getCoinCount());
				buyingTrade.updateTradeStatus(BOUGHT);
				tradeRepository.save(buyingTrade);
			}
		}
	}

}
