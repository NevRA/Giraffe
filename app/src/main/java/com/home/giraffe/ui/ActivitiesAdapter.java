package com.home.giraffe.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IImageLoader;
import com.home.giraffe.objects.BaseJiveObject;
import com.home.giraffe.objects.Discussion;
import roboguice.RoboGuice;

import java.util.List;

public class ActivitiesAdapter extends ArrayAdapter<BaseJiveObject> {
    IImageLoader mImageLoader;

    private List<BaseJiveObject> mObjects;

    public ActivitiesAdapter(Context context, int textViewResourceId, List<BaseJiveObject> objects) {
        super(context, textViewResourceId, objects);
        mObjects = objects;
        mImageLoader = RoboGuice.getInjector(context).getProvider(IImageLoader.class).get();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseJiveObject object = mObjects.get(position);
        switch (object.getType()) {
            case Message:
                return getMessageView(object);
            case Instance:
                return getInstanceView(object);
            case Group:
                return getGroupView(object);
            case Person:
                return getPersonView(object);
            case Discussion:
                return getDiscussionView((Discussion)object);
            case File:
                return getFileView(object);
            case Space:
                return getSpaceView(object);
            case Project:
                return getProjectView(object);
            default:
                return getUnknownView();
        }
    }

    private View getUnknownView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.unknown_object, null);
    }

    private View getProjectView(BaseJiveObject object) {
        return getUnknownView();
    }

    private View getSpaceView(BaseJiveObject object) {
        return getUnknownView();
    }

    private View getFileView(BaseJiveObject object) {
        return getUnknownView();
    }

    private View getDiscussionView(Discussion discussion) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_object, null);

        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        if(discussion.isQuestion()){
            icon.setImageResource(R.drawable.ic_question_discussion);
        }
        else{
            icon.setImageResource(R.drawable.ic_discussion);
        }

        TextView userDisplayName = (TextView) view.findViewById(R.id.userDisplayName);
        userDisplayName.setText(discussion.getAuthor().getDisplayName());

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(discussion.getTitle());

        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(discussion.getContent());

        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        mImageLoader.DisplayImage(discussion.getAuthor().getAvatarUrl(), avatar);

        return view;
    }

    private View getPersonView(BaseJiveObject object) {
        return getUnknownView();
    }

    private View getGroupView(BaseJiveObject object) {
        return getUnknownView();
    }

    private View getInstanceView(BaseJiveObject object) {
        return getUnknownView();
    }

    private View getMessageView(BaseJiveObject object) {
        return getUnknownView();
    }
}
