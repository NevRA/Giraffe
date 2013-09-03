package com.home.giraffe.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.network.RequestsManager;
import roboguice.content.RoboAsyncTaskLoader;

public class SignInTask extends RoboAsyncTaskLoader {

    @Inject
    IRequestsManager mRequestsManager;

    private String mUrl;
    private String mUserName;
    private String mUserPassword;

    public SignInTask(Context context, String url, String userName, String userPassword) {
        super(context);
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
    public Object loadInBackground() {
        try {
            mRequestsManager.signIn(mUrl, mUserName, mUserPassword);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }
}