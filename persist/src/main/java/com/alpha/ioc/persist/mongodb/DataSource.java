package com.alpha.ioc.persist.mongodb;

import com.alpha.ioc.di.annotation.Component;
import com.alpha.ioc.di.annotation.Profile;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
@Component
@Profile("mongodb")
public class DataSource {
    private String databaseName;

    public DataSource(String databaseName) {
        this.databaseName = databaseName;
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new BookCodec()),
                MongoClient.getDefaultCodecRegistry());
        MongoCollection<Document> collection = database.getCollection(collectionName).withCodecRegistry(codecRegistry);
        return collection;
    }

}
