package com.home.giraffe.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
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
    public Context getContext() {
        return mContext;
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
    public void showToast(FragmentActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(final FragmentActivity activity, final Exception e) {
        if (activity == null) return;

        MessageDialogFragment dialog = new MessageDialogFragment(getString(R.string.message_dialog_error_title), e.getMessage());
        dialog.show(activity.getSupportFragmentManager(), null);

        Utils.e(e);
    }

    @Override
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
