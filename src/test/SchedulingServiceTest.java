package test;

import dao.SST;
import org.junit.Test;
import service.systemInfor.SchedulingService;
import util.chageDateFormat;
import vo.Rule;
import vo.Scheduling;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        String str1 = "2019-06-05";
        String str2 = "2019-07-05";
        SimpleDateFormat spfd = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = null;
        Date end = null;
        try {
            begin = spfd.parse(str1);
            end = spfd.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        chageDateFormat cha = new chageDateFormat(begin, end);
        System.out.println(cha.getMondays());

        String week = "11110001110001";

        Date date=new Date();
        SimpleDateFormat dateFm=new SimpleDateFormat("EEEE");
        String currSun=dateFm.format(date);
        SST sst=new SST();
        Scheduling sca = new Scheduling();
        sca.setDeptID(2);
        sca.setUserID(2);
        sca.setRuleID(2);
        List l=new ArrayList();
        for (int i = 0; i < week.length(); i++) {
                    String s = String.valueOf(week.charAt(i));
                    if (s.equals("1")) {
                        switch (i) {
                            case 0:
                                sca.setNoon("上午");
                                l=cha.getMondays();
                                sst.addScheduling(sca,l);

                                break;
                            case 1:
                                sca.setNoon("下午");

                                l=cha.getMondays();
                                sst.addScheduling(sca,l);

                                break;
                            case 2:
                                sca.setNoon("上午");

                                l=cha.getTuesdays();
                                sst.addScheduling(sca,l);

                                break;
                            case 3:
                                sca.setNoon("下午");

                                l=cha.getTuesdays();
                                sst.addScheduling(sca,l);
                                break;
                            case 4:
                                sca.setNoon("上午");

                                l=cha.getWensdays();
                                sst.addScheduling(sca,l);

                                break;
                            case 5:
                                sca.setNoon("下午");

                                l=cha.getWensdays();
                                sst.addScheduling(sca,l);
                                break;
                            case 6:
                                sca.setNoon("上午");

                                l=cha.getThursdays();
                                sst.addScheduling(sca,l);
                                break;
                            case 7:
                                sca.setNoon("下午");

                                l=cha.getThursdays();
                                sst.addScheduling(sca,l);
                                break;
                            case 8:
                                sca.setNoon("上午");

                                l=cha.getSaturdays();
                                sst.addScheduling(sca,l);
                                break;
                            case 9:
                                sca.setNoon("下午");

                                l=cha.getSaturdays();
                                sst.addScheduling(sca,l);
                                break;
                            case 10:
                                sca.setNoon("上午");

                                l=cha.getFridays();
                                sst.addScheduling(sca,l);
                                break;
                            case 11:
                                sca.setNoon("下午");
                                l=cha.getFridays();
                                sst.addScheduling(sca,l);
                                break;
                            case 12:
                                sca.setNoon("上午");

                                l=cha.getSundays();
                                sst.addScheduling(sca,l);
                                break;
                            case 13:
                                sca.setNoon("下午");

                                l=cha.getSundays();
                                sst.addScheduling(sca,l);
                                break;
                            }
                        }

        }

    }
}