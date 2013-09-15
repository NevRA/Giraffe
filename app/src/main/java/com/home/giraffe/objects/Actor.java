package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveActor;
import com.home.giraffe.objects.Jive.JiveAuthor;

public class Actor extends BaseObject{
    private String mDisplayName;
    private String mAvatarUrl;

    protected Actor(String id) {
        super(id);
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Actor;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public static Actor fromJiveActor(JiveActor jiveActor){
        Actor actor = new Actor(jiveActor.getId());
        actor.setDisplayName(jiveActor.getDisplayName());
        actor.setAvatarUrl(jiveActor.getImage().getUrl());

        return actor;
    }

    public static Actor fromJiveAuthor(JiveAuthor jiveAuthor){
        Actor actor = new Actor(jiveAuthor.getId());
        actor.setDisplayName(jiveAuthor.getDisplayName());
        actor.setAvatarUrl(jiveAuthor.getResources().getAvatar().getRef());

        return actor;
    }
}
