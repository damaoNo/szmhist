package test;

import org.junit.Test;
import service.PatientCostsService;
import vo.PatientCosts;
import vo.PatientCostsBack;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class PatientCostsServiceTest {
    PatientCostsService service=new PatientCostsService();

    @Test
    public void selectPatientCosts() throws SQLException {
        List list=service.selectPatientCosts("600601");
        System.out.println(list.toString());
    }

    @Test
    public void newPatientCosts() throws SQLException {
        PatientCosts pc=new PatientCosts();
        pc.setRegistID(35);
        pc.setInvoiceID(134);
        pc.setItemID(1);
        pc.setItemType(1);
        pc.setName("胃苏颗粒");
        pc.setPrice(88.88);
        pc.setAmount(6);
        pc.setDeptID(5);
        pc.setCreateOperID(301);
        Timestamp t=new Timestamp(System.currentTimeMillis());
        pc.setPayTime(t);
        pc.setFeeType(51);
        service.newPatientCosts(pc);
    }
}