package com.somlab.gridfs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class GridFSTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		FileInputStream inputStream = null;
		
		GridFS videos = new GridFS(db, "videos"); // returns GridFS bucket names "videos"
		
		try {
			
			inputStream = new FileInputStream("C:\\Users\\sbalak16\\Desktop\\DATA\\Career\\Technical\\MongoDB\\MongoDB-University-Course\\CODE\\M101J\\src\\main\\resources\\client_story_video.mp4");
		} catch( FileNotFoundException e ) {
			System.out.println("Can't open the file!");
			e.printStackTrace();
			System.exit(1);
		}
		
		GridFSInputFile video = videos.createFile(inputStream, "client_story.mp4");
		
		// create some meta-data for the video
		BasicDBObject meta = new BasicDBObject("description", "Ameriprise Client Story");
		ArrayList<String> tags = new ArrayList<String> ();
		tags.add("Marketting");
		tags.add("client");
		tags.add("story");
		
		meta.append("tags", tags);
		
		video.setMetaData( meta );
		video.save();
		
		System.out.println("Object ID in Files Collection: "+ video.get("_id") );

	}

}
