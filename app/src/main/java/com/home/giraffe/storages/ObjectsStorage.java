package com.home.giraffe.storages;

import com.home.giraffe.objects.BaseObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ObjectsStorage {
    private Map<String, BaseObject> mObjects;

    public ObjectsStorage() {
        mObjects = Collections.synchronizedMap(new HashMap<String, BaseObject>());
    }

    public Map<String, BaseObject> getObjects() {
        return mObjects;
    }

    public void add(BaseObject object) {
        BaseObject orig = get(object.getId());
        if (orig == null) {
            mObjects.put(object.getId(), object);
        }
    }

    public BaseObject get(String id) {
        return getObjects().get(id);
    }
}
