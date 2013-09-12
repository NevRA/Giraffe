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
import com.home.giraffe.objects.*;

public class RequestsManager implements IRequestsManager {
    @Inject
    Gson mGson;

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

        String token = NetworkUtils.getTokenFromCookies(loginResponse.getCookies());
        if(token == null){
            throw new Exception(mUiManager.getString(R.string.signin_access_denied_error_message));
        }

        mSettingsManager.setCommunityUrl(communityUrl);
        mSettingsManager.setUserToken(token);

        Author me = getUserInfo(Constants.ME);
        mSettingsManager.setUserDisplayName(me.getDisplayName());
        mSettingsManager.setUserJobTitle(me.getJobTitle());
        mSettingsManager.setUserId(me.getId());
    }

    @Override
    public Author getUserInfo(String userId) throws Exception {
        String profile = mSettingsManager.getCommunityUrl() + Constants.PEOPLE + userId;

        return mGson.fromJson(mConnector.getRequest(profile).getBody(), Author.class);
    }

    @Override
    public Inbox getInbox() throws Exception {
        String inbox = mSettingsManager.getCommunityUrl() + Constants.INBOX;

        return mGson.fromJson(mConnector.getRequest(inbox).getBody(), Inbox.class);
    }

    @Override
    public Activities getActivities() throws Exception {
        String activities = mSettingsManager.getCommunityUrl() + Constants.ACTIVITIES;

        Activities result =  mGson.fromJson(mConnector.getRequest(activities).getBody(), Activities.class);
        for (JiveContainer container : result.getList()){
            if(container.getType() == JiveTypes.JiveMessage){
                Discussion discussion =  mGson.fromJson(mConnector.getRequest(container.getParentId()).getBody(), Discussion.class);
                container.setDiscussion(discussion);
            }
        }

        return result;
    }
}
