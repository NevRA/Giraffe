package com.home.giraffe.ui;

import android.content.Context;
import android.content.Intent;
import com.home.giraffe.interfaces.IUiManager;

public class UiManager implements IUiManager {
    @Override
    public <T> void startActivity(Context context, Class<T> type) {
        context.startActivity(new Intent(context, type));
    }
}
