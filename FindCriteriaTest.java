package com.somlab.crud;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class FindCriteriaTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("findTest");
		collection.drop();
		
		// insert 10 docs with a random integer as the value of field 'x'
		for(int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("x", new Random().nextInt(2))
				.append("y", new Random().nextInt(100)));
		}
		
		QueryBuilder builder = QueryBuilder.start("x").is(0)
				.and("y").greaterThan(10).lessThan(70);
		
		// Alternate to QueryBuilder - using basic DBObject constructs
		
		DBObject query = new BasicDBObject("x", 0)
			.append("y", new BasicDBObject("$gt", 10).append("$lt", 90));
		
		
		System.out.println("Query Builder Count:"+collection.count(builder.get()));
		//System.out.println("Query Count:"+collection.count(query));
		
		System.out.println("\nFind all:");
		DBCursor cursor = collection.find(builder.get());
		try {
			
			while(cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
			
		} finally {
			cursor.close();
		}
	}
}
