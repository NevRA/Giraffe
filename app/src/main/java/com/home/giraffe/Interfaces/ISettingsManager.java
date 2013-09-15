package com.home.giraffe.interfaces;

public interface ISettingsManager {
    boolean isLoggedOn();

    void setCommunityUrl(String communityUrl);
    String getCommunityUrl();

    void setUserId(String userId);
    String getUserId();

    void setUserDisplayName(String userDisplayName);
    String getUserDisplayName();

    void setUserJobTitle(String userJobTitle);
    String getUserJobTitle();

    void setUserToken(String userToken);
    String getUserToken();
}
