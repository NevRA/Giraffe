package com.home.giraffe.utils;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import com.home.giraffe.Constants;
import com.home.giraffe.MainApplication;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.JiveTypes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import roboguice.RoboGuice;

import java.text.DecimalFormat;

public class Utils {
    static final String className = Utils.class.getName().toLowerCase();

    public static Post getPostFromObjectType(JiveTypes jiveTypes) {
        Post post = null;
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
            case JivePost:
                post = new Post("");
                break;
            default:
                break;
        }

        return post;
    }

    public static String addHtmlTags(String text){
        String html = text.replace("\n", "<br/>");
        return html;
    }

    public static String cleanTags(String html){
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("p");
        for(int i = elements.size() - 1; i >= 0; i --){
            Element el = elements.get(i);
            if(el.html().equals("&nbsp;")){
                el.remove();
            }
        }

        return doc.html();
    }

    public static String getAuthorizationCookie(){
        ISettingsManager settingsManager = RoboGuice.getInjector(MainApplication.getInstance()).getProvider(ISettingsManager.class).get();

        return Constants.RememberMeCookie + "=" + settingsManager.getUserToken();
    }

    public static int dpToPx(int dp){
        Resources r = MainApplication.getInstance().getResources();
        return  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static void v(String str, Object...args) {
        Log.v(Constants.APP_TAG, getLocation() + String.format(str, args));
    }

    public static void v(String message) {
        Log.v(Constants.APP_TAG, getLocation() + message);
    }

    public static void d(String str, Object...args) {
        Log.d(Constants.APP_TAG, getLocation() + String.format(str, args));
    }

    public static void d(String message) {
        Log.d(Constants.APP_TAG, getLocation() + message);
    }

    public static void w(String str, Object...args) {
        Log.w(Constants.APP_TAG, getLocation() + String.format(str, args));
    }

    public static void w(String message) {
        Log.w(Constants.APP_TAG, getLocation() + message);
    }

    public static void e(Throwable t) {
        Log.e(Constants.APP_TAG, getLocation() + t.getMessage() + "\n" + Log.getStackTraceString(t));
    }

    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    private static String getLocation() {
        final Long threadId = Thread.currentThread().getId();
        final StackTraceElement[] traces = Thread.currentThread()
                .getStackTrace();
        boolean found = false;

        for (StackTraceElement trace : traces) {
            try {
                if (found) {
                    if (!trace.getClassName().toLowerCase().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + threadId.toString() + "] : [" + getClassName(clazz) + ":"
                                + trace.getMethodName() + ":"
                                + trace.getLineNumber() + "]: ";
                    } else
                        found = false;

                }
                if (trace.getClassName().toLowerCase().startsWith(className)) {
                    found = true;
                }
            } catch (ClassNotFoundException e) {
                Log.e(Constants.APP_TAG, e.getMessage());
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            String className = clazz.getSimpleName();
            if (!TextUtils.isEmpty(className)) {
                return className;
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }
}
