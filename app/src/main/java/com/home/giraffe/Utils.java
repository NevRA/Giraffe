package com.home.giraffe;

import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.JiveContainer;
import com.home.giraffe.objects.Jive.JivePost;
import com.home.giraffe.objects.Jive.JiveTypes;

public class Utils {

    public Comment getCommentFromJiveContainer(JiveContainer jiveContainer) {
        Comment comment = new Comment(jiveContainer.getObject().getId());
        comment.fromJiveContainer(jiveContainer);

        return comment;
    }


    public Post getPostFromJiveContainer(JiveContainer jiveContainer) {
        Post post = getPostFromObjectType(jiveContainer.getObject().getType());
        post.fromJiveContainer(jiveContainer);

        return post;
    }

    public Post getPostFromJivePost(JivePost jivePost) {
        Post post = getPostFromObjectType(jivePost.getType());
        post.fromJivePost(jivePost);

        return post;
    }

    public Post getPostFromObjectType(JiveTypes jiveTypes) {
        Post post;
        switch (jiveTypes) {
            case JiveDiscussion:
                post = new Discussion("");
                break;
            case JiveDocument:
                post = new Document("");
                break;
            case JiveFile:
                post = new File("");
                break;
            case JivePoll:
                post = new Poll("");
                break;
            default:
                post = new Post("");
                break;
        }

        return post;
    }
}
