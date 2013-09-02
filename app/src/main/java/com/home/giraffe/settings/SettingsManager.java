package com.home.giraffe.settings;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.interfaces.ISettingsManager;

public class SettingsManager implements ISettingsManager {

    private SharedPreferences mSharedPreferences;

    @Inject
    public SettingsManager(SharedPreferences sharedPreferences){
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public boolean isLoggedOn() {
        return !TextUtils.isEmpty(getUserToken());
    }

    @Override
    public void setCommunityUrl(String communityUrl) {
        mSharedPreferences.edit().putString(Constants.CommunityUrl, communityUrl).commit();
    }

    @Override
    public String getCommunityUrl() {
        return mSharedPreferences.getString(Constants.CommunityUrl, null);
    }

    @Override
    public void setUserName(String userName) {
        mSharedPreferences.edit().putString(Constants.UserNamePref, userName).commit();
    }

    @Override
    public String getUserName() {
        return mSharedPreferences.getString(Constants.UserNamePref, null);
    }

    @Override
    public void setUserToken(String userToken) {
        mSharedPreferences.edit().putString(Constants.UserTokenPref, userToken).commit();
    }

    @Override
    public String getUserToken() {
        return mSharedPreferences.getString(Constants.UserTokenPref, null);
    }
}
