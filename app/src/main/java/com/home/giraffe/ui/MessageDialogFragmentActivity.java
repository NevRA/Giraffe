package com.home.giraffe.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.home.giraffe.R;

public class MessageDialogFragmentActivity extends RoboSherlockFragmentActivity {
    public class MessageDialogFragment extends DialogFragment {
        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Message")
                    .setTitle("title");

            AlertDialog dialog = builder.create();
            dialog.setCancelable(true);
            dialog.show();

            return dialog;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MessageDialogFragment dialog = new MessageDialogFragment();
        dialog.show(getSupportFragmentManager(), null);

        dialog.onCancel(new DialogInterface() {
            @Override
            public void cancel() {
                finish();
            }

            @Override
            public void dismiss() {
                finish();
            }
        });
    }
}
