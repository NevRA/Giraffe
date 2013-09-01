package com.home.giraffe.network;

import android.net.Uri;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.IConnector;
import com.home.giraffe.interfaces.IRequestsManager;
import com.home.giraffe.objects.Person;

public class RequestsManager implements IRequestsManager {
    @Inject
    Gson mGson;

    @Inject
    IConnector mConnector;

    @Inject
    public RequestsManager(){
    }

    @Override
    public void signIn(String serverAddress, String userName, String userPassword) throws Exception {
        String loginUrl = "https://" + serverAddress + Constants.LOGIN;
        String csMeUrl = "https://" + serverAddress + "/api/core/v3/people/@me";
        String credentialsBody = String.format("username=%s&password=%s&autoLogin=true", Uri.encode(userName), Uri.encode(userPassword));

        HttpResponse authCookiesResponse = mConnector.getRequest(loginUrl);
        HttpResponse loginResponse = mConnector.postRequest(loginUrl, credentialsBody, authCookiesResponse.getCookies(), false);
        HttpResponse profileResponse = mConnector.getRequest(csMeUrl, loginResponse.getCookies());
        Person person = mGson.fromJson(profileResponse.getBody(), Person.class);
    }
}
