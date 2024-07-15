package edu.web.news.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class TokenGenerator {
	private static SecureRandom random = new SecureRandom();

	public static String generateToken(int length) {
		return new BigInteger(130, random).toString(32).substring(0, length);
	}

	
}
