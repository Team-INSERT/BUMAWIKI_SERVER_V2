package com.project.bumawiki.domain.coin.service;

import static com.project.bumawiki.global.util.RandomUtil.*;

import java.security.SecureRandom;
import java.util.List;

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

	@Scheduled(fixedRate = 100000)
	void changePrice() {
		int CHANGE_MONEY_RANGE = 200000;

		Price recentPrice = priceRepository.getRecentPrice();
		int max = recentPrice.getPrice() + CHANGE_MONEY_RANGE;
		int min = Math.max(recentPrice.getPrice() - CHANGE_MONEY_RANGE, 0);

		SecureRandom random = getRandomInstance();
		int randomPrice = random.nextInt(max - min + 1) + min;
		Price newPrice = new Price(randomPrice - randomPrice % 100);

		priceRepository.save(newPrice);

		processBuyingTrade(newPrice);
		processSellingTrade(newPrice);
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
				buyingTrade.updateTradeStatus(TradeStatus.BOUGHT);
				tradeRepository.save(buyingTrade);
			}
		}
	}

}
