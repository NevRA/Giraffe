package com.home.giraffe.interfaces;

public interface ISettingsManager {
    boolean isLoggedOn();

    void setCommunityUrl(String communityUrl);
    String getCommunityUrl();

    void setUserName(String userName);
    String getUserName();

    void setUserToken(String userToken);
    String getUserToken();
}
