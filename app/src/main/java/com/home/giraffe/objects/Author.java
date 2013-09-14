package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveActor;
import com.home.giraffe.objects.Jive.JiveAuthor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Author{
    private Integer mId;
    private String mDisplayName;
    private String mAvatarUrl;

    public int getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public static Author fromJiveActor(JiveActor jiveActor){
        Author author = new Author();

        Pattern pattern = Pattern.compile("(people/)(\\d+)");
        Matcher matcher = pattern.matcher(jiveActor.getId());
        if(matcher.find()){
            author.setId(Integer.valueOf(matcher.group(2)));
        }
        else {
            throw new IllegalArgumentException("User id");
        }

        author.setDisplayName(jiveActor.getDisplayName());
        author.setAvatarUrl(jiveActor.getImage().getUrl());

        return author;
    }

    public static Author fromJiveAuthor(JiveAuthor jiveAuthor){
        Author author = new Author();
        author.setId(jiveAuthor.getId());
        author.setDisplayName(jiveAuthor.getDisplayName());
        author.setAvatarUrl(jiveAuthor.getResources().getAvatar().getRef());

        return author;
    }
}
