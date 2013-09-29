package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.Constants;
import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.*;
import com.home.giraffe.objects.activity.Activities;
import com.home.giraffe.objects.socialnews.JoinedSocialNewsItem;
import com.home.giraffe.objects.socialnews.LevelSocialNewsItem;
import com.home.giraffe.objects.socialnews.SocialNews;
import com.home.giraffe.utils.Utils;

import java.text.ParseException;

public class GetActivitiesTask extends BaseTask<Activities> {
    private Activities mActivities;
    private String mUrl;


    public GetActivitiesTask(FragmentActivity activity, String url) {
        super(activity);
        mUrl = url;
        mActivities = new Activities();
        mActivities.setCurrent(url);
    }

    @Override
    public Activities loadInBackground() {
        try {
            JiveActivities jiveActivities = mRequestsManager.getActivities(mUrl);
            if(jiveActivities.getList().isEmpty()){
                return mActivities;
            }
            mActivities.setNext(jiveActivities.getLinks().getNext());
            mActivities.setPrevious(jiveActivities.getLinks().getPrevious());

            for (JiveContainer jiveContainer : jiveActivities.getList()) {
                processJiveContainer(jiveContainer);
            }
        } catch (Exception e) {
            mUiManager.showError(getActivity(), e);
        }

        return mActivities;
    }

    private void processJiveContainer(JiveContainer jiveContainer) throws Exception {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveVerbTypes jiveVerbTypes = jiveContainer.getVerbType();
        JiveObject jiveObject = jiveContainer.getObject();

        if(     jiveObject == null ||
                jiveVerbTypes == JiveVerbTypes.JiveUnsupported ||
                jiveObject.getType() == JiveTypes.Unsupported)
            return; // for some group updates etc.

        switch (jiveObject.getType()) {
            case JiveMessage:
                break;
            case JiveComment:
                break;
            case JiveDiscussion:
                break;
            case JiveDocument:
                break;
            case JiveFile:
                break;
            case JivePost:
                break;
            case JivePoll:
                break;
            case JiveLevel:
                break;
            case JiveGroup:
                break;
            case JiveProject:
                break;

            case Unsupported:
            case JiveSpace:
            case JiveInstance:
            case JivePerson:
            case JiveTask:
                return;
        }

        mObjectsStorage.add(new Actor(jiveActor));

        if (jiveVerbTypes == JiveVerbTypes.JivePromoted) {
            processJivePromotion(jiveContainer);
        }else if (jiveVerbTypes == JiveVerbTypes.JiveJoined) {
            processJiveJoined(jiveContainer);
        }else if (jiveVerbTypes == JiveVerbTypes.JiveLiked) {
            processJiveLike(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveCompleted) {
            processJiveTask(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveCreated) {
            processJivePost(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveReplied ||
                jiveVerbTypes == JiveVerbTypes.JiveCommented) {
            processJiveComment(jiveContainer);
        }
    }

    private void processJiveComment(JiveContainer jiveContainer) throws Exception {
        JiveObject jiveParent = jiveContainer.getJive().getParent();
        Post post = (Post) mObjectsStorage.get(jiveParent.getId());

        if (post == null) {
            JivePost jivePost = mRequestsManager.getPost(jiveParent.getId());
            post = Utils.getPostFromObjectType(jivePost.getType());
            post.fromJivePost(jivePost);
        }

        Comment comment = new Comment(jiveContainer);
        post.addComment(comment);

        addObjectToActivities(post);
    }

    private void processJivePost(JiveContainer jiveContainer) throws ParseException {
        Post post = Utils.getPostFromObjectType(jiveContainer.getObject().getType());
        post.fromJiveContainer(jiveContainer);
        addObjectToActivities(post);
    }

    private void addObjectToStorage(BaseObject object){
        mObjectsStorage.add(object);
    }

    private void addObjectToActivities(BaseObject object){
        addObjectToStorage(object);

        BaseObject activityItem = mActivities.getActivity(object.getId());
        if (activityItem == null) {
            mActivities.addActivity(object);
        }
    }

    private void processJiveTask(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void processJiveLike(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void processJiveJoined(JiveContainer jiveContainer) {
        JoinedSocialNewsItem joinedNews = new JoinedSocialNewsItem(jiveContainer);

        SocialNews socialNews = (SocialNews)mActivities.getActivity(Constants.SocialNewsId);
        if(socialNews == null){
            socialNews = new SocialNews();
            mActivities.addActivity(socialNews);
        }

        socialNews.addNews(joinedNews);
    }

    private void processJivePromotion(JiveContainer jiveContainer) {
        LevelSocialNewsItem levelNews = new LevelSocialNewsItem(jiveContainer);

        SocialNews socialNews = (SocialNews)mActivities.getActivity(Constants.SocialNewsId);
        if(socialNews == null){
            socialNews = new SocialNews();
            mActivities.addActivity(socialNews);
        }

        socialNews.addNews(levelNews);
    }
}
