package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Author;

public class GetUserProfileTask extends BaseTask<Author> {

    private String mUserId;

    public GetUserProfileTask(FragmentActivity activity, String userId) {
        super(activity);
        mUserId = userId;
    }

    @Override
    public Author loadInBackground() {
        try {
            return getRequestsManager().getUserInfo(mUserId);
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}
