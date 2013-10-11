package com.home.giraffe.events;

public class ActionsUnreadCountEvent {
    private int mCount;

    public ActionsUnreadCountEvent(int count){
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }
}
