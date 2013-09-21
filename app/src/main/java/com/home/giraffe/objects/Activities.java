package com.home.giraffe.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activities {
    private List<ActivityItem> mActivityItems;
    private String mCurrent;
    private String mNext;
    private String mPrevious;

    public Activities() {
        mActivityItems = Collections.synchronizedList(new ArrayList<ActivityItem>());
    }

    public String getPrevious() {
        return mPrevious;
    }

    public void setPrevious(String previous) {
        mPrevious = previous;
    }

    public String getNext() {
        return mNext;
    }

    public void setNext(String next) {
        mNext = next;
    }

    public String getCurrent() {
        return mCurrent;
    }

    public void setCurrent(String current) {
        mCurrent = current;
    }

    public List<ActivityItem> getActivities() {
        return mActivityItems;
    }

    public void addActivity(ActivityItem activityItem) {
        mActivityItems.add(activityItem);
    }

    public void addActivities(List<ActivityItem> activityItems) {
        addActivities(mActivityItems.size(), activityItems);
    }

    public void addActivities(int pos, List<ActivityItem> activityItems) {
        mActivityItems.addAll(pos, activityItems);
    }

    public ActivityItem getActivity(String id, BaseObjectTypes type) {
        for (ActivityItem activity : getActivities()) {
            if (activity.getId().equalsIgnoreCase(id) &&
                    activity.getType() == type) {
                return activity;
            }
        }
        return null;
    }
}
