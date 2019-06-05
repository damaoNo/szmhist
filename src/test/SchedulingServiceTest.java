package test;

import org.junit.Test;
import service.systemInfor.SchedulingService;
import util.chageDateFormat;
import vo.Rule;
import vo.Scheduling;


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

    @Test
    public void deleteSchedInfo() throws SQLException {
        String[] ID=new String[]{"55","56","57","58","59"};
        sc.deleteSchedInfo(ID);

    }

    @Test
    public void addRuler() throws SQLException {
        Rule ruler=new vo.Rule();
        ruler.setRuleNmae("mon");
        ruler.setDeptID(3);
        ruler.setUserID(1);
        ruler.setWeek("111000110011");
        sc.addRuler(ruler);
    }

    @Test
    public void ruler1() {
        List list=sc.ruler(3);
        System.out.println(list);
    }

    @Test
    public void addScheduling() throws SQLException {
        String str1="2019-06-05";
        String str2="2019-07-05";
        SimpleDateFormat spfd=new SimpleDateFormat("yyyy-MM-dd");
        Date begin= null;
        Date end=null;
        try {
            begin = spfd.parse(str1);
            end=spfd.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        chageDateFormat cha=new chageDateFormat (begin,end);
        System.out.println( cha.getMondays());

//
//        Scheduling scheduling=new Scheduling();
//        sc.addScheduling(scheduling);
    }
}