
package com.globalRelay.userInfoValidation;

/**
 * @author hibab
 *
 */
public class User {	
	
	public String getFirstName() {
		return m_firstName;
	}

	public void setFirstName(String firstName) {
		this.m_firstName = firstName;
	}

	public String getLastName() {
		return m_lastName;
	}

	public void setLastName(String lastName) {
		this.m_lastName = lastName;
	}

	public String getCompanyName() {
		return m_companyName;
	}

	public void setCompanyName(String companyName) {
		this.m_companyName = companyName;
	}

	public String getAddress() {
		return m_address;
	}

	public void setAddress(String address) {
		this.m_address = address;
	}

	public String getCity() {
		return m_city;
	}

	public void setCity(String city) {
		this.m_city = city;
	}

	public String getProvince() {
		return m_province;
	}

	public void setProvince(String province) {
		this.m_province = province;
	}

	public String getPostalCode() {
		return m_postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.m_postalCode = postalCode;
	}

	public String getEmail() {
		return m_email;
	}

	public void setEmail(String email) {
		this.m_email = email;
	}

	public String getWebsite() {
		return m_website;
	}

	public void setWebsite(String website) {
		this.m_website = website;
	}
	
	private String m_firstName;
	private String m_lastName;
	private String m_companyName;
	private String m_address;
	private String m_city;
	private String m_province;
	private String m_postalCode;
	private String m_email;
	private String m_website;


}