/**
 * * @ClassName: chageDateFormat
 * * @description: 修改日期格式-将下周的星期几转换为日期
 * * @author: cro
 * * @create: 2019-06-03 09:13
 **/

package util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class chageDateFormat {
    private List<Date> mondays=new ArrayList<>();
    private List<Date> tuesdays=new ArrayList<>();
    private List<Date> wensdays=new ArrayList<>();
    private List<Date> thursdays=new ArrayList<>();
    private List<Date> fridays=new ArrayList<>();
    private List<Date> saturdays=new ArrayList<>();
    private List<Date> sundays=new ArrayList<>();
    private Calendar c_begin;
    private Calendar c_end;
    private int count;

    public chageDateFormat(Date beginDate,Date lastDate) {
        c_begin=Calendar.getInstance();
        c_end=Calendar.getInstance();
        c_begin.setTime(beginDate);
        c_end.setTime(lastDate);
        c_end.add(Calendar.DAY_OF_YEAR,1);
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weeks = dfs.getWeekdays();
        count=1;
        while (c_begin.before(c_end)) {
            Date date = new Date(c_begin.getTime().getTime());
            int day = c_begin.get(Calendar.DAY_OF_WEEK);

            if (day == 2){
                mondays.add(date);
            }
            if (day == 3){
                tuesdays.add(date);
            }
            if (day == 4){
                wensdays.add(date);
            }
            if (day == 5){
                thursdays.add(date);
            }
            if (day == 6){
                fridays.add(date);
            }
            if (day == 7){
                saturdays.add(date);
            }
            if (day == 1){
                sundays.add(date);
            }

            if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                count++;
            }
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
    }
    private void addDay(){

    }

    public List<Date> getMondays() {
        return mondays;
    }

    public void setMondays(List<Date> mondays) {
        this.mondays = mondays;
    }

    public List<Date> getTuesdays() {
        return tuesdays;
    }

    public void setTuesdays(List<Date> tuesdays) {
        this.tuesdays = tuesdays;
    }

    public List<Date> getWensdays() {
        return wensdays;
    }

    public void setWensdays(List<Date> wensdays) {
        this.wensdays = wensdays;
    }

    public List<Date> getThursdays() {
        return thursdays;
    }

    public void setThursdays(List<Date> thursdays) {
        this.thursdays = thursdays;
    }

    public List<Date> getFridays() {
        return fridays;
    }

    public void setFridays(List<Date> fridays) {
        this.fridays = fridays;
    }

    public List<Date> getSaturdays() {
        return saturdays;
    }

    public void setSaturdays(List<Date> saturdays) {
        this.saturdays = saturdays;
    }

    public List<Date> getSundays() {
        return sundays;
    }

    public void setSundays(List<Date> sundays) {
        this.sundays = sundays;
    }
}
