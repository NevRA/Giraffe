package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.*;

public class Promotion {
    private String mActorId;
    private String mLevelName;
    private String mLevelImageUrl;

    public Promotion(JiveContainer jiveContainer) {
        fromJiveContainer(jiveContainer);
    }

    public String getActorId() {
        return mActorId;
    }

    public void setActorId(String actorId) {
        mActorId = actorId;
    }

    public String getLevelName() {
        return mLevelName;
    }

    public void setLevelName(String levelName) {
        this.mLevelName = levelName;
    }

    public String getLevelImageUrl() {
        return mLevelImageUrl;
    }

    public void setLevelImageUrl(String levelImageUrl) {
        mLevelImageUrl = levelImageUrl;
    }

    public void fromJiveContainer(JiveContainer jiveContainer) {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveObject jiveObject = jiveContainer.getObject();

        setLevelName(jiveObject.getDisplayName());
        setActorId(jiveActor.getId());
        setLevelImageUrl(jiveObject.getImage().getUrl());
    }
}
