package com.home.giraffe.objects;

public class Poll extends Post {
    public Poll(String id) {
        super(id);
    }

    @Override
    public String getFriendlyName() {
        return "Poll";
    }
}
