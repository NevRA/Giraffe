package com.home.giraffe.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.home.giraffe.objects.JiveContainer;

import java.util.List;

public class JiveContainerAdapter extends ArrayAdapter<JiveContainer> {

    private List<JiveContainer> mItems;

    public JiveContainerAdapter(Context context, int textViewResourceId, List<JiveContainer> objects) {
        super(context, textViewResourceId, objects);
        mItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JiveContainer container = mItems.get(position);
        switch (container.getType()) {
            case JiveMessage:
                return getMessageView(container);
            case JiveInstance:
                return getInstanceView(container);
            case JiveGroup:
                return getGroupView(container);
            case JivePerson:
                return getPersonView(container);
            case JiveDiscussion:
                return getDiscussionView(container);
            case JiveFile:
                return getFileView(container);
            case JiveSpace:
                return getSpaceView(container);
            case JiveProject:
                return getProjectView(container);
            default:
                return getUnknownView();
        }
    }

    private View getUnknownView() {
        return null;
    }

    private View getProjectView(JiveContainer container) {
        return null;
    }

    private View getSpaceView(JiveContainer container) {
        return null;
    }

    private View getFileView(JiveContainer container) {
        return null;
    }

    private View getDiscussionView(JiveContainer container) {
        return null;
    }

    private View getPersonView(JiveContainer container) {
        return null;
    }

    private View getGroupView(JiveContainer container) {
        return null;
    }

    private View getInstanceView(JiveContainer container) {
        return null;
    }

    private View getMessageView(JiveContainer container) {
        return null;
    }
}
