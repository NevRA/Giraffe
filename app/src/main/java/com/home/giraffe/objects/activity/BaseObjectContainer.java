package com.home.giraffe.objects.activity;

import com.home.giraffe.objects.BaseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseObjectContainer {
    private List<BaseObject> mActivityItems;
    private String mCurrent;
    private String mNext;
    private String mPrevious;

    public BaseObjectContainer() {
        mActivityItems = Collections.synchronizedList(new ArrayList<BaseObject>());
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

    public List<BaseObject> getActivities() {
        return mActivityItems;
    }

    public void addActivity(BaseObject activityItem) {
        mActivityItems.add(activityItem);
    }

    public void addActivities(List<BaseObject> activityItems) {
        addActivities(mActivityItems.size(), activityItems);
    }

    public void addActivities(int pos, List<BaseObject> activityItems) {
        mActivityItems.addAll(pos, activityItems);
    }

    public BaseObject getActivity(String id) {
        for (BaseObject activity : getActivities()) {
            if (activity.getId().equalsIgnoreCase(id)){
                return activity;
            }
        }
        return null;
    }
}
