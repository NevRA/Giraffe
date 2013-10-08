package com.home.giraffe.network;

import android.text.TextUtils;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.utils.Utils;
import org.apache.http.HttpEntity;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.HttpEntityWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import roboguice.RoboGuice;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class NetworkUtils {
    private NetworkUtils(){

    }

    @Inject
    ISettingsManager mSettingsManager;

    public static String getTokenFromCookies(Cookie[] cookies){
        for (Cookie cookie : cookies){
            if(!TextUtils.isEmpty(cookie.getValue()) && cookie.getName().equals(Constants.RememberMeCookie)){
                return cookie.getValue();
            }
        }

        return null;
    }

    public static String cleanTags(String html){
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("p");
        for(int i = elements.size() - 1; i >= 0; i --){
            Element el = elements.get(i);
            if(el.html().equals("&nbsp;")){
                el.remove();
            }
        }

        return doc.html();
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
