package com.home.giraffe.interfaces;

import android.content.Context;
import android.os.Handler;

public interface IUiManager {
    <T> void startActivity(Context context, Class<T> type);
    void showError(Exception e);
    void runOnUi(Handler handler);
}
