package com.home.giraffe.objects;

public class Promotion extends BaseObject {
    protected Promotion(String id) {
        super(id);
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Promotion;
    }
}
