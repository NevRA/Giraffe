package com.home.giraffe.objects;

import com.home.giraffe.utils.Utils;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Post extends BaseObjectWithContent {
    public Post(String id) {
        super(id);
        mComments = Collections.synchronizedSet(new TreeSet<Comment>());
    }

    private Set<Comment> mComments;

    public Set<Comment> getComments() {
        return mComments;
    }

    public void addComment(Comment comment) {
        Comment parent = getCommentById(comment.getParentId());
        if(parent != null){
            comment.setLevel(parent.getLevel() + 1);
        }

        mComments.add(comment);

        Utils.v(String.format("To post %s added comment %s", getId(), comment));
    }

    public Comment getCommentById(String id){
        for (Comment comment : mComments){
            if(comment.getId().equals(id)){
                return comment;
            }
        }

        return null;
    }

    public String getFriendlyName() {
        return "Post";
    }
}
