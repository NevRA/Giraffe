package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.*;
import com.home.giraffe.utils.ISO8601DateFormatter;
import com.home.giraffe.utils.Utils;
import org.jsoup.Jsoup;

import java.text.ParseException;
import java.util.Date;

public abstract class BaseObjectWithContent extends BaseObject {
    protected BaseObjectWithContent(String id) {
        super(id);
    }

    private String mJiveId;
    private String mCommentsId;
    private int mReplyCount;
    private int mLikeCount;
    private String mTitle;
    private String mContent;
    private String mRawContent;
    private Actor mActor;
    private Long mUpdatedTime;
    private Long mPublishedTime;

    public void setJiveId(String id){
        mJiveId = id;
    }

    public String getJiveId(){
        return mJiveId;
    }

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
        return Utils.cleanTags(mRawContent);
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

    public Long getPublishedTime() {
        return mPublishedTime;
    }

    public void setPublishedTime(long published) {
        mPublishedTime = published;
    }

    public Long getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(long updated) {
        mUpdatedTime = updated;
    }

    public String getCommentsId() {
        return mCommentsId;
    }

    public void setCommentsId(String commentsId) {
        mCommentsId = commentsId;
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

        Date publishedDate = ISO8601DateFormatter.toDate(jiveObject.getPublished());
        setPublishedTime(publishedDate.getTime());

        Date updatedDate = ISO8601DateFormatter.toDate(jiveObject.getUpdated());
        setUpdatedTime(updatedDate.getTime());
    }

    public void fromJivePost(JivePost jivePost) throws ParseException {
        JiveAuthor jiveAuthor = jivePost.getAuthor();

        setId(jivePost.getId());
        setJiveId(jivePost.getJiveId());
        setParentId(jivePost.getParent());
        setCommentsId(jivePost.getCommentsId());
        setReplyCount(jivePost.getReplyCount());
        setLikeCount(jivePost.getLikeCount());
        setTitle(jivePost.getSubject());
        setRawContent(jivePost.getContent().getText());
        setActor(new Actor(jiveAuthor));

        Date publishedDate = ISO8601DateFormatter.toDate(jivePost.getPublished());
        setPublishedTime(publishedDate.getTime());

        Date updatedDate = ISO8601DateFormatter.toDate(jivePost.getUpdated());
        setUpdatedTime(updatedDate.getTime());
    }
}
