package com.home.giraffe.settings;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.inject.Inject;
import com.home.giraffe.Constants;
import com.home.giraffe.events.SettingsChangedEvent;
import com.home.giraffe.events.SettingsClearedEvent;
import com.home.giraffe.interfaces.ISettingsManager;
import de.greenrobot.event.EventBus;

public class SettingsManager implements ISettingsManager {
    private SharedPreferences mSharedPreferences;
    private EventBus mEventBus;

    @Inject
    public SettingsManager(SharedPreferences sharedPreferences, EventBus eventBus){
        mSharedPreferences = sharedPreferences;
        mEventBus = eventBus;
    }

    @Override
    public boolean isLoggedOn() {
        return !TextUtils.isEmpty(getUserToken());
    }

    @Override
    public void setAppVersion(String appVersion) {
        mSharedPreferences.edit().putString(Constants.AppVersionPref, appVersion).commit();
        settingsChanged();
    }

    @Override
    public String getAppVersion() {
        return mSharedPreferences.getString(Constants.AppVersionPref, null);
    }

    @Override
    public void setCommunityUrl(String communityUrl) {
        mSharedPreferences.edit().putString(Constants.CommunityUrlPref, communityUrl).commit();
        settingsChanged();
    }

    @Override
    public String getCommunityUrl() {
        return mSharedPreferences.getString(Constants.CommunityUrlPref, null);
    }

    @Override
    public void setUserId(String userId) {
        mSharedPreferences.edit().putString(Constants.UserIdPref, userId).commit();
        settingsChanged();
    }

    @Override
    public String getUserId() {
        return mSharedPreferences.getString(Constants.UserIdPref, null);
    }

    @Override
    public void setUserDisplayName(String userDisplayName) {
        mSharedPreferences.edit().putString(Constants.UserNamePref, userDisplayName).commit();
        settingsChanged();
    }

    @Override
    public String getUserDisplayName() {
        return mSharedPreferences.getString(Constants.UserNamePref, null);
    }

    @Override
    public void setUserJobTitle(String userJobTitle) {
        mSharedPreferences.edit().putString(Constants.UserJobTitlePref, userJobTitle).commit();
        settingsChanged();
    }

    @Override
    public String getUserJobTitle() {
        return mSharedPreferences.getString(Constants.UserJobTitlePref, null);
    }

    @Override
    public void setUserToken(String userToken) {
        mSharedPreferences.edit().putString(Constants.UserTokenPref, userToken).commit();
        settingsChanged();
    }

    @Override
    public String getUserToken() {
        return mSharedPreferences.getString(Constants.UserTokenPref, null);
    }

    @Override
    public void clear() {
        mSharedPreferences.edit().clear().commit();
        settingsChanged();
        settingsCleared();
    }

    private void settingsCleared() {
        if(mEventBus != null)
            mEventBus.post(new SettingsClearedEvent());
    }

    private void settingsChanged() {
        if(mEventBus != null)
            mEventBus.post(new SettingsChangedEvent());
    }
}
