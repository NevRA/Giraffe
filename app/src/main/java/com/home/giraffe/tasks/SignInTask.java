package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.Constants;
import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.utils.Utils;

public class SignInTask extends BaseTaskLoader {

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
    public Object loadInBackground() {
        Utils.d("Started SignInTask");

        try {
            String communityUrl  = mUrl.contains("http") || mUrl.contains("https") ? mUrl : "https://" + mUrl;
            String token = mRequestsManager.signIn(communityUrl, mUserName, mUserPassword);

            mSettingsManager.setCommunityUrl(communityUrl);
            mSettingsManager.setUserToken(token);

            JiveAuthor me = mRequestsManager.getUserInfo(communityUrl + Constants.PEOPLE + Constants.ME);
            mSettingsManager.setUserDisplayName(me.getDisplayName());
            mSettingsManager.setUserJobTitle(me.getJobTitle());
            mSettingsManager.setUserId(me.getId());

        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }
        finally {
            Utils.d("Finished SignInTask");
        }

        return null;
    }
}