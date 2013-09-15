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
import com.home.giraffe.objects.ActivityItem;
import com.home.giraffe.objects.Actor;
import com.home.giraffe.objects.BaseObject;
import com.home.giraffe.objects.Post;
import com.home.giraffe.storages.ObjectsStorage;
import roboguice.RoboGuice;

import java.util.List;

public class ActivitiesAdapter extends ArrayAdapter<ActivityItem> {
    IImageLoader mImageLoader;
    ObjectsStorage mObjectsStorage;

    private List<ActivityItem> mItems;

    public ActivitiesAdapter(Context context, int textViewResourceId, List<ActivityItem> objects) {
        super(context, textViewResourceId, objects);
        mItems = objects;
        mImageLoader = RoboGuice.getInjector(context).getProvider(IImageLoader.class).get();
        mObjectsStorage = RoboGuice.getInjector(context).getProvider(ObjectsStorage.class).get();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActivityItem item = mItems.get(position);
        switch (item.getType()) {

            case Actor:
                break;
            case Comment:
                break;
            case Discussion:
            case File:
            case Document:
                return getPostView(item);
            case Poll:
                break;
            case Promotion:
                break;
            case Like:
                break;
        }

        return getUnknownView();
    }

    private View getUnknownView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.unknown_object, null);
    }

    private View getProjectView(BaseObject object) {
        return getUnknownView();
    }

    private View getSpaceView(BaseObject object) {
        return getUnknownView();
    }

    private View getFileView(BaseObject object) {
        return getUnknownView();
    }

    private View getPostView(ActivityItem activityItem) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_object, null);

        Post post = (Post)mObjectsStorage.get(activityItem.getId());
        Actor actor = (Actor)mObjectsStorage.get(post.getActorId());

        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        if(/*activityItem.isQuestion() TODO*/true){
            icon.setImageResource(R.drawable.ic_question_discussion);
        }
        else{
            icon.setImageResource(R.drawable.ic_discussion);
        }

        TextView userDisplayName = (TextView) view.findViewById(R.id.userDisplayName);
        userDisplayName.setText(actor.getDisplayName());

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(post.getTitle());

        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(post.getContent());

        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        mImageLoader.DisplayImage(actor.getAvatarUrl(), avatar);

        return view;
    }

    private View getPersonView(BaseObject object) {
        return getUnknownView();
    }

    private View getGroupView(BaseObject object) {
        return getUnknownView();
    }

    private View getInstanceView(BaseObject object) {
        return getUnknownView();
    }

    private View getMessageView(BaseObject object) {
        return getUnknownView();
    }
}
