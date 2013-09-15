package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.objects.Jive.JiveAuthor;
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
            String token = getRequestsManager().signIn(mUrl, mUserName, mUserPassword);

            ISettingsManager settingsManager = getSettingsManager();

            settingsManager.setCommunityUrl(mUrl);
            settingsManager.setUserToken(token);

            JiveAuthor me = getRequestsManager().getUserInfo(mUrl + Constants.PEOPLE + Constants.ME);
            settingsManager.setUserDisplayName(me.getDisplayName());
            settingsManager.setUserJobTitle(me.getJobTitle());
            settingsManager.setUserId(me.getId());

        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}