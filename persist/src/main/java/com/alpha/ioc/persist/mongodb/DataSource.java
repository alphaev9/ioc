package com.alpha.ioc.persist.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Component;

@Component
public class DataSource {
    public MongoCollection<Document> getCollection(String collectionName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ioc");
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new BookCodec()),
                MongoClient.getDefaultCodecRegistry());
        MongoCollection<Document> collection = database.getCollection(collectionName).withCodecRegistry(codecRegistry);
        return collection;
    }

}
