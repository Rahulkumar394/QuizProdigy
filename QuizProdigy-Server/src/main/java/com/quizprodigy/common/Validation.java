package com.quizprodigy.common;

import java.util.regex.Pattern;

public class Validation {

    // validate the Email
	public static boolean emailValidation(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		java.util.regex.Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	// validate contact Number
	public static boolean contactNumberValidation(String contact_number) {
		String phoneRegex = "[0-9]{10}";
		Pattern p = Pattern.compile(phoneRegex);
		java.util.regex.Matcher matcher2 = p.matcher(contact_number);

		if (matcher2.matches()) {
			return true;
		} else {
			return false;
		}
	}

	// validate first name
	public static boolean nameValidation(String first_name) {

		if (first_name.length() > 2) {
			return true;
		} else {
			return false;
		}
	}


	// validate password
	public static boolean passwordValidation(String password) {

		// String passwordregex = "^(?=.*[!@#$%^&*()-+=])(?=\\S+$).{6,}$";
		// Pattern pattern = Pattern.compile(passwordregex);
		// Matcher matcher3 = pattern.matcher(password);

		if (password.length() >= 6 && password.length() <= 16) {
			return true;
		} else {
			return false;
		}

	}
}
