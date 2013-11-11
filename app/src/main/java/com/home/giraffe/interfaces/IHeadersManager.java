package com.home.giraffe.interfaces;


import org.apache.http.Header;

public interface IHeadersManager {
    Header[] getImportantHeaders(Header[] headers);
}
