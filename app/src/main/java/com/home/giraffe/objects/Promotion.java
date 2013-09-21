package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.*;

public class Promotion extends BaseObject {
    private String mActorId;
    private String mLevelName;
    private String mLevelImageUrl;

    public Promotion(String id) {
        super(id);
    }

    public Promotion(JiveContainer jiveContainer) {
        super(null);
        fromJiveContainer(jiveContainer);
    }

    public String getActorId() {
        return mActorId;
    }

    public void setActorId(String actorId) {
        mActorId = actorId;
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Promotion;
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

        setId(jiveContainer.getPublished());
        setLevelName(jiveObject.getDisplayName());
        setActorId(jiveActor.getId());
        setLevelImageUrl(jiveObject.getImage().getUrl());
    }
}
