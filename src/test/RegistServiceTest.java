package test;

import org.junit.Test;
import service.IRegistService;
import service.RegistService;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class RegistServiceTest {

    @Test
    public void findMaxInvoiceNum() throws SQLException {
        IRegistService service=new RegistService();
        String num=service.findMaxInvoiceNum(9);
        System.out.println(num);
    }

    @Test
    public void findMaxCaseNum() throws SQLException {
        IRegistService service=new RegistService();
        String num=service.findMaxCaseNum();
        System.out.println(num);
    }

    @Test
    public void read() {
    }
}