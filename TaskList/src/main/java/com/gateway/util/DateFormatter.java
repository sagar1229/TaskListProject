package com.gateway.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Jayavardhan on 12/14/15.
 */
public class DateFormatter {


    private DateFormatter(){

    }

    private static DateFormatter instance = new DateFormatter();

    private static final String defaultDateFormat = "yyyy-MM-dd";

    public static DateFormatter getInstance(){
        return instance;
    }

    public Date parseDefaultDateString(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateFormat);
        try {
            Date date = dateFormat.parse(dateString);
            return date;
        }catch (ParseException e){
            System.out.print("Invalid Date Format , " + e);
            return null;
        }

    }

    public String parseDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateFormat);
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
