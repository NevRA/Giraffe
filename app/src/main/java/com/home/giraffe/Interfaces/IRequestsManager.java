package com.home.giraffe.interfaces;

import com.home.giraffe.objects.Activities;
import com.home.giraffe.objects.Inbox;
import com.home.giraffe.objects.Person;

public interface IRequestsManager {
    void signIn(String serverAddress, String userName, String userPassword) throws Exception;
    Person getUserInfo(String userName) throws Exception;
    Inbox getInbox() throws Exception;
    Activities getActivities() throws Exception;
}
