package com.home.giraffe.events;

import java.io.File;

public class OpenFileEvent {
    private File mFile;
    private String mType;

    public OpenFileEvent(File file, String type){
        this.mFile = file;
        this.mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public File getFile() {
        return mFile;
    }

    public void setFile(File file) {
        this.mFile = file;
    }
}
