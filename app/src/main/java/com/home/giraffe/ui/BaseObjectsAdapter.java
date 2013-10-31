package com.home.giraffe.ui;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.home.giraffe.R;
import com.home.giraffe.interfaces.IImageLoader;
import com.home.giraffe.interfaces.IUiManager;
import com.home.giraffe.objects.Actor;
import com.home.giraffe.objects.BaseObject;
import com.home.giraffe.objects.Comment;
import com.home.giraffe.objects.Post;
import com.home.giraffe.objects.socialnews.*;
import com.home.giraffe.storages.ObjectsStorage;
import roboguice.RoboGuice;

import java.util.ArrayList;
import java.util.List;

public class BaseObjectsAdapter extends ArrayAdapter<BaseObject> {
    IUiManager mUiManager;
    IImageLoader mImageLoader;
    ObjectsStorage mObjectsStorage;

    private List<BaseObject> mItems;

    public BaseObjectsAdapter(Context context, List<BaseObject> objects) {
        super(context, 0, objects);
        mItems = objects;
        mImageLoader = RoboGuice.getInjector(context).getProvider(IImageLoader.class).get();
        mObjectsStorage = RoboGuice.getInjector(context).getProvider(ObjectsStorage.class).get();
        mUiManager = RoboGuice.getInjector(context).getProvider(IUiManager.class).get();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseObject item = mItems.get(position);
        if (item instanceof Post) {
            return getPostView(item);
        }
        if (item instanceof SocialNews) {
            return getSocialNewsView(item);
        }

        return getUnknownView();
    }

    private View getSocialNewsView(BaseObject activityItem) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.social_news_parent_preview, null);

        RelativeLayout new_layout = (RelativeLayout) view.findViewById(R.id.news_layout);

        SocialNews socialNews = (SocialNews) activityItem;

        TextView postType = (TextView) view.findViewById(R.id.postType);
        postType.setText(mUiManager.getString(R.string.social_news_label).toUpperCase());

        final LinearLayout news = (LinearLayout) view.findViewById(R.id.news);
        final ImageView arrow = (ImageView) view.findViewById(R.id.imageArrow);

        TextView newsUpdatesLabel = (TextView)view.findViewById(R.id.new_news_label);
        newsUpdatesLabel.setText(
                Integer.toString(socialNews.getNews().size()) +
                        " " +
                        mUiManager.getString(R.string.news_updates_label));

        List<String> avatars = new ArrayList<String>();
        for (BaseSocialNewsItem newsItem : socialNews.getNews()) {
            LinearLayout newsItemView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.social_news_preview, news, false);

            ImageView avatar = (ImageView) newsItemView.findViewById(R.id.avatar);
            String avatarUrl = newsItem.getActor().getAvatarUrl();
            avatars.add(avatarUrl);
            mImageLoader.DisplayImage(avatarUrl, avatar);

            TextView content = (TextView) newsItemView.findViewById(R.id.content);

            if (newsItem instanceof LevelSocialNewsItem) {
                LevelSocialNewsItem levelNews = (LevelSocialNewsItem) newsItem;
                content.setText(Html.fromHtml("<b>" + levelNews.getActor().getDisplayName() + "</b>" + " " +
                        mUiManager.getString(R.string.achieved_level) + " " +
                        levelNews.getLevel()));
            }

            if (newsItem instanceof JoinedSocialNewsItem) {
                JoinedSocialNewsItem joinedNews = (JoinedSocialNewsItem) newsItem;
                content.setText(Html.fromHtml("<b>" + joinedNews.getActor().getDisplayName() + "</b>" + " " +
                        mUiManager.getString(R.string.social_joined) + " " +
                        joinedNews.getGroup()));
            }

            if (newsItem instanceof CreatedSocialNewsItem) {
                CreatedSocialNewsItem joinedNews = (CreatedSocialNewsItem) newsItem;
                content.setText(Html.fromHtml("<b>" + joinedNews.getActor().getDisplayName() + "</b>" + " " +
                        mUiManager.getString(R.string.social_created) + " " +
                        joinedNews.getGroup()));
            }

            news.addView(newsItemView);
        }

        Gallery ga = (Gallery) view.findViewById(R.id.gallery);
        ga.setAdapter(new ImageAdapter(getContext(), avatars));

        new_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (news.getVisibility() == View.VISIBLE) {
                    news.setVisibility(View.GONE);
                    arrow.setBackgroundResource(R.drawable.ic_arrow_down);
                } else {
                    news.setVisibility(View.VISIBLE);
                    arrow.setBackgroundResource(R.drawable.ic_arrow_up);
                }
            }
        });

        return view;
    }

    private View getUnknownView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.unknown_object, null);
    }

    private View getPostView(BaseObject activityItem) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.post_preview, null);

        Post post = (Post) activityItem;
        Actor actor = post.getActor();

        TextView postType = (TextView) view.findViewById(R.id.postType);
        postType.setText(post.getFriendlyName().toUpperCase());

        TextView postUpdatedTime = (TextView) view.findViewById(R.id.time);
        postUpdatedTime.setText(DateUtils.getRelativeTimeSpanString(post.getUpdatedTime()).toString());

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

        if (!post.getComments().isEmpty())
            addComments(post, view);

        return view;
    }

    private void addComments(Post post, View postView) {
        RelativeLayout comments_layout = (RelativeLayout) postView.findViewById(R.id.comments_layout);
        TextView newCommentsLabel = (TextView) postView.findViewById(R.id.new_comments_label);
        final LinearLayout comments = (LinearLayout) postView.findViewById(R.id.comments);
        final ImageView arrow = (ImageView) postView.findViewById(R.id.imageArrow);

        for (Comment comment : post.getComments()) {
            Actor actor = comment.getActor();
            LinearLayout commentView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.comment_preview, comments, false);

            TextView content = (TextView) commentView.findViewById(R.id.content);
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
