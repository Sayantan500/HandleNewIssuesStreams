package helpdesk_ticketing_system.handle_new_issues_streams;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

public class MongoDb {
    private final MongoCollection<Document> mongoCollection;
    private final ObjectMapper objectMapper;

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
        objectMapper = new ObjectMapper();
    }
    boolean addData(Issue issue, Context context)
    {
        InsertOneResult result;
        try {
            result = mongoCollection.insertOne(Document.parse(objectMapper.writeValueAsString(issue)));
        } catch (JsonProcessingException e) {
            context.getLogger().log(
                    "Exception Class : " + e.getClass().getName() + "\tMessage : " + e.getMessage() + "\n"
            );
            throw new RuntimeException(e);
        }
        return result.wasAcknowledged();
    }
}
