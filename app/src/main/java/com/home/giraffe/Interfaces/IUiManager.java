package com.home.giraffe.interfaces;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public interface IUiManager {
    void startActivity(Context context, Intent intent);

    Context getContext();
    String getString(int resourceId);
    String[] getStringArray(int resourceId);
    void showToast(FragmentActivity activity, String message);
    void showError(FragmentActivity activity, Exception e);
    int dpToPx(int dp);
    void hideKeyboard(View view);
}
