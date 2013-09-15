package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Jive.JiveAuthor;

public class GetUserProfileTask extends BaseTask<JiveAuthor> {

    private String mUserId;

    public GetUserProfileTask(FragmentActivity activity, String userId) {
        super(activity);
        mUserId = userId;
    }

    @Override
    public JiveAuthor loadInBackground() {
        try {
            return mRequestsManager.getUserInfo(mUserId);
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }

        return null;
    }
}
