package com.schoolofnet.JavaMongoDB;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connection {
	
	public MongoDatabase getConnection() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("java_mongo");
		
		return database;
	}
	
	public void example() {
		
/*		Credential Connection 
 * 
		String userName = "";
		String database = "";
		char[] password = null;
		
		MongoCredential credential = MongoCredential.createCredential(userName, database, password);
		MongoCliente mongo = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential)))
*/
		
/*		Local connection		
		MongoClient mongo = new MongoClient("localhost", 27017);
		
		DB database = (DB) mongo.getDatabase("java_mongodb");
*/
		
		/* URI Connection 
		MongoClientURI uri = new MongoClientURI("mongodb://user:pass@localhost/?authSource=db2&ssl=true");
		MongoClient mongo = new MongoClient(uri);
		*/
		
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("java_mongo");
		
		database.createCollection("people");
		MongoCollection<Document> collection = database.getCollection("people");
		
		for (String name : database.listCollectionNames()) {
			System.out.println(name);
		}
		
		collection.drop();
	
		
		
	}
}
