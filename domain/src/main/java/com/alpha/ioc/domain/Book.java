package com.alpha.ioc.domain;

public class Book {
    public Book() {
    }

    public Book(String author, String title, String press) {
        this.author = author;
        this.title = title;
        this.press = press;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String author;
    private String title;
    private String press;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
