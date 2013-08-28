package com.home.giraffe.settings;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.Interfaces.ISettingsManager;

public class SettingsManager implements ISettingsManager {

    private SharedPreferences mSharedPreferences;

    @Inject
    public SettingsManager(SharedPreferences sharedPreferences){
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public boolean isLoggedOn() {
        return  !TextUtils.isEmpty(getUserName()) &&
                !TextUtils.isEmpty(getUserPassword());
    }

    @Override
    public void setServerAddress(String serverAddress) {
        mSharedPreferences.edit().putString(Constants.ServerAddressPref, serverAddress);
    }

    @Override
    public String getServerAddress() {
        return mSharedPreferences.getString(Constants.ServerAddressPref, null);
    }

    @Override
    public void setUserName(String userName) {
        mSharedPreferences.edit().putString(Constants.UserNamePref, userName);
    }

    @Override
    public String getUserName() {
        return mSharedPreferences.getString(Constants.UserNamePref, null);
    }

    @Override
    public void setUserPassword(String userPassword) {
        mSharedPreferences.edit().putString(Constants.UserNamePref, userPassword);
    }

    @Override
    public String getUserPassword() {
        return mSharedPreferences.getString(Constants.UserPasswordPref, null);
    }
}
