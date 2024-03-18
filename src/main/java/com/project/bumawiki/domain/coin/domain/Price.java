package com.project.bumawiki.domain.coin.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int price;

	private LocalDateTime startedTime;

	public Price(int price) {
		this.price = price;
		startedTime = LocalDateTime.now();
	}
}
