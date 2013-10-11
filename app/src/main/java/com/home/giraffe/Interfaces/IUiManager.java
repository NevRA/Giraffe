package com.home.giraffe.interfaces;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

public interface IUiManager {
    void startActivity(Context context, Intent intent);

    String getString(int resourceId);
    String[] getStringArray(int resourceId);
    void showError(FragmentActivity activity, Exception e);
    int dpToPx(int dp);
}
