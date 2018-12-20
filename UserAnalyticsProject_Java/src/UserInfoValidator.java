package com.globalRelay.userInfoValidation;

/**
 * @author hibab
 *
 */
public class UserInfoValidator {

	/**
	 * Validator for company name and address.
	 * 
	 * Allows for non ASCII and alpha numeric values.
	 * 
	 * isRequired is false.
	 * isAsciiOnly is false.
	 * isAlphaOnly is false.
	 */
	private static InputValidator s_ALPHANUMERIC_NONASCII_VALIDATOR = new
			InputValidator(256, 1, false, false, false);

	/**
	 * Validator for city and province.
	 * 
	 * Allows for non ASCII and alpha only values.
	 * 
	 * isRequired is false.
	 * isAsciiOnly is false.
	 * isAlphaOnly is false.
	 */
	private static InputValidator s_ALPHAONLY_NONASCII_VALIDATOR = new
			InputValidator(256, 1, false, false, true);
	
	/**
	 * Validator for email.
	 * @see EmailValidator.
	 * 
	 */
	private static InputValidator s_EMAIL_VALIDATOR = new
			EmailValidator();
	
	/**
	 * Validator for firstname and last name.
	 * @see NameValidator.
	 * 
	 */
	private static InputValidator s_NAME_VALIDATOR = new
			NameValidator();
	
	/**
	 * Validator for post code.
	 * @see PostcodeValidator.
	 * 
	 */
	private static PostcodeValidator s_POSTCODE_VALIDATOR = new
			PostcodeValidator();
	
	/**
	 * Validator for website.
	 * 
	 * Allows for ASCII only and alpha numeric values.
	 * 
	 * isRequired is false.
	 * isAsciiOnly is false.
	 * isAlphaOnly is false.
	 */
	private static InputValidator s_WEBSITE_VALIDATOR = new
			InputValidator(256, 1, false, false, false);
	
	/**
	 * Validates address input.
	 * 
	 * @param str input string of address.
	 */
	public static void validateAddress(String str) throws ValidationException {
		s_ALPHANUMERIC_NONASCII_VALIDATOR.validate(str);
	}
	
	/**
	 * Validates company name input.
	 * 
	 * @param str input string of company name .
	 */
	public static void validateCompany(String str) throws ValidationException {
		s_ALPHANUMERIC_NONASCII_VALIDATOR.validate(str);
	}
	
	/**
	 * Validates city input.
	 * 
	 * @param str input string of city.
	 */
	public static void validateCity(String str) throws ValidationException {
		s_ALPHANUMERIC_NONASCII_VALIDATOR.validate(str);
	}
	
	/**
	 * Validates email input.
	 * 
	 * @param str input string of email.
	 */
	public static void validateEmail(String str) throws ValidationException {
		s_EMAIL_VALIDATOR.validate(str);
	}
	
	/**
	 * Validates lastname nad firstname input.
	 * 
	 * @param str input string of name.
	 */
	public static void validateName(String str) throws ValidationException {
		s_NAME_VALIDATOR.validate(str);
	}
	
	/**
	 * Validates postcode input.
	 * 
	 * @param str input string of postcode.
	 */
	public static void validatePostalcode(String str) throws ValidationException {
		s_POSTCODE_VALIDATOR.validate(str);
	}
	
	/**
	 * Validates province input.
	 * 
	 * @param str input string of province.
	 */
	public static void validateProvince(String str) throws ValidationException {
		s_ALPHAONLY_NONASCII_VALIDATOR.validate(str);
	}
	
	/**
	 * Validates website input.
	 * 
	 * @param str input string of website.
	 */
	public static void validateWebsite(String str) throws ValidationException {
		s_WEBSITE_VALIDATOR.validate(str);
	}
}
