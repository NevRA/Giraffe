package com.home.giraffe.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.google.inject.Inject;
import com.home.giraffe.events.InboxUnreadCountEvent;
import com.home.giraffe.objects.Inbox;
import com.home.giraffe.tasks.GetInboxTask;
import de.greenrobot.event.EventBus;

public class InboxFragment extends RoboSherlockListFragment implements LoaderManager.LoaderCallbacks<Inbox>{
    @Inject
    EventBus mBus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Inbox> onCreateLoader(int i, Bundle bundle) {
        return new GetInboxTask(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Inbox> inboxLoader, Inbox inbox) {
        if(inbox != null){
            updateView(inbox);
        }
    }

    private void updateView(Inbox inbox) {
        mBus.post(new InboxUnreadCountEvent(inbox.getUnread()));
    }

    @Override
    public void onLoaderReset(Loader<Inbox> inboxLoader) {
    }
}
