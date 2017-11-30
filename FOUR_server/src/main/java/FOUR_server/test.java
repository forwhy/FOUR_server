package FOUR_server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class test {

    public static void main(String[] args) {

        Calendar c=Calendar.getInstance();
        int hours = c.get(c.HOUR_OF_DAY);
        System.out.println(hours);

    }

    }

