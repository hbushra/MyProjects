package com.globalRelay.userInfoValidation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class UserStoryTwoMain {

	private static Map<String, Method> m_fields = new LinkedHashMap<String, Method>();
	static {
		try {
			m_fields.put("first_name", UserInfoValidator.class.getMethod("validateName", String.class));
			m_fields.put("first_name", UserInfoValidator.class.getMethod("validateName", String.class));
			m_fields.put("company_name", UserInfoValidator.class.getMethod("validateCompany", String.class));
			m_fields.put("address", UserInfoValidator.class.getMethod("validateAddress", String.class));
			m_fields.put("city", UserInfoValidator.class.getMethod("validateCity", String.class));
			m_fields.put("province", UserInfoValidator.class.getMethod("validateProvince", String.class));
			m_fields.put("postal", UserInfoValidator.class.getMethod("validatePostalcode", String.class));
			m_fields.put("email", UserInfoValidator.class.getMethod("validateEmail", String.class));
			m_fields.put("web", UserInfoValidator.class.getMethod("validateWebsite", String.class));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		FileReader fr;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e1) {
			System.err.println("csv file can't be found");
			System.exit(-1);
		}
		
		try {
			Iterable<CSVRecord> records = 
					CSVFormat.RFC4180.withFirstRecordAsHeader().parse(br);
			
			int goodCount = 0;

			
			 for (CSVRecord record : records) {
				 
				boolean badRecord = false;
				
				 for (Map.Entry<String, Method> field : m_fields.entrySet()) {
					 String str = record.get(field.getKey());
					 
					 try { 
						 field.getValue().invoke(null, str);
						 badRecord = false;
					 } catch(Exception e) {
						ValidationException ve = (ValidationException) e.getCause();
						System.out.println(str);
						System.out.println(ve.getMessage());
						badRecord = true;
					}
					 
					if (badRecord) {
						break;
					}
				 }
				 
				 if (!badRecord) {
					 ++goodCount;
				 }
			 }
			 
			 System.out.println("Good count : " + goodCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
