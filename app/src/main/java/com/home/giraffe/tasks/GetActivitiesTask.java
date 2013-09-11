package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Activities;

public class GetActivitiesTask extends BaseTask<Activities> {
    public GetActivitiesTask(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public Activities loadInBackground() {
        try {
            return getRequestsManager().getActivities();
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}
