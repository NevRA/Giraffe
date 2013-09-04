package com.home.giraffe.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.AsyncTaskLoader;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.interfaces.IUiManager;
import roboguice.content.RoboAsyncTaskLoader;

import java.lang.ref.WeakReference;

public class SignInTask extends RoboAsyncTaskLoader {

    @Inject
    IRequestsManager mRequestsManager;

    @Inject
    IUiManager mUiManager;

    private WeakReference<FragmentActivity> mActivity;
    private String mUrl;
    private String mUserName;
    private String mUserPassword;

    public SignInTask(FragmentActivity activity, String url, String userName, String userPassword) {
        super(activity);
        mActivity = new WeakReference<FragmentActivity>(activity);
        mUrl = url;
        mUserName = userName;
        mUserPassword = userPassword;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Override
    protected void onStopLoading(){

    }

    @Override
    public Object loadInBackground() {
        try {
            mRequestsManager.signIn(mUrl, mUserName, mUserPassword);
        } catch (Exception e) {
            mUiManager.showError(mActivity.get(), e);
        }

        return null;
    }
}