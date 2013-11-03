package com.home.giraffe;

import java.util.regex.Pattern;

public class Constants {

    public static final String APP_TAG = "giraffe";
    public static final String HOME = "/index.jspa";
    public static final String LOGIN = "/cs_login";
    public static final String API = "/api/core/v3/";
    public static final String PEOPLE = API + "people/";
    public static final String ACTIONS = API + "actions";
    public static final String ACTIVITIES_COUNT_PARAM = "?count=40";
    public static final String INBOX = API + "inbox" + ACTIVITIES_COUNT_PARAM;
    public static final String INBOX_ZERO_RECORDS = API + "inbox?filter=author(/people/-1)";
    public static final String ALL_ACTIVITIES = API + "activities" + ACTIVITIES_COUNT_PARAM;
    public static final String CONNECTIONS_STREAM = API + "streams/connections/activities" + ACTIVITIES_COUNT_PARAM;
    public static final String CUSTOM_STREAM = API + "streams/%s/activities" + ACTIVITIES_COUNT_PARAM;
    public static final String ME = "@me";

    public static final String RememberMeCookie = "SPRING_SECURITY_REMEMBER_ME_COOKIE";

    public static final String SocialNewsId = "SocialNews";
    public static final String LikesId = "Likes";

    public static final String CommunityUrl = "CommunityUrlPref";
    public static final String UserIdPref = "UserIdPref";
    public static final String UserNamePref = "UserNamePref";
    public static final String UserJobTitlePref = "UserJobTitlePref";
    public static final String UserTokenPref = "UserTokenPref";

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);

    // Extras

    public static final String ID_EXTRA = "id_extra";
}
