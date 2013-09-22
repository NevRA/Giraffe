package com.home.giraffe.objects.socialnews;

import com.home.giraffe.objects.Actor;
import com.home.giraffe.objects.Jive.JiveActor;
import com.home.giraffe.objects.Jive.JiveContainer;
import com.home.giraffe.objects.Jive.JiveObject;

public abstract class BaseSocialNewsItem {
    private Actor mActor;

    public Actor getActor() {
        return mActor;
    }

    public void setActor(Actor actor) {
        this.mActor = actor;
    }

    public void fromJiveContainer(JiveContainer jiveContainer) {
        JiveActor jiveActor = jiveContainer.getActor();
        setActor(new Actor(jiveActor));
    }
}
