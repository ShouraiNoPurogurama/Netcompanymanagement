package com.se1863.networkcompany.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.se1863.networkcompany.exception.IllegalDateException;


public class Utils {
    public static Date extractDate(String data) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(data);
        return date;
    }

    public static Date addDateByMonth(Date data, String duration) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, Integer.parseInt(duration));
        String resultStr = formatter.format(cal.getTime());
        Date result = formatter.parse(resultStr);
        return result;
    }

    public static boolean validateDuplicationOfList(List<?> input) {
        Set<Object> validator = new HashSet<>();
        input.stream()
            .forEach(data -> validator.add(data));
        return input.size() == validator.size();
    }

    public static boolean validStartAndEndDate(Date startDate, Date endDate) {
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        startDate.setSeconds(1);
        if(startDate.before(date)) throw new IllegalDateException("Contract start date must be in the present or future.");
        if(startDate.after(endDate)) throw new IllegalDateException("Contract end date must be after the start date.");
        return true;
    }
}
