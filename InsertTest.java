package com.somlab.crud;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("insertTest");
		
		DBObject doc = new BasicDBObject("_id", new ObjectId() ).append("x", 0);
		DBObject doc1 = new BasicDBObject().append("x", 1);
		DBObject doc2 = new BasicDBObject().append("x", 2);
		
		System.out.println(doc);	// print before insert	
		collection.insert(doc);
		System.out.println(doc); // print after insert
		//doc.removeField("_id");
		System.out.println(doc); // print after insert
		collection.insert(doc);
		collection.insert( Arrays.asList(doc1, doc2) ); 
	
	}

}
