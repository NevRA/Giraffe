package com.home.giraffe.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activities {
    public Activities(){
        setItems(Collections.synchronizedList(new ArrayList<BaseJiveObject>()));
    }

    private List<BaseJiveObject> mItems;

    public List<BaseJiveObject> getItems() {
        return mItems;
    }

    public void setItems(List<BaseJiveObject> items) {
        mItems = items;
    }

    public BaseJiveObject findItemById(String id){
        for (BaseJiveObject object : getItems()) {
            if (object.getId().equalsIgnoreCase(id)){
                return object;
            }
        }
        return null;
    }
}
