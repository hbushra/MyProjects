/*
 * 
 */
package com.globalRelay.userInfoValidation;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author hibab
 *
 */
public class UserStoryOneMain {

	private static Map<String, Method> m_fields = new LinkedHashMap<String, Method>();
	
	static {
		try {
			m_fields.put("First name", UserInfoValidator.class.getMethod("validateName", String.class));
			m_fields.put("Last name", UserInfoValidator.class.getMethod("validateName", String.class));
			m_fields.put("Company name", UserInfoValidator.class.getMethod("validateCompany", String.class));
			m_fields.put("Address", UserInfoValidator.class.getMethod("validateAddress", String.class));
			m_fields.put("City", UserInfoValidator.class.getMethod("validateCity", String.class));
			m_fields.put("Province", UserInfoValidator.class.getMethod("validateProvince", String.class));
			m_fields.put("Postal code", UserInfoValidator.class.getMethod("validatePostalcode", String.class));
			m_fields.put("Email address", UserInfoValidator.class.getMethod("validateEmail", String.class));
			m_fields.put("Web site", UserInfoValidator.class.getMethod("validateWebsite", String.class));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean stopUSerEntry = false;
		
		for (Map.Entry<String, Method> oneFiled : m_fields.entrySet()) {
			boolean keepRunning = false;
			do
			{
				try {
					System.out.println("Enter " + oneFiled.getKey() + ": ");
					String input = sc.nextLine();
					
					try {
						if (-1 == Integer.parseInt(input)) {
							stopUSerEntry = true;
							break;
						}
					} catch(NumberFormatException ne) {
						
					}
					
					oneFiled.getValue().invoke(null, input);
					keepRunning = false;
				} catch(Exception e) {
					ValidationException ve = (ValidationException) e.getCause();
					System.out.println(ve.getMessage());
					keepRunning = true;
				}
			} while (keepRunning);
			
			if (stopUSerEntry) {
				break;
			}
		}
		sc.close();
	}
}
