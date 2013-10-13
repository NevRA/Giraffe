package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.Constants;
import com.home.giraffe.events.ActionsUnreadCountEvent;
import com.home.giraffe.events.InboxUnreadCountEvent;
import com.home.giraffe.utils.Utils;

public class GetBadgesCountTask extends BaseTaskLoader {
    public GetBadgesCountTask(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public Object loadInBackground() {
        Utils.d("Started GetBadgesCountTask");

        try {
            int badgeCount = mRequestsManager.getInboxBadgeCount();
            Utils.d(String.format("%d unread items in Inbox", badgeCount));

            mBus.post(new InboxUnreadCountEvent(badgeCount));

            badgeCount = mRequestsManager.getJiveContainers(mSettingsManager.getCommunityUrl() + Constants.ACTIONS).getList().size();
            Utils.d(String.format("%d unread items in Actions", badgeCount));

            mBus.post(new ActionsUnreadCountEvent(badgeCount));

        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }
        finally {
            Utils.d("Finished GetBadgesCountTask");
        }

        return null;
    }
}
