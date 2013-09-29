package com.home.giraffe.objects;

import android.text.format.DateUtils;
import com.home.giraffe.objects.Jive.Jive;
import com.home.giraffe.objects.Jive.JiveActor;
import com.home.giraffe.objects.Jive.JiveContainer;
import com.home.giraffe.objects.Jive.JiveObject;
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

    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(String updated) {
        mUpdatedTime = updated;
    }

    public void fromJiveContainer(JiveContainer jiveContainer) throws ParseException {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveObject jiveObject = jiveContainer.getObject();
        Jive jive = jiveContainer.getJive();


        setId(jiveObject.getId());
        setReplyCount(jive.getReplyCount());
        setTitle(jiveContainer.getTitle());
        setContent(jiveObject.getSummary());
        setActor(new Actor(jiveActor));

        Date date = ISO8601DateFormatter.toDate(jiveObject.getUpdated());
        setUpdatedTime(DateUtils.getRelativeTimeSpanString(date.getTime()).toString());
    }
}
