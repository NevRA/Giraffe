package com.home.giraffe.objects;


public class Author {
    String displayName;
    int id;
    Jive jive;

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }

    public String getJobTitle(){
        return jive.getJobTitle();
    }
}
