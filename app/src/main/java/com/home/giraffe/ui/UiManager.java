package com.home.giraffe.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import com.google.inject.Inject;
import com.home.giraffe.interfaces.IUiManager;

public class UiManager implements IUiManager {

    private Context mContext;

    @Inject
    public UiManager(Context context){
        mContext = context;
    }

    @Override
    public <T> void startActivity(Context context, Class<T> type) {
        context.startActivity(new Intent(context, type));
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void runOnUi(Handler handler) {

    }
}
