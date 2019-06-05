package test;

import org.junit.Test;
import service.systemInfor.SchedulingService;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SchedulingServiceTest {
    SchedulingService sc=new SchedulingService();
    @Test
    public void schedInfoNow() throws SQLException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date begin= null;
        Date end= null;
        try {
            begin = sdf.parse("2019-04-01");
            end=sdf.parse("2019-04-04");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List list=sc.schedInfoNow(begin,end,1);
        System.out.println(list.toString());

    }

    @Test
    public void depaEffctive() throws SQLException {
        List list=sc.depaEffctive();
        System.out.println(list.toString());
    }

    @Test
    public void registCode() throws SQLException {
        List list=sc.registCode();
        System.out.println(list.toString());
    }

    @Test
    public void ruler() {
        List list=sc.ruler(2);
        System.out.println(list.toString());
    }

    @Test
    public void selectRN() throws SQLException {
        List list=sc.selectRN("心血管内科","");
        System.out.println(list.toString());
    }
}