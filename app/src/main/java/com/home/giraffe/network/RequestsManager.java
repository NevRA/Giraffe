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
import com.home.giraffe.objects.Person;

import java.net.URL;

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
    }

    @Override
    public Person getUserInfo(String userId) throws Exception {
        String profile = mSettingsManager.getCommunityUrl() + Constants.PEOPLE + userId;

        return mGson.fromJson(mConnector.getRequest(profile).getBody(), Person.class);
    }
}
