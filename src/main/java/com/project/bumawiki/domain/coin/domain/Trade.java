package com.project.bumawiki.domain.coin.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.project.bumawiki.domain.coin.domain.type.TradeStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long coinPrice;
	private Long coinCount;
	private Long usedMoney;
	@Enumerated(EnumType.STRING)
	private TradeStatus tradeStatus;
	private Long coinAccountId;
	private LocalDateTime tradedTime;

	public Trade(Long coinPrice, Long coinCount, Long usedMoney, TradeStatus tradeStatus, Long coinAccountId) {
		this.coinPrice = coinPrice;
		this.coinCount = coinCount;
		this.usedMoney = usedMoney;
		this.tradeStatus = tradeStatus;
		this.coinAccountId = coinAccountId;
		this.tradedTime = LocalDateTime.now();
	}

	public void updateTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
}
