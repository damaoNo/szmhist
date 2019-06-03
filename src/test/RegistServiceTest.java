package test;

import org.junit.Test;
import service.regist.IRegistService;
import service.regist.RegistService;
import vo.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RegistServiceTest {
    IRegistService service=new RegistService();
    @Test
    public void findMaxInvoiceNum() throws SQLException {
        String num=service.findMaxInvoiceNum(9);
        System.out.println(num);
    }

    @Test
    public void findMaxCaseNum() throws SQLException {
        String num=service.findMaxCaseNum();
        System.out.println(num);
    }

    @Test
    public void readSettleCategories() throws SQLException {
        List list=service.readSettleCategories();
        System.out.println(list.toString());
    }

    @Test
    public void readRegistLevels() throws SQLException {
        List list=service.readRegistLevels();
        System.out.println(list.toString());
    }

    @Test
    public void findRegistLevelByID() throws SQLException {
        RegistLevel rl=service.findRegistLevelByID(1);
        System.out.println(rl);
    }

    @Test
    public void findDepartment() throws SQLException {
        List list=service.findDepartment();
        System.out.println(list.toString());
    }

    @Test
    public void findDoctorInfo() throws SQLException, ParseException {
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String date1="2019-4-1";
        date=df.parse(date1);

        SchedDoctor sd=new SchedDoctor(date,"上午",1,1);
        List list=service.findDoctorInfo(sd);
        System.out.println(list.toString());
    }

    @Test
    public void findDoctorUsedId() throws SQLException, ParseException {
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String date1="2019-3-19";
        date=df.parse(date1);
        Register r=new Register();
        r.setUserID(1);
        r.setVisitDate(date);
        int i=service.findDoctorUsedId(r);
        System.out.println(i);
    }

    @Test
    public void regist() throws SQLException {
        Date date=new Date();
        Timestamp t=new Timestamp(System.currentTimeMillis());
        Register r=new Register(1,"600599","李白",71,"12345",date,20,"岁","天涯海",date,"上午",1,1,2,1,"1",t,123,1);
        service.regist(r);
    }

    @Test
    public void useInvoice() throws ParseException, SQLException {

        Timestamp t=new Timestamp(System.currentTimeMillis());
        Invoice iv=new Invoice("800818",88.88,2,t,301,24,51,"no",0);

        service.useInvoice(iv);
    }


}