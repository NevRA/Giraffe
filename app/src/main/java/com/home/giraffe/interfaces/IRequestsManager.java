package com.home.giraffe.interfaces;

import com.home.giraffe.objects.Jive.*;
import com.home.giraffe.objects.Post;

public interface IRequestsManager {
    String signIn(String serverAddress, String userName, String userPassword) throws Exception;
    JiveAuthor getUserInfo(String userName) throws Exception;
    JiveInbox getInbox() throws Exception;
    int getInboxBadgeCount() throws Exception;
    int getActionsBadgeCount() throws Exception;
    JivePost getPost(String url) throws Exception;
    JiveContainers getJiveContainers(String url) throws Exception;
    JivePosts getJivePosts(String url) throws Exception;
    void postMessage(String url, JivePost post) throws Exception;
    byte[] getData(String url) throws Exception;
}
