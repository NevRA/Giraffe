package com.home.giraffe.interfaces;

import com.home.giraffe.network.HttpResponse;

public interface IConnector {
    HttpResponse getRequest(String url) throws Exception;
    HttpResponse postRequest(String url, String body) throws Exception;
    byte[] getData(String url) throws Exception;
}
