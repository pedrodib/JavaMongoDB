package com.schoolofnet.JavaMongoDB;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;

import static com.mongodb.client.model.Filters.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MongoDatabase connection = new Connection().getConnection();
        
        // connection.createCollection("people");
        
        final MongoCollection<Document> peopleCollection = connection.getCollection("people");
        
        
        /*Person pedro = new Person("Pedro", 21);
        createNewDoc(peopleCollection, pedro);*/
        
        Block<Document> showBlock = new Block<Document>() {

			public void apply(final Document doc) {
				Integer age = doc.getInteger("age");
				
				if(25 <= age) {
					peopleCollection.updateOne(
							eq("name", doc.getString("name")), 
							new Document("$set", new Document("age", 29)));
					System.out.println("É a Pessoa Erick");
				}else {
					System.out.println(doc.toJson());
				}
			}
		};
		
		/*findBy("name", "Pedro Dib", peopleCollection).forEach(showBlock);*/
		/*findBy("age", 21, peopleCollection).forEach(showBlock);*/
		/*peopleCollection.deleteOne(eq("name", "Wesley"));*/
		/*peopleCollection.findOneAndUpdate(filter, update);
		peopleCollection.findOneAndReplace(filter, replacement);*/
		
		findAll(peopleCollection).forEach(showBlock);
    }
    
    public static void createNewDoc(MongoCollection collection, Person person) {
    	Document document = new Document();
        document.append("name", person.getName());
        document.append("age", person.getAge());
        
        collection.insertOne(document);
    }
    
    public static FindIterable<Document> findAll(MongoCollection<Document> peopleCollection) {
    	return peopleCollection.find();
    }
    
    public static FindIterable<Document> findBy(String key, Object value, MongoCollection<Document> peopleCollection) {
    	return peopleCollection.find(eq(key, value));
    }
}
