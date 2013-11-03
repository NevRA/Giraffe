package com.home.giraffe.network;

import android.text.TextUtils;
import com.home.giraffe.Constants;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.HttpEntityWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class NetworkUtils {
    private NetworkUtils(){
    }

    public static String getLocationFromHeaders(Header[] headers){
        for (Header header : headers){
            if(header.getName().equalsIgnoreCase("Location")){
                return header.getValue();
            }
        }

        return null;
    }

    public static String getTokenFromCookies(Cookie[] cookies){
        for (Cookie cookie : cookies){
            if(!TextUtils.isEmpty(cookie.getValue()) && cookie.getName().equals(Constants.RememberMeCookie)){
                return cookie.getValue();
            }
        }

        return null;
    }

    public static class GzipDecompressingEntity extends HttpEntityWrapper
    {
        public GzipDecompressingEntity(final HttpEntity entity)
        {
            super(entity);
        }

        public InputStream getContent() throws IOException,
                IllegalStateException
        {
            // the wrapped entity's getContent() decides about repeatability
            InputStream wrappedIn = wrappedEntity.getContent();
            return new GZIPInputStream(wrappedIn);
        }

        @Override
        public long getContentLength()
        {
            // length of ungzipped content is not known
            return -1;
        }
    }
}
