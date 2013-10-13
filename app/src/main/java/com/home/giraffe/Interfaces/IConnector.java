package com.home.giraffe.interfaces;

import com.home.giraffe.network.HttpResponse;
import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

import java.io.IOException;

public interface IConnector {
    HttpResponse getRequest(String url) throws Exception;
    HttpResponse postRequest(String url, String body) throws Exception;
    HttpResponse postRequest(String url, String body, boolean allowRedirect) throws Exception;
    byte[] getData(String url) throws Exception;
}
