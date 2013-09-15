package com.home.giraffe.objects;

public class Comment extends BaseObjectWithContent {
    public Comment(String id) {
        super(id);
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Comment;
    }
}
