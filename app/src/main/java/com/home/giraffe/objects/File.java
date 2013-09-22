package com.home.giraffe.objects;

public class File extends Post {
    public File(String id) {
        super(id);
    }

    @Override
    public String getFriendlyName() {
        return "File";
    }
}
