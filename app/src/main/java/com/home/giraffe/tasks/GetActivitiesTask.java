package com.home.giraffe.tasks;

import android.support.v4.app.FragmentActivity;
import com.home.giraffe.objects.*;
import com.home.giraffe.objects.Jive.*;
import com.home.giraffe.storages.ObjectsStorage;

public class GetActivitiesTask extends BaseTask<Activities> {
    private Activities mActivities;

    public GetActivitiesTask(FragmentActivity activity) {
        super(activity);
        mActivities = new Activities();
    }

    @Override
    public Activities loadInBackground() {
        try {

            ObjectsStorage storage = getObjectsStorage();

            JiveActivities jiveActivities = getRequestsManager().getActivities();
            for (JiveContainer jiveContainer : jiveActivities.getList()){
                processJiveContainer(jiveContainer);
            }
        } catch (Exception e) {
            getUiManager().showError(getActivity(), e);
        }

        return mActivities;
    }

    private void processJiveContainer(JiveContainer jiveContainer) throws Exception {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveVerbTypes jiveVerbTypes = jiveContainer.getVerbType();

        getObjectsStorage().add(Actor.fromJiveActor(jiveActor));

        if(jiveVerbTypes == JiveVerbTypes.JivePromoted){
            processJivePromotion(jiveContainer);
        }

        else if(jiveVerbTypes == JiveVerbTypes.JiveLiked){
            processJiveLike(jiveContainer);
        }

        else if(jiveVerbTypes == JiveVerbTypes.JiveCompleted){
            processJiveTask(jiveContainer);
        }

        else if(jiveVerbTypes == JiveVerbTypes.JiveCreated){
            processJivePost(jiveContainer);
        }

        else if(jiveVerbTypes == JiveVerbTypes.JiveReplied ||
                jiveVerbTypes == JiveVerbTypes.JiveCommented){
            processJiveComment(jiveContainer);
        }

//        switch (jiveType) {
//            case Unknown:
//                break;
//            case JiveMessage:
//            case JiveComment:
//                ActivityItem item = mActivities.getActivity(jiveParent.getId(), jiveParent.getType());
//                if(object == null){
//                    JivePost jivePost = mRequestsManager.getPost(parent.getId());
//                    if(jivePost.getType() == JiveTypes.JiveDiscussion){
//                        object = Post.fromJivePost(parent.getId(), jivePost);
//                        activities.getItems().add(object);
//                    }
//                    else{
//                    }
//                }
//                break;
    }

    private void processJiveComment(JiveContainer jiveContainer) throws Exception {
        JiveObject jiveParent = jiveContainer.getJive().getParent();
        Post post = (Post)getObjectsStorage().get(jiveParent.getId());

        if(post == null){
            JivePost jivePost = mRequestsManager.getPost(jiveParent.getId());
            Actor actor = (Actor)getObjectsStorage().get(jiveParent.getId());
            if(actor == null){
                JiveAuthor jiveAuthor = mRequestsManager.getUserInfo(jivePost.getAuthor().getId());
                getObjectsStorage().add(Actor.fromJiveAuthor(jiveAuthor));
            }

            switch (jiveParent.getType()) {
                case JiveDiscussion:
                    post = new Discussion(jiveParent.getId());
                    break;
                case JiveDocument:
                    post = new Document(jiveParent.getId());
                    break;
                case JiveFile:
                    post = new File(jiveParent.getId());
                    break;
                case JivePoll:
                    post = new Poll(jiveParent.getId());
                    break;
                case JivePost:
                    post = new Post(jiveParent.getId());
                    break;
                default:
                    return;
            }

            post.fromJivePost(jivePost);
        }

        getObjectsStorage().add(post);

        ActivityItem activityItem = mActivities.getActivity(post.getId(), post.getType());
        if(activityItem == null){
            activityItem = new ActivityItem(post.getId(), post.getType());
            mActivities.addActivity(activityItem);
        }
    }

    private void processJivePost(JiveContainer jiveContainer) {
        JiveObject object = jiveContainer.getObject();
        Post post;
        switch (object.getType()) {
            case JiveDiscussion:
                post = new Discussion(object.getId());
                break;
            case JiveDocument:
                post = new Document(object.getId());
                break;
            case JiveFile:
                post = new File(object.getId());
                break;
            case JivePoll:
                post = new Poll(object.getId());
                break;
            case JivePost:
                post = new Post(object.getId());
                break;
            default:
                return;
        }

        post.fromJiveContainer(jiveContainer);

        getObjectsStorage().add(post);

        ActivityItem activityItem = mActivities.getActivity(post.getId(), post.getType());
        if(activityItem == null){
            activityItem = new ActivityItem(post.getId(), post.getType());
            mActivities.addActivity(activityItem);
        }
    }

    private void processJiveTask(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void processJiveLike(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void processJivePromotion(JiveContainer jiveContainer) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
