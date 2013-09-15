package com.home.giraffe.objects;

public abstract class BaseObject {
    private String mId;

    protected BaseObject(String id){
        setId(id);
    }

    abstract public BaseObjectTypes getType();

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
