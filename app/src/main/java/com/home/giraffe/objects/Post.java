package com.home.giraffe.objects;

import android.text.format.DateUtils;
import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.objects.Jive.JiveContent;
import com.home.giraffe.objects.Jive.JivePost;
import com.home.giraffe.utils.ISO8601DateFormatter;
import com.home.giraffe.utils.Utils;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

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
        Comment parent = getCommentById(comment.getParentId());
        if(parent != null){
            comment.setLevel(parent.getLevel() + 1);
        }

        mComments.add(comment);

        Utils.d(String.format("To post %s added comment %s", getId(), comment));
    }

    public Comment getCommentById(String id){
        for (Comment comment : mComments){
            if(comment.getId().equals(id)){
                return comment;
            }
        }

        return null;
    }

    public void clearCommentIds() {
        mComments.clear();
    }

    public String getFriendlyName() {
        return "Post";
    }

    public void fromJivePost(JivePost jivePost) throws ParseException {
        JiveAuthor jiveAuthor = jivePost.getAuthor();
        JiveContent jiveContent = jivePost.getContent();

        setId(jivePost.getId());
        setReplyCount(jivePost.getReplyCount());
        setLikeCount(jivePost.getLikeCount());
        setTitle(jivePost.getSubject());
        setRawContent(jiveContent.getText());
        setActor(new Actor(jiveAuthor));

        Date date = ISO8601DateFormatter.toDate(jivePost.getUpdated());
        setUpdatedTime(DateUtils.getRelativeTimeSpanString(date.getTime()).toString());
    }
}
