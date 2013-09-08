package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.Person;

public class UserProfileTask extends BaseTask<Person> {

    private String mUserId;

    public UserProfileTask(FragmentActivity activity, String userId) {
        super(activity);
        mUserId = userId;
    }

    @Override
    public Person loadInBackground() {
        try {
            return getRequestsManager().getUserInfo(mUserId);
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return null;
    }
}
