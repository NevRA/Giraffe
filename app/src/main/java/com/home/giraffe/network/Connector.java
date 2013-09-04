package com.home.giraffe.network;

import com.home.giraffe.interfaces.IConnector;
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

    DefaultHttpClient mHttpClient;
    public Connector(){
        mHttpClient = new DefaultHttpClient();
    }

    private String cutSecurityString(String response){
        return response.replaceAll("throw.*;\\s*","");
    }

    private void proceedCookies(Cookie[] cookies){
        CookieStore cookieStore = mHttpClient.getCookieStore();
        for (Cookie cookie : cookies){
            cookieStore.addCookie(cookie);
        }
    }

    private com.home.giraffe.network.HttpResponse proceedResponse(HttpResponse response) throws Exception {
        if(     response.getStatusLine().getStatusCode() != HttpStatus.SC_OK &&
                response.getStatusLine().getStatusCode() != 302  ){
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
        return getRequest(url, new Cookie[0]);
    }

    @Override
    public com.home.giraffe.network.HttpResponse postRequest(String url, String body) throws Exception {
        return postRequest(url, body, new Cookie[0]);
    }

    @Override
    public com.home.giraffe.network.HttpResponse getRequest(String url, Cookie[] cookies) throws Exception {
        proceedCookies(cookies);

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = mHttpClient.execute(httpGet);
        return proceedResponse(response);
    }

    @Override
    public com.home.giraffe.network.HttpResponse postRequest(String url, String body, Cookie[] cookies) throws Exception {
        return postRequest(url, body, cookies, true);
    }

    @Override
    public com.home.giraffe.network.HttpResponse postRequest(String url, String body, Cookie[] cookies, boolean allowRedirect) throws Exception {
        proceedCookies(cookies);

        if(!allowRedirect){
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

        HttpResponse response = mHttpClient.execute(httpPost);
        return proceedResponse(response);
    }
}
