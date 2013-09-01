package com.home.giraffe.network;

import org.apache.http.cookie.Cookie;

public class HttpResponse {
    private Cookie[] mCookies;
    private String mBody;

    public HttpResponse(Cookie[] cookies, String body)
    {
        mCookies = cookies;
        mBody = body;
    }

    public Cookie[] getCookies() {
        return mCookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.mCookies = cookies;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        this.mBody = body;
    }
}
