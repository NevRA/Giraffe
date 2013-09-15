package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.*;

public abstract class BaseObjectWithContent extends BaseObject {
    protected BaseObjectWithContent(String id) {
        super(id);
    }

    private String mTitle;
    private String mContent;
    private String mActorId;

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
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void fromJiveContainer(JiveContainer jiveContainer) {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveObject jiveObject = jiveContainer.getObject();

        setId(jiveObject.getId());
        setTitle(jiveContainer.getTitle());
        setContent(jiveObject.getSummary());
        setActorId(jiveActor.getId());
    }
}
