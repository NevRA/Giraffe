package com.home.giraffe.objects.socialnews;

import com.home.giraffe.Constants;
import com.home.giraffe.objects.BaseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocialNews extends BaseObject {
    private List<BaseSocialNewsItem> mNews;

    public SocialNews() {
        super(Constants.SocialNewsId);
        mNews = Collections.synchronizedList(new ArrayList<BaseSocialNewsItem>());
    }

    public List<BaseSocialNewsItem> getNews() {
        return mNews;
    }

    public void addNews(BaseSocialNewsItem newsItem){
        mNews.add(newsItem);
    }
}
