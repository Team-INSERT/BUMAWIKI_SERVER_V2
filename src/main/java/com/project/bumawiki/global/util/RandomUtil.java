package com.project.bumawiki.global.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.project.bumawiki.domain.coin.exception.RandomInstanceException;

public class RandomUtil {
	public static SecureRandom getRandomInstance() {
		SecureRandom random;
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			throw new RandomInstanceException();
		}
		return random;
	}
}
