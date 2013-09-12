package com.home.giraffe.objects;

public class JiveContainer {
    String title;
    String content;
    private Discussion discussion;
    JiveObject object;
    Actor actor;
    Jive jive;

    public int replyCount(){
        return jive.getReplyCount();
    }

    public boolean isQuestion(){
        return jive.isQuestion();
    }

    public String getId() {
        return object.getId();
    }

    public Jive getJive() {
        return jive;
    }

    public JiveTypes getType() {
        return object.getObjectType();
    }

    public String getTitle() {
        return title;
    }

    public String getDisplayName() {
        return object.getDisplayName();
    }

    public String getContent() {
        return content;
    }

    public String getObjectSummary() {
        return object.getSummary();
    }

    public String getParentSummary() {
        return jive.getParentSummary();
    }

    public String getUserAvatar() {
        return actor.getAvatar();
    }

    public String getUserDisplayName() {
        return actor.getDisplayName();
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }
}
