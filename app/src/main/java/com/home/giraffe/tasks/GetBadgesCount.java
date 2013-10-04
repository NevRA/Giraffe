package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.events.InboxUnreadCountEvent;

public class GetBadgesCount extends BaseTask{
    public GetBadgesCount(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public Object loadInBackground() {
        try {
            final int badgeCount = mRequestsManager.getInboxBadgeCount();
            mBus.post(new InboxUnreadCountEvent(badgeCount));
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }

        return null;
    }
}
