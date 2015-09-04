package org.saarang.saarangsdk.Helpers;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class TimeHelper {

    private static String LOG_TAG = "TimeHelper";

    public TimeHelper(){
    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    //function for getting Relative time from provided time formatted string directly.

    public static String getRelative(String timeString){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        long time=1999999999;
        try {
            Date date = (Date) formatter.parse(timeString);
            time = date.getTime();
        }
        catch (Exception e){

        }

        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();

        now-=5.5*60*60*1000; //correction for time zone . Decrease now by 5 : 30 hours.

        if (time > now || time <= 0) {
            return null;
        }

        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "Just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "A minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "An hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "Yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }

    }

    // function for getting timestamp from the provided time.
    public static long getTimeStamp(String time){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date date = (Date) formatter.parse(time);
            return date.getTime();
        }
        catch (Exception e){

        }
        return 1999999999;

    }

    //function for getting Relative time from timestamp. (With respect to current time.)

    public static String getTimeAgo(long time) {


        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "Just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "A minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "An hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "Yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }



    //function for getting Date and Time from timestamp
    public static  String getDateCurrentTimeZone(long timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date currenTimeZone = (Date) calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }

    public static String getDate(String timeStamp){
        String year = "", month = "", day = "", date;
        int i = 0, count = 0;
        if(timeStamp == null){
            return "";
        }
        else {

            while ((timeStamp.charAt(i) != 'T' || timeStamp.charAt(i) != 't') && i < (timeStamp.length() - 1)) {
                char c = timeStamp.charAt(i);

                if (count < 4 && c != '-') {
                    year += c;
                } else if (count < 7 && c != '-') {
                    month += c;
                } else if (count < 10 && c != '-') {
                    day += c;
                }
                i++;
                count++;
            }
            date = setMonthInFormat(month) + " " + day + ", " + year;
            return date;
        }
    }

    public static String getTime(String timeStamp){
        int hr = 0, min = 0;
        if(timeStamp == null){
            return "";
        }
        else
        {
            hr = Integer.parseInt(timeStamp.substring(11, 13));
            min = Integer.parseInt(timeStamp.substring(14, 16));

            if(hr>12){
                return (hr-12) + ":" + min + " PM";
            }
            else if(hr == 12){
                return (hr) + ":" + min + " PM";
            }
            else{
                return hr + ":" + min + " AM";
            }
        }
    }

    private static String setMonthInFormat(String month){
        switch (Integer.parseInt(month)) {
            case 1:
                return "January";

            case 2:
                return "February";

            case 3:
                return "March";

            case 4:
                return "April";

            case 5:
                return "May";

            case 6:
                return "June";

            case 7:
                return "July";

            case 8:
                return "August";

            case 9:
                return "September";

            case 10:
                return "October";

            case 11:
                return "November";

            case 12:
                return "December";

            default:
                return "";
        }
    }

}
