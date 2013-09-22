package com.home.giraffe.ui;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.home.giraffe.R;
import com.home.giraffe.Utils;
import com.home.giraffe.interfaces.IImageLoader;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.*;
import com.home.giraffe.storages.ObjectsStorage;
import roboguice.RoboGuice;

import java.util.List;

public class ActivitiesAdapter extends ArrayAdapter<BaseObject> {
    IUiManager mUiManager;
    IImageLoader mImageLoader;
    Utils mUtils;
    ObjectsStorage mObjectsStorage;

    private List<BaseObject> mItems;

    public ActivitiesAdapter(Context context, int textViewResourceId, List<BaseObject> objects) {
        super(context, textViewResourceId, objects);
        mItems = objects;
        mImageLoader = RoboGuice.getInjector(context).getProvider(IImageLoader.class).get();
        mObjectsStorage = RoboGuice.getInjector(context).getProvider(ObjectsStorage.class).get();
        mUiManager = RoboGuice.getInjector(context).getProvider(IUiManager.class).get();
        mUtils = RoboGuice.getInjector(context).getProvider(Utils.class).get();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseObject item = mItems.get(position);
        if(item instanceof Post){
            return getPostView(item);
        }
        return getUnknownView();
    }

    private View getUnknownView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.unknown_object, null);
    }

    private View getPostView(BaseObject activityItem) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.post_object, null);

        Post post = (Post)activityItem;
        Actor actor = post.getActor();

        TextView postType = (TextView) view.findViewById(R.id.postType);
        postType.setText(post.getFriendlyName().toUpperCase());

        TextView userDisplayName = (TextView) view.findViewById(R.id.userDisplayName);
        userDisplayName.setText(actor.getDisplayName());

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(post.getTitle());

        TextView content = (TextView) view.findViewById(R.id.content);
        content.setText(post.getContent());

        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        mImageLoader.DisplayImage(actor.getAvatarUrl(), avatar);

        TextView replies = (TextView) view.findViewById(R.id.replies);
        replies.setText(Integer.toString(post.getReplyCount()));

        TextView likes = (TextView) view.findViewById(R.id.likes);
        likes.setText(Integer.toString(post.getLikeCount()));

        if(!post.getComments().isEmpty())
            addComments(post, view);

        return view;
    }

    private void addComments(Post post, View postView) {
        RelativeLayout comments_layout = (RelativeLayout)postView.findViewById(R.id.comments_layout);
        TextView newCommentsLabel = (TextView)postView.findViewById(R.id.new_comments_label);
        final LinearLayout comments = (LinearLayout)postView.findViewById(R.id.comments);
        final ImageView arrow = (ImageView)postView.findViewById(R.id.imageArrow);

        for (Comment comment : post.getComments()){
            Actor actor = comment.getActor();
            LinearLayout commentView = (LinearLayout)LayoutInflater.from(getContext()).inflate(R.layout.comment_object, comments, false);

            TextView content = (TextView)commentView.findViewById(R.id.content);
            content.setText(Html.fromHtml("<b>" + actor.getDisplayName() + "</b>" + " " + comment.getContent()));

            ImageView avatar = (ImageView) commentView.findViewById(R.id.avatar);
            mImageLoader.DisplayImage(actor.getAvatarUrl(), avatar);

            comments.addView(commentView);
        }

        int commentsCount = post.getComments().size();
        newCommentsLabel.setText(
                Integer.toString(commentsCount) +
                " " +
                mUiManager.getString(commentsCount == 1 ?
                        R.string.new_comment_label :
                        R.string.new_comments_label));

        comments_layout.setVisibility(View.VISIBLE);
        comments_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comments.getVisibility() == View.VISIBLE) {
                    comments.setVisibility(View.GONE);
                    arrow.setBackgroundResource(R.drawable.ic_arrow_down);
                } else {
                    comments.setVisibility(View.VISIBLE);
                    arrow.setBackgroundResource(R.drawable.ic_arrow_up);
                }
            }
        });
    }
}
