package com.home.giraffe.utils;

/*       "2013-06-25T14:00:00Z";
         "2013-06-25T140000Z";
         "2013-06-25T14:00:00+04";
         "2013-06-25T14:00:00+0400";
         "2013-06-25T140000+0400";
         "2013-06-25T14:00:00-04";
         "2013-06-25T14:00:00-0400";
         "2013-06-25T140000-0400";*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ISO8601DateFormatter {

    private static final DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private static final DateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd'T'HHmmssZ");
    private static final String UTC_PLUS = "+";
    private static final String UTC_MINUS = "-";

    public static Date toDate(String iso8601string) throws ParseException {
        iso8601string = iso8601string.trim();
        iso8601string = iso8601string.replaceAll("(\\.\\d*)", "");
        if(iso8601string.toUpperCase().indexOf("Z")>0){
            iso8601string = iso8601string.toUpperCase().replace("Z", "+0000");
        }else if(((iso8601string.indexOf(UTC_PLUS))>0)){
            iso8601string = replaceColon(iso8601string, iso8601string.indexOf(UTC_PLUS));
            iso8601string = appendZeros(iso8601string, iso8601string.indexOf(UTC_PLUS), UTC_PLUS);
        }else if(((iso8601string.indexOf(UTC_MINUS))>0)){
            iso8601string = replaceColon(iso8601string, iso8601string.indexOf(UTC_MINUS));
            iso8601string = appendZeros(iso8601string, iso8601string.indexOf(UTC_MINUS), UTC_MINUS);
        }

        Date date = null;
        if(iso8601string.contains(":"))
            date = DATE_FORMAT_1.parse(iso8601string);
        else{
            date = DATE_FORMAT_2.parse(iso8601string);
        }
        return date;
    }

    public static String toISO8601String(Date date){
        return DATE_FORMAT_1.format(date);
    }

    private static String replaceColon(String sourceStr, int offsetIndex){
        if(sourceStr.substring(offsetIndex).contains(":"))
            return sourceStr.substring(0, offsetIndex) + sourceStr.substring(offsetIndex).replace(":", "");
        return sourceStr;
    }

    private static String appendZeros(String sourceStr, int offsetIndex, String offsetChar){
        if((sourceStr.length()-1)-sourceStr.indexOf(offsetChar,offsetIndex)<=2)
            return sourceStr + "00";
        return sourceStr;
    }
}