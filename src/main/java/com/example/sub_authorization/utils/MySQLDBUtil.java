package com.example.sub_authorization.utils;


import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MySQLDBUtil {
    private static final String HOSTNAME = "localhost"; // for local :localhost
    private static final String PORT_NUM = "8889"; // change it to your mysql port number // for local: 8889
    public static final String DB_NAME = "bf_hw_day10_q1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://"
            + HOSTNAME + ":" + PORT_NUM + "/" + DB_NAME
            + "?user=" + USERNAME + "&password=" + PASSWORD
            + "&autoReconnect=true&serverTimezone=UTC";
    public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static Date stringToDate(String date) {
        Date utilDate = null;
        try {
            utilDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return utilDate;
    }

    public static java.sql.Date stringToSqlDate(String date){
        Date utilDate = stringToDate(date);
        return new java.sql.Date(utilDate.getTime());
    }
}
