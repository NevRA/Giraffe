package com.home.giraffe.objects.Jive;

public class JiveContainer {
    String title;
    String content;
    JiveObject object;
    JiveActor actor;
    Jive jive;

    public Jive getJive() {
        return jive;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public JiveObject getObject() {
        return object;
    }

    public JiveActor getActor() {
        return actor;
    }
}
