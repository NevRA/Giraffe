package com.home.giraffe.objects;

public class Document extends Post {
    public Document(String id) {
        super(id);
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Document;
    }
}
