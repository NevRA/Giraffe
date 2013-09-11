package com.home.giraffe.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.home.giraffe.R;
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
        return LayoutInflater.from(getContext()).inflate(R.layout.unknown_object, null);
    }

    private View getProjectView(JiveContainer container) {
        return getUnknownView();
    }

    private View getSpaceView(JiveContainer container) {
        return getUnknownView();
    }

    private View getFileView(JiveContainer container) {
        return getUnknownView();
    }

    private View getDiscussionView(JiveContainer container) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_object, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(container.getTitle());

        return view;
    }

    private View getPersonView(JiveContainer container) {
        return getUnknownView();
    }

    private View getGroupView(JiveContainer container) {
        return getUnknownView();
    }

    private View getInstanceView(JiveContainer container) {
        return getUnknownView();
    }

    private View getMessageView(JiveContainer container) {
        return getUnknownView();
    }
}
