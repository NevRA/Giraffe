package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Discussion extends BaseJiveObject {
    public Discussion(String id){
        super(id, BaseJiveObjectTypes.Discussion);
        setComment(Collections.synchronizedList(new ArrayList<Comment>()));
    }

    private boolean mIsQuestion;
    private List<Comment> mComments;

    public List<Comment> getComment() {
        return mComments;
    }

    public void setComment(List<Comment> comments) {
        mComments = comments;
    }

    public boolean isQuestion() {
        return mIsQuestion;
    }

    public void setIsQuestion(boolean isQuestion) {
        mIsQuestion = isQuestion;
    }

    public static Discussion fromJiveContainer(JiveContainer jiveContainer){
        Jive jive = jiveContainer.getJive();
        JiveActor jiveActor = jiveContainer.getActor();
        JiveObject jiveObject = jiveContainer.getObject();

        Discussion discussion = new Discussion(jiveObject.getId());
        discussion.setIsQuestion(jive.isQuestion());
        discussion.setTitle(jiveContainer.getTitle());
        discussion.setContent(jiveObject.getSummary());
        discussion.setAuthor(Author.fromJiveActor(jiveActor));

        return discussion;
    }

    public static Discussion fromJivePost(String id, JivePost jivePost){
        JiveAuthor jiveAuthor = jivePost.getAuthor();
        JiveContent jiveContent = jivePost.getContent();

        Discussion discussion = new Discussion(id);
        discussion.setIsQuestion(jivePost.isQuestion());
        discussion.setTitle(jivePost.getSubject());
        discussion.setContent(jiveContent.getText());
        discussion.setAuthor(Author.fromJiveAuthor(jiveAuthor));

        return discussion;
    }
}
