package com.globalRelay.userInfoValidation;

/**
 * @author hibab
 * Interface for validating user input.
 */
public interface DataValidator {
	void validate(String str) throws ValidationException;
}
