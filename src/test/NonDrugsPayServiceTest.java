package test;

import org.junit.Test;
import service.systemInfor.NonDrugsPayService;

import java.sql.SQLException;
import java.util.List;


public class NonDrugsPayServiceTest {
    NonDrugsPayService non=new NonDrugsPayService();
    @Test
    public void nonDrugsEffective() {
    }

    @Test
    public void payEffective() throws SQLException {
        List list=non.payEffective();
        System.out.println(list.toString());
    }

    @Test
    public void depEffective() throws SQLException {
        List list=non.depEffective();
        System.out.println(list);
    }

    @Test
    public void nonDrugsInfo() throws SQLException {
        List list=non.nonDrugsInfo(1);
        System.out.println(list);
    }
}