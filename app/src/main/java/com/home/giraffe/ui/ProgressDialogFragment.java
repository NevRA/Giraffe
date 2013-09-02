package com.home.giraffe.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.home.giraffe.R;

public class ProgressDialogFragment extends DialogFragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                getActivity().getString(R.string.loading_in_progress), true);

        dialog.setCancelable(false);

        return dialog;
    }

}