package com.home.giraffe;

import com.home.giraffe.objects.BaseObjectTypes;
import com.home.giraffe.objects.Jive.JiveTypes;
import com.home.giraffe.objects.Jive.JiveVerbTypes;

public class Utils {
    public BaseObjectTypes getBaseObjectTypeByJiveTypes(JiveTypes parentJiveType, JiveVerbTypes jiveVerbTypes){
        if(jiveVerbTypes == JiveVerbTypes.JiveLiked){
            return BaseObjectTypes.Like;
        }

        if(jiveVerbTypes == JiveVerbTypes.JivePromoted){
            return BaseObjectTypes.Promotion;
        }

        switch (parentJiveType) {
            case JiveComment:
                break;
            case JiveMessage:
                break;
            case JiveInstance:
                break;
            case JiveGroup:
                break;
            case JivePerson:
                break;
            case JiveDiscussion:
                return BaseObjectTypes.Discussion;
            case JiveDocument:
                return BaseObjectTypes.Document;
            case JiveFile:
                return BaseObjectTypes.File;
            case JiveSpace:
                break;
            case JiveProject:
                break;
            case JiveTask:
                return BaseObjectTypes.Task;
            case JivePoll:
                return BaseObjectTypes.Poll;
            case JiveLevel:
                break;
        }

        return BaseObjectTypes.Unknown;
    }
}
