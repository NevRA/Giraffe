package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.*;

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

        if(jiveObject == null)
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
            case Unknown:
            case JiveSpace:
            case JiveProject:
            case JiveInstance:
            case JiveGroup:
            case JivePerson:
            case JiveTask:
                return;

        }

        mObjectsStorage.add(new Actor(jiveActor));

        if (jiveVerbTypes == JiveVerbTypes.JivePromoted) {
            processJivePromotion(jiveContainer);
        } else if (jiveVerbTypes == JiveVerbTypes.JiveLiked) {
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
            post = mUtils.getPostFromObjectType(jivePost.getType());
            post.fromJivePost(jivePost);
        }

        Comment comment = new Comment(jiveContainer);
        post.addComment(comment);

        addObjectToActivities(post);
    }

    private void processJivePost(JiveContainer jiveContainer) {
        Post post = mUtils.getPostFromObjectType(jiveContainer.getObject().getType());
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

    private void processJivePromotion(JiveContainer jiveContainer) {
        Promotion promotion = new Promotion(jiveContainer);

        // TODO
    }
}
