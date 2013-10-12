package com.home.giraffe.tasks;

import com.home.giraffe.objects.Jive.JiveTypes;

public class NewComment {
    private JiveTypes mType;
    private String mMessage;

    public NewComment(JiveTypes type, String message){
        mType = type;
        mMessage = message;
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
}
