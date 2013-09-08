package com.home.giraffe.objects;


public class Person {
    private String displayName;
    private int id;
    private Jive jive;

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
