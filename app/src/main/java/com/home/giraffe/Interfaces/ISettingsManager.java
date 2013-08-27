package com.home.giraffe.Interfaces;

import android.R;

public interface ISettingsManager {
    boolean isLoggedOn();

    void setUserName(String userName);
    String getUserName();

    void setUserPassword(String userPassword);
    String getUserPassword();
}
