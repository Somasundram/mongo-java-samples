package com.somlab;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreemarkerStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException  {

		final Configuration  configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreemarkerStyle.class, "/");
		
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		
		DB database = client.getDB("test");
		final DBCollection collection = database.getCollection("things");
		
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request request, Response response) {
				
				StringWriter writer = new StringWriter();

				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					DBObject document = collection.findOne();
					
					//Map<String, Object> helloMap = new HashMap<String, Object>();
					//helloMap.put("name", "Som Freemarker");
					
					helloTemplate.process(document, writer);
					
					System.out.println(writer);
					
				} catch (Exception e) {
					halt(500);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
				return writer;
			}
		});
		

	}

}


