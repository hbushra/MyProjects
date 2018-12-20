/**
 * 
 */
package com.globalRelay.userInfoValidation;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author hibab
 *
 */
public class EmailValidator extends InputValidator {
	private String s_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
			+ "A-Z]{2,7}$";

	private Pattern s_PATTERN = Pattern.compile(s_REGEX);

	private Set<String> m_emails = new HashSet<String>();
	
	EmailValidator() {
		super(256,  5, true, true, false);
	}

	@Override
	public void validate(String str) throws ValidationException {
		super.validate(str);
		
		Matcher matcher = s_PATTERN.matcher(str);

		if (!matcher.matches()) {
			throw new ValidationException("Invalid format for email.");
		}
		
		if (m_emails.contains(str)) {
			throw new ValidationException("Duplicate email.");
		}
		
		m_emails.add(str);
	}
}
