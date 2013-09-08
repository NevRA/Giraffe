package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;

public class SignInTask extends BaseTask {

    private String mUrl;
    private String mUserName;
    private String mUserPassword;

    public SignInTask(FragmentActivity activity, String url, String userName, String userPassword) {
        super(activity);
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
            getRequestsManager().signIn(mUrl, mUserName, mUserPassword);
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}