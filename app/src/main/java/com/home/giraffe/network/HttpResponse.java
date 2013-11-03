package com.home.giraffe.network;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

public class HttpResponse {
    private Header[] mHeaders;
    private Cookie[] mCookies;
    private String mBody;

    public HttpResponse(Header[] headers, Cookie[] cookies, String body)
    {
        mHeaders = headers;
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

    public Header[] getHeaders() {
        return mHeaders;
    }

    public void setHeaders(Header[] headers) {
        this.mHeaders = headers;
    }
}
