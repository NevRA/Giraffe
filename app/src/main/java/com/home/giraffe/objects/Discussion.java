package com.home.giraffe.objects;

public class Discussion {
    private String subject;
    Author author;
    private Content content;

    public Author getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }
}
