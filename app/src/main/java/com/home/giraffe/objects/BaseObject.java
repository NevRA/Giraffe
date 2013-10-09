package com.home.giraffe.objects;

public abstract class BaseObject {
    private String mParentId;
    private String mId;

    protected BaseObject(String id){
        setId(id);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getParentId() {
        return mParentId;
    }

    public void setParentId(String id) {
        mParentId = id;
    }
}
