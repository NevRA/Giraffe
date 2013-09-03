package com.home.giraffe.tasks;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.network.RequestsManager;
import com.home.giraffe.ui.MessageDialogFragmentActivity;
import roboguice.content.RoboAsyncTaskLoader;

public class SignInTask extends RoboAsyncTaskLoader {

    @Inject
    IRequestsManager mRequestsManager;

    @Inject
    IUiManager mUiManager;

    private Context mContext;
    private String mUrl;
    private String mUserName;
    private String mUserPassword;

    public SignInTask(Context context, String url, String userName, String userPassword) {
        super(context);
        mContext = context;
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
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    mUiManager.startActivity(mContext, MessageDialogFragmentActivity.class);
                }
            });
        }

        return null;
    }
}