/*
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
*/
package uk.ac.prospects.w4.webapp.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Validates dates as input by the user on the code generator page.
 */
public class DateValidator {

	private static final String FULL_DATE_FORMAT = "yyyy-MM-dd";
	private static final String YEAR_MONTH_DATE_FORMAT ="yyyy-MM";
	private static final String YEAR_DATE_FORMAT = "yyyy";


	/** Validates dates as input by the user.
	 * Accepts an empty value; a full date with year, month and day; a year and month; or just a year
	 * @param startDate the date
	 * @return whether the date value is valid
	 */
	public static boolean validate(String startDate){
		if(startDate== null || startDate.trim().isEmpty()){
			return true;
		}
		if(validateDateFormat(startDate, FULL_DATE_FORMAT)){
			return true;
		}
		if(validateDateFormat(startDate, YEAR_MONTH_DATE_FORMAT)){
			return true;
		}
		if(validateDateFormat(startDate, YEAR_DATE_FORMAT)){
			return true;
		}

		return false;

	}

	/**
	 * Validates the date string format according to the {@code datePattern} provided
	 * @param date {@code String} - date passed
	 * @param datePattern {@code datePattern} - date pattern expected
	 * @return {@code true} if date falls into the {@code datePattern} provided,
	 * otherwise {@code false}
	 */
	private static boolean validateDateFormat(String date, String datePattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		try {
			if (date.trim().length() != dateFormat.toPattern().length()) return false;
			dateFormat.setLenient(false);
			dateFormat.parse(date.trim());
		} catch (ParseException e) {
			return false;
		}
		return true;
	}


}
