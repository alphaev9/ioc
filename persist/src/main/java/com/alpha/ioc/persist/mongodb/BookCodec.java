package com.alpha.ioc.persist.mongodb;

import com.alpha.ioc.domain.Book;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class BookCodec implements Codec<Book> {
    @Override
    public Book decode(BsonReader bsonReader, DecoderContext decoderContext) {
        bsonReader.readStartDocument();
        bsonReader.readObjectId();

        bsonReader.readName();
        bsonReader.readStartDocument();
        bsonReader.readName();
        String author = bsonReader.readString();
        bsonReader.readName();
        String title = bsonReader.readString();
        bsonReader.readName();
        String press = bsonReader.readString();
        bsonReader.readEndDocument();
        bsonReader.readEndDocument();

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setPress(press);
        return book;
    }

    @Override
    public void encode(BsonWriter bsonWriter, Book book, EncoderContext encoderContext) {
        if (book == null) {
            bsonWriter.writeNull();
        } else {
            bsonWriter.writeStartDocument();
            bsonWriter.writeName("author");
            bsonWriter.writeString(book.getAuthor());
            bsonWriter.writeName("title");
            bsonWriter.writeString(book.getTitle());
            bsonWriter.writeName("press");
            bsonWriter.writeString(book.getPress());
            bsonWriter.writeEndDocument();
        }
    }

    @Override
    public Class<Book> getEncoderClass() {
        return Book.class;
    }
}
