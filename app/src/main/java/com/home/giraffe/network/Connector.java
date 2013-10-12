package com.home.giraffe.network;

import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.IConnector;
import com.home.giraffe.interfaces.ISettingsManager;
import com.home.giraffe.utils.Utils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Connector implements IConnector {

    @Inject
    ISettingsManager mSettingsManager;

    DefaultHttpClient mHttpClient;

    public Connector() {
        Utils.d("Connector created");

        mHttpClient = getHttpClient();
    }

    private DefaultHttpClient getHttpClient(){
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

        HttpParams params = new BasicHttpParams();
        params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(20));
        params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
        params.setBooleanParameter(HttpConnectionParams.STALE_CONNECTION_CHECK, false);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpConnectionParams.setConnectionTimeout(params, 15 * 1000);
        HttpConnectionParams.setSoTimeout(params, 3 * 60 * 1000);

        ClientConnectionManager manager = new ThreadSafeClientConnManager(params, schemeRegistry);
        return new DefaultHttpClient(manager, params);
    }

    private String cutSecurityString(String response) {
        return response.replaceFirst("throw.*;\\s*", "");
    }

    private com.home.giraffe.network.HttpResponse proceedResponse(HttpResponse response) throws Exception {
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK &&
            response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED &&
            response.getStatusLine().getStatusCode() != HttpStatus.SC_MOVED_TEMPORARILY) {
            throw new Exception(response.getStatusLine().getReasonPhrase());
        }

        HttpEntity entity = getEntityFromResponse(response);

        CookieStore cookieStore = mHttpClient.getCookieStore();
        Cookie[] cookies = cookieStore.getCookies().toArray(new Cookie[cookieStore.getCookies().size()]);
        String body = cutSecurityString(EntityUtils.toString(entity, HTTP.UTF_8));
        return new com.home.giraffe.network.HttpResponse(cookies, body);
    }

    private HttpEntity getEntityFromResponse(HttpResponse response)
    {
        Header[] contentEncodings = response.getHeaders("Content-Encoding");
        if (contentEncodings != null)
        {
            for (Header header : contentEncodings)
            {
                if (header.getValue().equalsIgnoreCase("gzip"))
                {
                    return new NetworkUtils.GzipDecompressingEntity(response.getEntity());
                }
            }
        }

        return response.getEntity();
    }

    @Override
    public com.home.giraffe.network.HttpResponse getRequest(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept-Encoding", "gzip");
        httpGet.addHeader("Accept", "*/*");
        httpGet.addHeader("Cookie", Constants.RememberMeCookie + "=" + mSettingsManager.getUserToken());
        HttpResponse response = mHttpClient.execute(httpGet);
        return proceedResponse(response);
    }

    @Override
    public com.home.giraffe.network.HttpResponse postRequest(String url, String body) throws Exception {
        return postRequest(url, body, true);
    }

    @Override
    public com.home.giraffe.network.HttpResponse postRequest(String url, String body, boolean allowRedirect) throws Exception {
        if (!allowRedirect) {
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
        httpPost.addHeader("Accept-Encoding", "gzip");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader(new BasicHeader("Content-Type",
                mSettingsManager.isLoggedOn() ?
                        "application/json; charset=UTF-8" :
                        "application/x-www-form-urlencoded"));
        httpPost.addHeader("Cookie", Constants.RememberMeCookie + "=" + mSettingsManager.getUserToken());

        HttpResponse response = mHttpClient.execute(httpPost);
        return proceedResponse(response);
    }
}
