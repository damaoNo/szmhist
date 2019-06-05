package test.regist;

import org.junit.Test;
import service.regist.IRegistService;
import service.regist.RegistService;
import vo.Invoice;
import vo.Register;
import vo.SchedDoctor;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        List list=service.findDoctorInfo("2019-04-01","上午",1,1);
        System.out.println(list.toString());
    }

    @Test
    public void findMoneyTicket() throws SQLException {
        List list=service.findMoneyTicket(1,1,"2019-04-01");
        System.out.println(list.toString());
    }

    @Test
    public void name() throws SQLException {
        /*
         * 挂号-开发票-插入挂号记录
         * @param reg   CaseNumber-病历号,RealName-患者名,Gender-性别,IDnumber,BirthDate,Age,AgeType-int,
         *      HomeAddress,VisitDate-1,Noon,DeptID,UserID,RegistLeID,SettleID,
         *      IsBook-是否要病历本,RegistTime-系统当前时间（自动设置）,RegisterID,VisitState-1-已挂号（自动设置）
         * @param iv
         * InvoiceNum-发票号,Money-金额,State-1,CreationTime-系统当前时间,UserID-操作人id,RegistID-挂号id,FeeType-收费方式,Back-空,DailyState-空日结
         */
        Invoice iv=new Invoice();
        Register r=new Register();
        r.setCaseNumber("600601");
        r.setRealName("dufu");
        r.setGender(71);
        r.setIdNumber("111");
        r.setBirthDate(new Date());
        r.setAge(18);
        r.setAgeTpye("岁");
        r.setHomeAddress("11");
        r.setVisitDate(new Date());
        r.setNoon("上午");
        r.setDeptID(1);
        r.setUserID(1);
        r.setRegistLeID(1);
        r.setSettLeID(2);
        r.setisBook("0");
        r.setRegisterID(301);
        iv.setInvoiceNum("10000000");
        iv.setMoney(20d);
        iv.setState(1);
        iv.setUserID(1);
        iv.setFeeType(1);
        service.regist(r,iv);
    }

    @Test
    public void findRegistByCaseNumber() throws SQLException {
        List list=service.findRegistByCaseNumber("600601");
        System.out.println(list.toString());
    }

    @Test
    public void backRegist() throws SQLException {
        service.backRegist(56);
    }

    @Test
    public void findAllCosts() throws SQLException {
        List list=service.findAllCosts("600606");
        System.out.println(list);
    }
}