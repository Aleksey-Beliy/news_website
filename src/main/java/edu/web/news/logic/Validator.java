package edu.web.news.logic;

import java.util.regex.Pattern;

public class Validator {

	public static boolean isUsernameValid(String username) {
		return username != null && username.length() >= 2 && username.length() <= 20;
	}

	public static boolean isEmailValid(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return email != null && Pattern.matches(emailRegex, email);
	}

	public static boolean isPasswordValid(String password) {
		return password != null && password.length() >= 6;
	}

	public static boolean doPasswordsMatch(String password, String confirmPassword) {
		return password != null && password.equals(confirmPassword);
	}
}
