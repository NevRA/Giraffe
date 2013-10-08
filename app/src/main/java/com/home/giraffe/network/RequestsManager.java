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
import com.home.giraffe.objects.Jive.JiveActivities;
import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.objects.Jive.JiveInbox;
import com.home.giraffe.objects.Jive.JivePost;
import com.home.giraffe.utils.Utils;

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
    public String signIn(String url, String userName, String userPassword) throws Exception {
        Utils.d("Started signIn");

        String loginUrl = url + Constants.LOGIN;
        String credentialsBody = String.format("username=%s&password=%s&autoLogin=true", Uri.encode(userName), Uri.encode(userPassword));

        HttpResponse loginResponse = mConnector.postRequest(loginUrl, credentialsBody, false);

        String token = NetworkUtils.getTokenFromCookies(loginResponse.getCookies());
        if (token == null) {
            throw new Exception(mUiManager.getString(R.string.signin_access_denied_error_message));
        }

        return token;
    }

    @Override
    public JiveAuthor getUserInfo(String userId) throws Exception {
        Utils.d("Started getUserInfo");

        return mGson.fromJson(mConnector.getRequest(userId).getBody(), JiveAuthor.class);
    }

    @Override
    public JiveInbox getInbox() throws Exception {
        Utils.d("Started getInbox");

        String inbox = mSettingsManager.getCommunityUrl() + Constants.INBOX;
        return mGson.fromJson(mConnector.getRequest(inbox).getBody(), JiveInbox.class);
    }

    @Override
    public int getInboxBadgeCount() throws Exception {
        Utils.d("Started getInboxBadgeCount");

        String inbox = mSettingsManager.getCommunityUrl() + Constants.INBOX_ZERO_RECORDS;
        return mGson.fromJson(mConnector.getRequest(inbox).getBody(), JiveInbox.class).getUnreadCount();
    }

    @Override
    public int getActionsBadgeCount() throws Exception {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public JivePost getPost(String url) throws Exception {
        Utils.d("Started getPost for: " + url);

        return mGson.fromJson(mConnector.getRequest(url).getBody(), JivePost.class);
    }

    @Override
    public JiveActivities getActivities(String url) throws Exception {
        Utils.d("Started getActivities for: " + url);

        return mGson.fromJson(mConnector.getRequest(url).getBody(), JiveActivities.class);
    }
}
