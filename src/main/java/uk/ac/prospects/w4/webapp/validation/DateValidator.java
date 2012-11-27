package uk.ac.prospects.w4.webapp.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author malvinel
 */
public class DateValidator {

	private static final String FULL_DATE_FORMAT = "yyyy-MM-dd";
	private static final String YEAR_MONTH_DATE_FORMAT ="yyyy-MM";
	private static final String YEAR_DATE_FORMAT = "yyyy";


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
