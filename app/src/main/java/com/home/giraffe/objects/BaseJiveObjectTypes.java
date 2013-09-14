package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveTypes;

public enum BaseJiveObjectTypes {
    Unknown,
    Message,
    Comment,
    Instance,
    Group,
    Person,
    Discussion,
    File,
    Space,
    Project,
    Level;

    public static BaseJiveObjectTypes fromJiveTypes(JiveTypes types){
        switch (types) {
            case JiveMessage:
                return Message;
            case JiveComment:
                return Comment;
            case JiveInstance:
                return Instance;
            case JiveGroup:
                return Group;
            case JivePerson:
                return Person;
            case JiveDiscussion:
                return Discussion;
            case JiveFile:
                return File;
            case JiveSpace:
                return Space;
            case JiveProject:
                return Project;
            case JiveLevel:
                return Level;
        }

        return Unknown;
    }
}
