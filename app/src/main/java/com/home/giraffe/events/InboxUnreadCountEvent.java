package com.home.giraffe.events;


public class InboxUnreadCountEvent {
    private int mCount;

    public InboxUnreadCountEvent(int count){
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }
}
