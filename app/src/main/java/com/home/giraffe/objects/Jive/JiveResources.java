package com.home.giraffe.objects.Jive;

public class JiveResources {
    private JiveRef avatar;
    private JiveRef self;
    private JiveRef comments;
    private JiveRef messages;

    public JiveRef getAvatar() {
        return avatar;
    }

    public JiveRef getSelf() {
        return self;
    }

    public JiveRef getCommentsId() {
        return comments != null ? comments : messages;
    }
}
