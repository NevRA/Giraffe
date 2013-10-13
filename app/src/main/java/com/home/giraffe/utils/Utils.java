package com.home.giraffe.utils;

import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.JiveTypes;
import roboguice.RoboGuice;

import java.text.DecimalFormat;

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

    public static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
