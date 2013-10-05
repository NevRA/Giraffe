package com.home.giraffe.utils;

import android.util.Log;
import com.home.giraffe.Constants;
import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.JiveTypes;

public class Utils {
    public static Post getPostFromObjectType(JiveTypes jiveTypes) {
        Post post;
        switch (jiveTypes) {
            case JiveDiscussion:
                post = new Discussion("");
                break;
            case JiveDocument:
                post = new Document("");
                break;
            case JiveFile:
                post = new File("");
                break;
            case JivePoll:
                post = new Poll("");
                break;
            default:
                post = new Post("");
                break;
        }

        return post;
    }

    public static void v(String message){
        Log.v(Constants.APP_TAG, message);
    }

    public static void d(String message){
        Log.d(Constants.APP_TAG, message);
    }

    public static void e(Throwable t){
        Log.e(Constants.APP_TAG, t.getMessage() + "\n" + Log.getStackTraceString(t));
    }
}
