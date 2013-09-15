package com.home.giraffe.objects;

public class ActivityItem {
    private String mId;
    private BaseObjectTypes mType;

    public ActivityItem(String id, BaseObjectTypes type){
        mId = id;
        mType = type;
    }

    public String getId(){
        return mId;
    }

    public BaseObjectTypes getType(){
        return mType;
    }
}
