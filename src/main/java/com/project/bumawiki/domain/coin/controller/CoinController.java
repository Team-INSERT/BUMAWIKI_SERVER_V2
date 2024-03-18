package com.project.bumawiki.domain.coin.controller;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.bumawiki.domain.coin.controller.dto.CoinAccountResponse;
import com.project.bumawiki.domain.coin.controller.dto.PriceResponse;
import com.project.bumawiki.domain.coin.controller.dto.TradeRequest;
import com.project.bumawiki.domain.coin.controller.dto.TradeResponse;
import com.project.bumawiki.domain.coin.service.CoinService;
import com.project.bumawiki.global.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coins")
public class CoinController {
	private final CoinService coinService;

	@PostMapping
	public CoinAccountResponse createCoinAccount() {
		return CoinAccountResponse.from(
				coinService.createCoinAccount(SecurityUtil.getCurrentUserWithLogin())
		);
	}

	@GetMapping("/mine")
	public CoinAccountResponse findOwnAccount() {
		return CoinAccountResponse.from(
			coinService.findByUser(SecurityUtil.getCurrentUserWithLogin())
		);
	}

	@PostMapping("/buy")
	public TradeResponse buyCoin(@RequestBody TradeRequest tradeRequest) {
		return TradeResponse.from(
			coinService.buyCoin(tradeRequest.toEntity(), SecurityUtil.getCurrentUserWithLogin())
		);
	}

	@PostMapping("/sell")
	public TradeResponse sellCoin(@RequestBody TradeRequest tradeRequest) {
		return TradeResponse.from(
			coinService.sellCoin(tradeRequest.toEntity(), SecurityUtil.getCurrentUserWithLogin())
		);
	}

	@GetMapping("/trades/{accountId}")
	public List<TradeResponse> getTrades(@PathVariable Long accountId) {
		return coinService.getTrades(accountId)
			.stream()
			.map(TradeResponse::from)
			.toList();
	}

	@GetMapping("/graph")
	public List<PriceResponse> getGraph() {
		return coinService.getAllPrices()
			.stream()
			.map(PriceResponse::from)
			.toList();
	}

	@DeleteMapping("/{tradeId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void cancelTrade(@PathVariable Long tradeId) {
		coinService.cancelTrade(tradeId, SecurityUtil.getCurrentUserWithLogin());
	}

	@PostMapping("/daily")
	public Long dailyCheck() {
		return coinService.dailyCheck(SecurityUtil.getCurrentUserWithLogin());
	}
}
