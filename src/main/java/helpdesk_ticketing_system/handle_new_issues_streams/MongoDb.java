package helpdesk_ticketing_system.handle_new_issues_streams;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

public class MongoDb {
    private final MongoCollection<Document> mongoCollection;
    private final Gson gson;

    public MongoDb() {
        String connectionUri = System.getenv("mongodb_connection_uri");
        String username = System.getenv("mongodb_username");
        String password = System.getenv("mongodb_password");
        String database = System.getenv("mongodb_database");
        String collection = System.getenv("mongodb_collection");

        String connectionString = String.format(connectionUri, username, password);
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build()
        );

        mongoCollection = mongoClient.getDatabase(database).getCollection(collection);
        gson = new Gson();
    }
    boolean addData(Issue issue)
    {
        InsertOneResult result = mongoCollection.insertOne(Document.parse(gson.toJson(issue)));
        return result.wasAcknowledged();
    }
}
