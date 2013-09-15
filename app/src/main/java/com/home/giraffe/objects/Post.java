package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.objects.Jive.JiveContent;
import com.home.giraffe.objects.Jive.JivePost;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Post extends BaseObjectWithContent {
    public Post(String id) {
        super(id);
        mCommentIds = Collections.synchronizedSet(new HashSet<String>());
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Post;
    }

    private Set<String> mCommentIds;

    public Set<String> getCommentIds() {
        return mCommentIds;
    }

    public void addCommentId(String commentId) {
        mCommentIds.add(commentId);
    }

    public void clearCommentIds() {
        mCommentIds.clear();
    }

    public void fromJivePost(JivePost jivePost) {
        JiveAuthor jiveAuthor = jivePost.getAuthor();
        JiveContent jiveContent = jivePost.getContent();

        setId(jivePost.getId());
        setTitle(jivePost.getSubject());
        setContent(jiveContent.getText());
        setActorId(jiveAuthor.getId());
    }
}
