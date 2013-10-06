package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.events.InboxUnreadCountEvent;
import com.home.giraffe.utils.Utils;

public class GetBadgesCountTask extends BaseTask{
    public GetBadgesCountTask(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public Object loadInBackground() {
        Utils.d("Started GetBadgesCountTask");

        try {
            final int badgeCount = mRequestsManager.getInboxBadgeCount();
            mBus.post(new InboxUnreadCountEvent(badgeCount));
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }
        finally {
            Utils.d("Finished GetBadgesCountTask");
        }

        return null;
    }
}
