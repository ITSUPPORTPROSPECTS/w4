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
