package test;

import org.junit.Test;
import service.SchedulingService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class SchedulingServiceTest {
    SchedulingService sc=new SchedulingService();
    @org.junit.Test
    public void schedInfoNow() {
    }

    @org.junit.Test
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
}