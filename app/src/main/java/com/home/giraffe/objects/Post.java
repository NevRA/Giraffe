package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.*;

import java.util.*;

public class Post extends BaseObjectWithContent {
    public Post(String id) {
        super(id);
        mCommentIds = Collections.synchronizedSet(new HashSet<String>());
    }

    @Override
    public BaseObjectTypes getType() {
        return BaseObjectTypes.Post;
    }

    private Set<String> mCommentIds;

    public Set<String> getCommentIds() {
        return mCommentIds;
    }

    public void addCommentId(String commentId){
        mCommentIds.add(commentId);
    }

    public void clearCommentIds(){
        mCommentIds.clear();
    }

    public void fromJiveContainer(JiveContainer jiveContainer) {
        JiveActor jiveActor = jiveContainer.getActor();
        JiveObject jiveObject = jiveContainer.getObject();

        setTitle(jiveContainer.getTitle());
        setContent(jiveObject.getSummary());
        setActorId(jiveActor.getId());
    }

    public void fromJivePost(JivePost jivePost) {
        JiveAuthor jiveAuthor = jivePost.getAuthor();
        JiveContent jiveContent = jivePost.getContent();

        setTitle(jivePost.getSubject());
        setContent(jiveContent.getText());
        setActorId(jiveAuthor.getId());
    }
}
