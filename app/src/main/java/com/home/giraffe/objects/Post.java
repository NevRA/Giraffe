package com.home.giraffe.objects;

import android.text.format.DateUtils;
import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.objects.Jive.JiveContent;
import com.home.giraffe.objects.Jive.JivePost;
import com.home.giraffe.utils.ISO8601DateFormatter;

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
        mComments.add(comment);
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
