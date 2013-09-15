package com.home.giraffe.objects;

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
}
