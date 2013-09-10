package com.home.giraffe.ui;

import android.os.Bundle;
import android.view.View;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;

public class ActionsFragment extends RoboSherlockListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListShown(true);
    }
}
