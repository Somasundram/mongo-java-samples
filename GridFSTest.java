ppackage com.somlab.gridfs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class GridFSTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		FileInputStream inputStream = null;
		
		GridFS videos = new GridFS(db, "videos2"); // returns GridFS bucket names "videos"
		
		try {
			
			inputStream = new FileInputStream("client_story_video2.mp4");
		} catch( FileNotFoundException e ) {
			System.out.println("Can't open the file!");
			e.printStackTrace();
			System.exit(1);
		}
		
		GridFSInputFile video = videos.createFile(inputStream, "client_story2.mp4");
		
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
		
		/* Reading it back */
		
		GridFSDBFile gridFile = videos.findOne(new BasicDBObject("filename", "client_story2.mp4"));
		
		FileOutputStream outputStream = new FileOutputStream("client_story2_copy_from_DB.mp4");
		gridFile.writeTo(outputStream);		

	}

}
