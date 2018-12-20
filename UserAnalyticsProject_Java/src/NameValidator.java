/**
 * 
 */
package com.globalRelay.userInfoValidation;

/**
 * @author hibab
 *
 */
public class NameValidator extends InputValidator {

	NameValidator() {
		super(256, 1, true, true, true);
	}

	@Override
	public void validate(String str) throws ValidationException {
		super.validate(str);
	}
}
