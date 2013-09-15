package com.home.giraffe.network;

import android.text.TextUtils;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.ISettingsManager;
import org.apache.http.HttpEntity;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.HttpEntityWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class NetworkUtils {
    @Inject
    ISettingsManager mSettingsManager;

    public String getTokenFromCookies(Cookie[] cookies){
        for (Cookie cookie : cookies){
            if(!TextUtils.isEmpty(cookie.getValue()) && cookie.getName().equals(Constants.RememberMeCookie)){
                return cookie.getValue();
            }
        }

        return null;
    }

    public String getMyAvatarUrl(){
       return mSettingsManager.getUserId() + "/" + "avatar";
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
