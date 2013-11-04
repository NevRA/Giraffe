package com.home.giraffe.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.version_history_dialog_title));
        return inflater.inflate(R.layout.history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
