package com.home.giraffe.objects;

public class JiveContainer {
    String title;
    private String content;
    JiveObject object;

    public String getId() {
        return object.getId();
    }

    public JiveTypes getType() {
        return object.getObjectType();
    }

    public String getTitle() {
        return title;
    }

    public String getDisplayName() {
        return object.getDisplayName();
    }

    public String getContent() {
        return content;
    }
}
