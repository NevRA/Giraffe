package com.home.giraffe.ui;


import com.home.giraffe.Constants;

public class StreamFragment extends ActivityFragment {
    @Override
    public String getActivityUrl(){
        return mSettingsManager.getCommunityUrl() + Constants.STREAM;
    }
}
