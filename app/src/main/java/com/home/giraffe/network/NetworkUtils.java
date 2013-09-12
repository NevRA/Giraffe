package com.home.giraffe.network;

import android.text.TextUtils;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.ISettingsManager;
import org.apache.http.cookie.Cookie;

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
       return mSettingsManager.getCommunityUrl() + String.format(Constants.AVATAR, mSettingsManager.getUserId());
    }
}
