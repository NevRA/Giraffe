package com.home.giraffe.storages;

import com.home.giraffe.objects.BaseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectsStorage {
    private List<BaseObject> mObjects;

    public ObjectsStorage(){
        mObjects = Collections.synchronizedList(new ArrayList<BaseObject>());
    }

    public List<BaseObject> getObjects(){
        return mObjects;
    }

    public void add(BaseObject object){
        BaseObject orig = get(object.getId());
        if(orig == null){
            mObjects.add(object);
            return;
        }
    }

    public BaseObject get(String id){
        for (BaseObject object : getObjects()) {
            if (object.getId().equalsIgnoreCase(id)){
                return object;
            }
        }
        return null;
    }
}
