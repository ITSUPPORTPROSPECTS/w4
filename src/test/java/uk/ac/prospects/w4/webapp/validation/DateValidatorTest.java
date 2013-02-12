/*
Copyright (c) Graduate Prospects 2012

This program is free software: you can redistribute it and/or modify
it under the terms of the BSD 3-Clause License as published by
the Free Software Foundation.

You should have received a copy of the BSD 3-Clause License 
along with this program.  If not, see http://opensource.org/licenses/BSD-3-Clause
*/
package uk.ac.prospects.w4.webapp.validation;

import org.junit.Assert;
import org.junit.Test;

/**
 * Date Validator Test
 * @author malvinel
 */
public class DateValidatorTest {

	/***
	 * Tests {@link DateValidator#validate(String)}
	 */
	@Test
	public void testValidate(){
		Assert.assertTrue(DateValidator.validate("2013"));
		Assert.assertTrue(DateValidator.validate("2013-03"));
		Assert.assertTrue(DateValidator.validate("2013-11-27"));
		Assert.assertTrue(DateValidator.validate(null));
		Assert.assertTrue(DateValidator.validate(""));
		Assert.assertTrue(DateValidator.validate("  "));

		Assert.assertFalse(DateValidator.validate("20011"));
		Assert.assertFalse(DateValidator.validate("2013--asdasd"));
		Assert.assertFalse(DateValidator.validate("2013-11-67"));
		Assert.assertFalse(DateValidator.validate("-sdf"));

	}
}
