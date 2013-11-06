package com.home.giraffe.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.utils.Utils;
import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

import javax.inject.Inject;
import java.io.IOException;

public class HistoryDialog extends RoboDialogFragment {

    @Inject
    IUiManager mUiManager;

    @InjectView(R.id.history)
    TextView mHistoryView;

    @InjectView(R.id.ok)
    Button mOkButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.version_history_dialog_title));
        return inflater.inflate(R.layout.history, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        addHistory();
    }

    private void addHistory() {
        mHistoryView.setMovementMethod(LinkMovementMethod.getInstance());
        try {
            mHistoryView.setText(Html.fromHtml(Utils.getStringFromAssets("history.html")));
        } catch (IOException e) {
            mUiManager.showError(getActivity(), e);
        }
    }
}
