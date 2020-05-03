package com.db.tradefinance;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBTest {

    public static void main( String[] args )
    {

        MongoClientURI uri = new MongoClientURI(
                "mongodb://trade:tradefinance@cluster0-shard-00-00-wh8kw.mongodb.net:27017,cluster0-shard-00-01-wh8kw.mongodb.net:27017,cluster0-shard-00-02-wh8kw.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");
        System.out.println(database.getName());
        mongoClient.close();
        // Nitin Bedi



    }
}
