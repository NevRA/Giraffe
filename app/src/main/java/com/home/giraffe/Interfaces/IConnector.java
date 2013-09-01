package com.home.giraffe.interfaces;

import com.home.giraffe.network.HttpResponse;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

public interface IConnector {
    public HttpResponse getRequest(String url) throws Exception;
    public HttpResponse postRequest(String url, String body) throws Exception;

    public HttpResponse getRequest(String url, Cookie[] cookies) throws Exception;
    public HttpResponse postRequest(String url, String body, Cookie[] cookies) throws Exception;
    public HttpResponse postRequest(String url, String body, Cookie[] cookies, boolean allowRedirect) throws Exception;
}
