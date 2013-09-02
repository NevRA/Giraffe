package com.home.giraffe.network;

import org.apache.http.cookie.Cookie;

public class NetworkUtils {
    public static String getTokenFromCookies(Cookie[] cookies){
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("SPRING_SECURITY_REMEMBER_ME_COOKIE")){
                return cookie.getValue();
            }
        }

        throw new IllegalArgumentException("Token not found");
    }
}
