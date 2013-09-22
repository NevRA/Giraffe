package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.objects.Jive.JiveContent;
import com.home.giraffe.objects.Jive.JivePost;

import java.util.*;

public class Post extends BaseObjectWithContent {
    public Post(String id) {
        super(id);
        mComments = Collections.synchronizedSet(new LinkedHashSet<Comment>());
    }

    private Set<Comment> mComments;

    public Set<Comment> getComments() {
        return mComments;
    }

    public void addComment(Comment comment) {
        mComments.add(comment);
    }

    public void clearCommentIds() {
        mComments.clear();
    }

    public String getFriendlyName(){
        return "Post";
    }

    public void fromJivePost(JivePost jivePost) {
        JiveAuthor jiveAuthor = jivePost.getAuthor();
        JiveContent jiveContent = jivePost.getContent();

        setId(jivePost.getId());
        setReplyCount(jivePost.getReplyCount());
        setLikeCount(jivePost.getLikeCount());
        setTitle(jivePost.getSubject());
        setContent(jiveContent.getText());
        setActor(new Actor(jiveAuthor));
    }
}
