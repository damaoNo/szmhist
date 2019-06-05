package test.regist;

import org.junit.Test;
import service.regist.IRegistService;
import service.regist.RegistService;
import vo.SchedDoctor;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class RegistServiceTest {
    IRegistService service=new RegistService();

    @Test
    public void invoiceCaseNumPay() throws SQLException {
        List list=service.invoiceCaseNumPay(10);
        System.out.println(list.toString());
    }

    @Test
    public void findDoctorInfo() throws ParseException, SQLException {
//        SchedDoctor sc=new SchedDoctor();
//        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//        sc.setSchedDate(df.parse("2019-04-01"));
//        sc.setNoon("上午");
//        sc.setDeptID(1);
//        sc.setRegistLeID(1);
        List list=service.findDoctorInfo("2019-04-01","上午","心血管内科","专家号");
        System.out.println(list.toString());
    }
}