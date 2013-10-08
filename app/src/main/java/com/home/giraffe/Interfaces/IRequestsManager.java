package com.home.giraffe.interfaces;

import com.home.giraffe.objects.Jive.JiveObjects;
import com.home.giraffe.objects.Jive.JiveAuthor;
import com.home.giraffe.objects.Jive.JiveInbox;
import com.home.giraffe.objects.Jive.JivePost;

public interface IRequestsManager {
    String signIn(String serverAddress, String userName, String userPassword) throws Exception;
    JiveAuthor getUserInfo(String userName) throws Exception;
    JiveInbox getInbox() throws Exception;
    int getInboxBadgeCount() throws Exception;
    int getActionsBadgeCount() throws Exception;
    JivePost getPost(String url) throws Exception;
    JiveObjects getJiveObjects(String url) throws Exception;
}
