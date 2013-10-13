package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JivePost;

import java.text.ParseException;

public class File extends Post {
    private String mBinaryUrl;

    public File(String id) {
        super(id);
    }

    public String getBinaryUrl(){
        return mBinaryUrl;
    }

    public void setBinaryUrl(String binaryUrl){
        mBinaryUrl = binaryUrl;
    }

    @Override
    public String getFriendlyName() {
        return "File";
    }

    public void fromJivePost(JivePost jivePost) throws ParseException {
        super.fromJivePost(jivePost);

        setBinaryUrl(jivePost.getBinaryURL());
    }
}
