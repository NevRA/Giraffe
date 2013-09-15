package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.*;
import org.jsoup.Jsoup;

public abstract class BaseObjectWithContent extends BaseObject {
    protected BaseObjectWithContent(String id) {
        super(id);
    }

    private int mReplyCount;
    private int mLikeCount;
    private String mTitle;
    private String mContent;
    private String mActorId;

    public int getReplyCount(){
        return mReplyCount;
    }

    public int getLikeCount(){
        return mLikeCount;
    }

    public void setReplyCount(int replyCount){
        mReplyCount = replyCount;
    }

    public void setLikeCount(int likeCount){
        mLikeCount = likeCount;
    }

    public String getActorId() {
        return mActorId;
    }

    public void setActorId(String actorId) {
        mActorId = actorId;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = Jsoup.parse(content).text();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = Jsoup.parse(title).text();
    }

    public void fromJiveContainer(JiveContainer jiveContainer) {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveObject jiveObject = jiveContainer.getObject();
        Jive jive = jiveContainer.getJive();


        setId(jiveObject.getId());
        setReplyCount(jive.getReplyCount());
        setTitle(jiveContainer.getTitle());
        setContent(jiveObject.getSummary());
        setActorId(jiveActor.getId());
    }
}
