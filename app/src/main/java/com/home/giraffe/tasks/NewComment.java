package com.home.giraffe.tasks;

import com.home.giraffe.objects.Jive.JiveTypes;

public class NewComment {
    private JiveTypes mType;
    private String mMessage;
    private String mParentId;

    public NewComment(JiveTypes type, String message, String parentId){
        mType = type;
        mMessage = message;
        mParentId = parentId;
    }

    public JiveTypes getType() {
        return mType;
    }

    public void setType(JiveTypes type) {
        mType = type;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getParentId() {
        return mParentId;
    }

    public void setParentId(String parentId) {
        this.mParentId = parentId;
    }
}
