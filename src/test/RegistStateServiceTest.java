package test;

import org.junit.Test;
import service.systemInfor.IRegistStateService;
import service.systemInfor.RegistStateService;
import vo.RegisterRecord;

import java.sql.SQLException;

public class RegistStateServiceTest {
    IRegistStateService rss=new RegistStateService();
    @Test
    public void findRegistByCaseNumber() throws SQLException {
        RegisterRecord rr=rss.findRegistByCaseNumber("600601");
        System.out.println(rr);
    }

    @Test
    public void updateRegistState() throws SQLException {
        rss.updateRegistState(39);
    }

    @Test
    public void newURInvoice() {
//        rss.newURInvoice();
    }
}