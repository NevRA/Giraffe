package com.home.giraffe.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

public interface IUiManager {
    <T> void startActivity(Class<T> type);
    String getString(int resourceId);
    void showError(FragmentActivity activity, Exception e);
}
