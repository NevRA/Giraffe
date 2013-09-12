package com.home.giraffe.objects;

public class Actor {
    String id;
    String displayName;
    Image image;

    public String getId() {
        return id;
    }

    public String getAvatar() {
        return image.getUrl();
    }

    public String getDisplayName() {
        return displayName;
    }
}
