package com.home.giraffe.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.google.inject.Inject;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IUiManager;

public class UiManager implements IUiManager {
    private Context mContext;

    @Inject
    public UiManager(Context context){
        mContext = context;
    }

    @Override
    public <T> void startActivity(final Class<T> type) {
        Intent intent = new Intent(mContext, type);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public void startActivityFromIntent(Intent intent) {
        mContext.startActivity(intent);
    }

    @Override
    public String getString(int resourceId) {
        return mContext.getString(resourceId);
    }

    @Override
    public void showError(final FragmentActivity activity, final Exception e) {
        if(activity == null) return;

        MessageDialogFragment dialog = new MessageDialogFragment(getString(R.string.message_dialog_error_title), e.getMessage());
        dialog.show(activity.getSupportFragmentManager(), null);
    }
}
