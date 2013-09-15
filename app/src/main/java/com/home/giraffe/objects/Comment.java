package com.home.giraffe.objects;

public class Comment extends BaseObject {
    public Comment(String id) {
        super(id);
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Comment;
    }
}
