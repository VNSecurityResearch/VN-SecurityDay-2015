package com.hptsec.vulnlab.Utilities;

import java.util.Random;

public class MyRandomStringGenerator {

	private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";

	public static String getRandomString(int sizeOfRandomString) {
		final Random random = new Random();
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sizeOfRandomString; ++i)
			sb.append(ALLOWED_CHARACTERS.charAt(random
					.nextInt(ALLOWED_CHARACTERS.length())));
		return sb.toString();
	}

}
