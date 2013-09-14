package com.home.giraffe.network;

import android.net.Uri;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IConnector;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.Jive.*;

public class RequestsManager implements IRequestsManager {
    @Inject
    Gson mGson;

    @Inject
    NetworkUtils mNetworkUtils;

    @Inject
    IUiManager mUiManager;

    @Inject
    IConnector mConnector;

    @Inject
    ISettingsManager mSettingsManager;

    @Override
    public void signIn(String url, String userName, String userPassword) throws Exception {
        String communityUrl  = url.contains("http") || url.contains("https") ? url : "https://" + url;
        String loginUrl = communityUrl + Constants.LOGIN;
        String credentialsBody = String.format("username=%s&password=%s&autoLogin=true", Uri.encode(userName), Uri.encode(userPassword));

        HttpResponse loginResponse = mConnector.postRequest(loginUrl, credentialsBody, false);

        String token = mNetworkUtils.getTokenFromCookies(loginResponse.getCookies());
        if(token == null){
            throw new Exception(mUiManager.getString(R.string.signin_access_denied_error_message));
        }

        mSettingsManager.setCommunityUrl(communityUrl);
        mSettingsManager.setUserToken(token);

        JiveAuthor me = getUserInfo(Constants.ME);
        mSettingsManager.setUserDisplayName(me.getDisplayName());
        mSettingsManager.setUserJobTitle(me.getJobTitle());
        mSettingsManager.setUserId(me.getId());
    }

    @Override
    public JiveAuthor getUserInfo(String userId) throws Exception {
        String profile = mSettingsManager.getCommunityUrl() + Constants.PEOPLE + userId;
        return mGson.fromJson(mConnector.getRequest(profile).getBody(), JiveAuthor.class);
    }

    @Override
    public JiveInbox getInbox() throws Exception {
        String inbox = mSettingsManager.getCommunityUrl() + Constants.INBOX;
        return mGson.fromJson(mConnector.getRequest(inbox).getBody(), JiveInbox.class);
    }

    @Override
    public JivePost getPost(String url) throws Exception {
        return mGson.fromJson(mConnector.getRequest(url).getBody(), JivePost.class);
    }

    @Override
    public JiveActivities getActivities() throws Exception {
        String activities = mSettingsManager.getCommunityUrl() + Constants.ACTIVITIES;
        return mGson.fromJson(mConnector.getRequest(activities).getBody(), JiveActivities.class);
    }
}
