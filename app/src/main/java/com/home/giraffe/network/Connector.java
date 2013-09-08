package com.home.giraffe.network;

import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.IConnector;
import com.home.giraffe.interfaces.ISettingsManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Connector implements IConnector {

    @Inject
    ISettingsManager mSettingsManager;

    DefaultHttpClient mHttpClient;

    public Connector() {
        mHttpClient = new DefaultHttpClient();
    }

    private String cutSecurityString(String response) {
        return response.replaceAll("throw.*;\\s*", "");
    }

    private com.home.giraffe.network.HttpResponse proceedResponse(HttpResponse response) throws Exception {
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK &&
                response.getStatusLine().getStatusCode() != 302) {
            throw new Exception(response.getStatusLine().getReasonPhrase());
        }

        HttpEntity entity = response.getEntity();

        CookieStore cookieStore = mHttpClient.getCookieStore();
        Cookie[] cookies = cookieStore.getCookies().toArray(new Cookie[cookieStore.getCookies().size()]);
        String body = cutSecurityString(EntityUtils.toString(entity));
        return new com.home.giraffe.network.HttpResponse(cookies, body);
    }

    @Override
    public com.home.giraffe.network.HttpResponse getRequest(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(new BasicHeader("Accept", "*/*"));
        httpGet.addHeader("Cookie", Constants.RememberMeCookie + "=" + mSettingsManager.getUserToken());
        HttpResponse response = mHttpClient.execute(httpGet);
        return proceedResponse(response);
    }

    @Override
    public com.home.giraffe.network.HttpResponse postRequest(String url, String body) throws Exception {
        return postRequest(url, body, true);
    }

    @Override
    public com.home.giraffe.network.HttpResponse postRequest(String url, String body, boolean allowRedirect) throws Exception {
        if (!allowRedirect) {
            mHttpClient.setRedirectHandler(new DefaultRedirectHandler() {
                @Override
                public boolean isRedirectRequested(HttpResponse response, HttpContext context) {
                    return false;
                }
            });
        }

        StringEntity entity = new StringEntity(body, HTTP.UTF_8);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        httpPost.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        httpPost.addHeader("Cookie", Constants.RememberMeCookie + "=" + mSettingsManager.getUserToken());

        HttpResponse response = mHttpClient.execute(httpPost);
        return proceedResponse(response);
    }
}
