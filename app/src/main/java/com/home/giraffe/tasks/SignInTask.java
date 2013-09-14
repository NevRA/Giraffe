package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Jive.JiveInbox;

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
    public JiveInbox loadInBackground() {
        try {
            getRequestsManager().signIn(mUrl, mUserName, mUserPassword);
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}