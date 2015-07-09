package madtest.common.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by qct on 2015/6/11.
 */
public class MongoTest {
    public static void main(String[] args) {
        MongoClient client = MongoManager.getClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("test");
        collection.insertOne(new Document("sweeperTest", "hi"));

        FindIterable<Document> documents = collection.find();
    }
}
