package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveContainer;

public class Discussion extends Post{
    public Discussion(String id) {
        super(id);
    }

    private boolean mIsQuestion;

    public boolean isQuestion() {
        return mIsQuestion;
    }

    public void setIsQuestion(boolean isQuestion) {
        mIsQuestion = isQuestion;
    }

    @Override
    public String getFriendlyName() {
        return "Discussion";
    }

    @Override
    public void fromJiveContainer(JiveContainer jiveContainer) {
        super.fromJiveContainer(jiveContainer);

        setIsQuestion(jiveContainer.getJive().isQuestion());
    }
}
