package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.utils.Utils;

public class GetUserProfileTask extends BaseTask<JiveAuthor> {

    private String mUserId;

    public GetUserProfileTask(FragmentActivity activity, String userId) {
        super(activity);
        mUserId = userId;
    }

    @Override
    public JiveAuthor loadInBackground() {
        Utils.d("Started GetUserProfileTask");

        try {
            return mRequestsManager.getUserInfo(mUserId);
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }
        finally {
            Utils.d("Finished GetUserProfileTask");
        }

        return null;
    }
}
