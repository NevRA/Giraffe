package com.home.giraffe.objects;

import com.home.giraffe.objects.Jive.JiveVerbTypes;

public enum VerbTypes {
    Replied,
    Commented,
    Created,
    Modified,
    Promoted,
    Liked,
    CorrectAnswer,
    Completed;

    public static VerbTypes fromJiveVerbTypes(JiveVerbTypes types){
        switch (types) {
            case JiveReplied:
                return Replied;
            case JiveCommented:
                return Commented;
            case JiveCreated:
                return Created;
            case JiveModified:
                return Modified;
            case JivePromoted:
                return Promoted;
            case JiveLiked:
                return Liked;
            case JiveCorrectAnswerSet:
                return CorrectAnswer;
            case JiveCompleted:
                return Completed;
        }

        throw new IllegalArgumentException("Unsupported jive verb type");
    }
}
