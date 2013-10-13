package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JivePost;

import java.text.ParseException;

public class File extends Post {

    private String mName;
    private String mBinaryUrl;
    private String mContentType;

    public File(String id) {
        super(id);
    }

    public String getBinaryUrl(){
        return mBinaryUrl;
    }

    public void setBinaryUrl(String binaryUrl){
        mBinaryUrl = binaryUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getContentType() {
        return mContentType;
    }

    public void setContentType(String contentType) {
        this.mContentType = contentType;
    }

    @Override
    public String getFriendlyName() {
        return "File";
    }

    public void fromJivePost(JivePost jivePost) throws ParseException {
        super.fromJivePost(jivePost);

        setBinaryUrl(jivePost.getBinaryURL());
        setName(jivePost.getName());
        setContentType(jivePost.getContentType());
    }
}
