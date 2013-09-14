package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Jive.JiveInbox;

public class GetInboxTask extends BaseTask<JiveInbox> {
    public GetInboxTask(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public JiveInbox loadInBackground() {
        try {
            return getRequestsManager().getInbox();
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}
