/**
 * @author hibab
 */
package com.globalRelay.userInfoValidation;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * This class stores all the validation rules that must applied
 * on every record.
 * R1 - Field type must be a String.
 * R2 - Each field should be able to accept a maximum number of 256 characters.
 * R3 - first name, last name and email address are required.
 * R4 - company name, address, city, province, postal code, and website are optional.
 * 
 */
public class InputValidator implements DataValidator {
	private int m_maxLength;
	private int m_minLength;
	private boolean m_isRequired;
	private boolean m_isAsciiOnly;
	private boolean m_isAlphaOnly;
	
	InputValidator(
			int maxLength,
			int minLength,
			boolean isRequired, 
			boolean isAsciiOnly,
			boolean isAlphaOnly) {
		m_maxLength = maxLength;
		m_minLength = minLength;
		m_isRequired = isRequired;
		m_isAsciiOnly = isAsciiOnly;
		m_isAlphaOnly = isAlphaOnly;
	}
	
	public void validate(String str) throws ValidationException {
		if (null != str) {
			str = str.trim();
		}
		else { // We have a null string, throw for required.
			if (m_isRequired) {
				throw new ValidationException("Input is mandatory.");
			}
			return;
		}
		
		if (str.isEmpty()) {// We have an empty string, throw for required.
			if (m_isRequired) {
				throw new ValidationException("Input is mandatory.");
			}
			return;
		}
		else if (str.length() > m_maxLength) {
			throw new ValidationException("Input is too long.");
		}
		else if (str.length() < m_minLength) {
			throw new ValidationException("Input is too small.");
		}
		else if (m_isAsciiOnly) {
			CharsetEncoder asciiEncoder =
				      Charset.forName("US-ASCII").newEncoder();
			if (!asciiEncoder.canEncode(str)) {
				throw new ValidationException("Input has non-ascii characters.");
			}
		}
		else if (m_isAlphaOnly && !str.matches("[a-zA-z\\s]+")) {
			throw new ValidationException("Input can only contain letters.");
		}
	}
}
