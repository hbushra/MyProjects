/**
 * 
 */
package com.globalRelay.userInfoValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hibab
 *
 */
public class PostcodeValidator extends InputValidator {

	private static final String s_REGEX = 
		"^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
	 
	private static final Pattern s_PATTERN = Pattern.compile(s_REGEX);
	
	PostcodeValidator() {
		super(7, 7, false, true, false);
	}
	
	@Override
	public void validate(String str) throws ValidationException {
		// TODO Auto-generated method stub
		super.validate(str);
		
		 Matcher matcher = s_PATTERN.matcher(str);

		 if (!matcher.matches()) {
			 throw new ValidationException("Postcode should be in A1A 1A1 format.");
		 }
	}
}
