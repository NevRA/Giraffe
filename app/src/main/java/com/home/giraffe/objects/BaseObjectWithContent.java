package com.home.giraffe.objects;

import android.text.format.DateUtils;
import com.home.giraffe.network.NetworkUtils;
import com.home.giraffe.objects.Jive.*;
import com.home.giraffe.utils.ISO8601DateFormatter;
import org.jsoup.Jsoup;

import java.text.ParseException;
import java.util.Date;

public abstract class BaseObjectWithContent extends BaseObject {
    protected BaseObjectWithContent(String id) {
        super(id);
    }

    private int mReplyCount;
    private int mLikeCount;
    private String mTitle;
    private String mContent;
    private String mRawContent;
    private Actor mActor;
    private String mUpdatedTime;

    public int getReplyCount() {
        return mReplyCount;
    }

    public int getLikeCount() {
        return mLikeCount;
    }

    public void setReplyCount(int replyCount) {
        mReplyCount = replyCount;
    }

    public void setLikeCount(int likeCount) {
        mLikeCount = likeCount;
    }

    public Actor getActor() {
        return mActor;
    }

    public void setActor(Actor actor) {
        mActor = actor;
    }

    public String getRawContent() {
        return NetworkUtils.cleanTags(mRawContent);
    }

    public void setRawContent(String content) {
        mContent = Jsoup.parse(content).text();
        mRawContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = Jsoup.parse(title).text();
    }

    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(String updated) {
        mUpdatedTime = updated;
    }

    public void fromJiveActivityContainer(JiveContainer jiveContainer) throws ParseException {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveObject jiveObject = jiveContainer.getObject();
        Jive jive = jiveContainer.getJive();
        JiveObject parent = jive.getParent();

        setId(jiveObject.getId());
        setParentId(parent != null ? parent.getId() : "");
        setReplyCount(jive.getReplyCount());
        setTitle(jiveContainer.getTitle());
        setRawContent(jiveObject.getSummary());
        setActor(new Actor(jiveActor));

        Date date = ISO8601DateFormatter.toDate(jiveObject.getUpdated());
        setUpdatedTime(DateUtils.getRelativeTimeSpanString(date.getTime()).toString());
    }

    public void fromJivePost(JivePost jivePost) throws ParseException {
        JiveAuthor jiveAuthor = jivePost.getAuthor();

        setId(jivePost.getId());
        setParentId(jivePost.getParent());
        setReplyCount(jivePost.getReplyCount());
        setTitle(jivePost.getSubject());
        setRawContent(jivePost.getContent().getText());
        setActor(new Actor(jiveAuthor));

        Date date = ISO8601DateFormatter.toDate(jivePost.getUpdated());
        setUpdatedTime(DateUtils.getRelativeTimeSpanString(date.getTime()).toString());
    }
}
