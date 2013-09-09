package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Inbox;

public class GetInboxTask extends BaseTask<Inbox> {
    public GetInboxTask(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public Inbox loadInBackground() {
        try {
            return getRequestsManager().getInbox();
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}
