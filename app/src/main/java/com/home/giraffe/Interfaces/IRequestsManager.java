package com.home.giraffe.interfaces;

import com.home.giraffe.objects.Activities;
import com.home.giraffe.objects.Author;
import com.home.giraffe.objects.Inbox;

public interface IRequestsManager {
    void signIn(String serverAddress, String userName, String userPassword) throws Exception;
    Author getUserInfo(String userName) throws Exception;
    Inbox getInbox() throws Exception;
    Activities getActivities() throws Exception;
}
