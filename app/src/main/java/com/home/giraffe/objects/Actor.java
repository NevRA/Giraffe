package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveActor;
import com.home.giraffe.objects.Jive.JiveAuthor;

public class Actor extends BaseObject{
    private String mDisplayName;
    private String mAvatarUrl;

    public Actor(JiveActor jiveActor) {
        super(null);
        fromJiveActor(jiveActor);
    }

    public Actor(JiveAuthor jiveAuthor) {
        super(null);
        fromJiveAuthor(jiveAuthor);
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

    public void fromJiveActor(JiveActor jiveActor){
        setDisplayName(jiveActor.getDisplayName());
        setAvatarUrl(jiveActor.getImage().getUrl());
    }

    public void fromJiveAuthor(JiveAuthor jiveAuthor){
        setDisplayName(jiveAuthor.getDisplayName());
        setAvatarUrl(jiveAuthor.getResources().getAvatar().getRef());
    }
}
