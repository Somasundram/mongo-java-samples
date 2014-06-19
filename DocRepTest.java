package com.somlab.crud;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocRepTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", "som");
		doc.put("birthday", new Date(123456789));
		doc.put("programmer", true);
		doc.put("age", 8);
		doc.put("languages", Arrays.asList("Java", "C", "C++", "JavaScript"));
		doc.put("address", new BasicDBObject("street", "7076 Beverly Hills")
					.append("city", "Los Angeles")
					.append("state", "California")
					.append("zip", "12345"));
		
		System.out.println(doc);

	}

}
