package com.home.giraffe.ui;

import android.os.Bundle;
import android.view.View;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;

public class OverviewFragment extends RoboSherlockListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

        setListShown(true);
    }
}
