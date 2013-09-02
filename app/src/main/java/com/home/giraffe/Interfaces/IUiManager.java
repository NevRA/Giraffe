package com.home.giraffe.interfaces;

import android.content.Context;

public interface IUiManager {
    <T> void startActivity(Context context, Class<T> type);
}
