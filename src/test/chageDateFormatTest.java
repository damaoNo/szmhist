package test;

import org.junit.Test;
import util.chageDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class chageDateFormatTest {

    @Test
    public void getNextWeekMonday() throws ParseException {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1=df.parse("2019-10-10 00:00:00");
        chageDateFormat c=new chageDateFormat(new Date(),date1);
        System.out.println("5"+c.getFridays().toString());
        System.out.println("1"+c.getMondays().toString());
        System.out.println("6"+c.getSaturdays().toString());
        System.out.println("7"+c.getSundays().toString());
        System.out.println("4"+c.getThursdays().toString());
        System.out.println("2"+c.getTuesdays().toString());
        System.out.println("3"+c.getWensdays().toString());
    }
}