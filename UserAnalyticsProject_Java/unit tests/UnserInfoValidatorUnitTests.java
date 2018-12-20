/**
 * 
 */
package com.globalRelay.userInfoValidation;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author hibab
 *
 */
public class UnserInfoValidatorUnitTests {

    @Rule
    public ExpectedException exception  = ExpectedException.none();
    
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	 public void NullName() {
	    try {
	    	UserInfoValidator.validateName(null);
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Input is mandatory."));
	    }    
	}
	
	@Test
	 public void EmptyName() {
	    try {
	    	UserInfoValidator.validateName("");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Input is mandatory."));
	    }    
	}
	
	@Test
	 public void NumericName() {
	    try {
	    	UserInfoValidator.validateName("alpha1");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Input can only contain letters."));
	    }    
	}
	
	@Test
	 public void HyphenName() {
	    try {
	    	UserInfoValidator.validateName("Sarah-Jane");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Input can only contain letters."));
	    }    
	}
	
	@Test
	 public void AsciiName() {
	    try {
	    	UserInfoValidator.validateName("Müller");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Input has non-ascii characters."));
	    }
	}
	
	@Test
	 public void LongName() {
	    try {
	    	UserInfoValidator.validateName("gVqXvLngUjMgShgGqqOdZahhGOPcsnTGzKCkATTkFlCFEdHjSsqSWXwBHGCbgzSaCMUMFucEIaNtOSgAeZrGdkAWkaVQCcpIiECYuyxWLBvraRNujSBkkxHKboeSpnPmwInDIabOPpTPSkyMIkQDDmFTYHzRanaoKBkwmoNWuNrimyJcUSQekZcklfPamKeaceVPiJVXZPzuRbVqJFUIlagOpOIiKwHaJmXWMINpwmrhvaMEqhGvHISVQNnCjvnjdJVLGFigjElBfcqowWCDqfJHLipNRDJmaaHRHcdiLMiZbbhxwBqIgKXhbIsKvQeOjaeThtJIRbXzvifVseNWhZyGUfHEVEuPnnEItTjNMSvZMzqcUYIIDDnQmBXtGVwgXDxWRCDVMEyAKLoJ");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Input is too long."));
	    }
	}
	
	@Test
	 public void OneCharacterName() {
	    try {
	    	UserInfoValidator.validateName("a");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void InvalidPostCodeNoSpace() {
	    try {
	    	UserInfoValidator.validatePostalcode("A1A1A1");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Input is too small."));
	    }
	}
	
	@Test
	 public void InvalidPostCodeNoNumeric() {
	    try {
	    	UserInfoValidator.validatePostalcode("AAA AAA");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Postcode should be in A1A 1A1 format."));
	    }
	}
	
	@Test
	 public void InvalidPostCodeNoAlpha() {
	    try {
	    	UserInfoValidator.validatePostalcode("111 111");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Postcode should be in A1A 1A1 format."));
	    }
	}
	
	@Test
	 public void InvalidPostCode() {
	    try {
	    	UserInfoValidator.validatePostalcode("11a a11");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Postcode should be in A1A 1A1 format."));
	    }
	}
	
	@Test
	 public void ValidPostCode() {
	    try {
	    	UserInfoValidator.validatePostalcode("S7N 2R6");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void ValidEmail() {
	    try {
	    	UserInfoValidator.validateEmail("a@b.com");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void InvalidEmail() {
	    try {
	    	UserInfoValidator.validateEmail("a@b.c");
	    } catch (Exception e) {
	    	assertThat(e, instanceOf(ValidationException.class));
	    	assertThat(e.getMessage(), equalTo("Invalid format for email."));
	    }
	}
	
	@Test
	 public void ValidEmailAlphaNumeric() {
	    try {
	    	UserInfoValidator.validateEmail("a1b2@domain1.ca");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void ValidCompany() {
	    try {
	    	UserInfoValidator.validateCompany("A & H Sptc Systems & Matl Inc");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void ValidCompanyEmpty() {
	    try {
	    	UserInfoValidator.validateCompany("");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void ValidCityEmpty() {
	    try {
	    	UserInfoValidator.validateCity("");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void ValidAddressEmpty() {
	    try {
	    	UserInfoValidator.validateAddress("");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void ValidProvinceEmpty() {
	    try {
	    	UserInfoValidator.validateProvince("");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
	
	@Test
	 public void ValidPWebEmpty() {
	    try {
	    	UserInfoValidator.validateWebsite("");
	    } catch (Exception e) {
	    	fail("Should pass.");
	    }
	}
}
