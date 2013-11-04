package com.home.giraffe.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.utils.Utils;
import roboguice.inject.InjectView;

import javax.inject.Inject;
import java.io.IOException;

public class ActionsFragment extends RoboSherlockFragment {
    @Inject
    IUiManager mUiManager;

    @InjectView(R.id.textView)
    TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.not_implemented, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        try {
            mTextView.setText(Html.fromHtml(Utils.getStringFromAssets("not_implemented.html")));
        } catch (IOException e) {
            mUiManager.showError(getActivity(), e);
        }
    }
}
