package com.home.giraffe.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import com.google.inject.Inject;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.utils.Utils;

public class UiManager implements IUiManager {
    private Context mContext;

    @Inject
    public UiManager(Context context) {
        mContext = context;
    }

    @Override
    public void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public String getString(int resourceId) {
        return mContext.getString(resourceId);
    }

    @Override
    public String[] getStringArray(int resourceId) {
        return mContext.getResources().getStringArray(resourceId);
    }

    @Override
    public void showError(final FragmentActivity activity, final Exception e) {
        if (activity == null) return;

        MessageDialogFragment dialog = new MessageDialogFragment(getString(R.string.message_dialog_error_title), e.getMessage());
        dialog.show(activity.getSupportFragmentManager(), null);

        Utils.e(e);
    }

    @Override
    public int dpToPx(int dp){
        Resources r = mContext.getResources();
        return  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
