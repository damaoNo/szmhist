package test;

import org.junit.Test;
import service.systemInfor.SCMService;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SCMServiceTest {

    @Test
    public void scmSelectSettleCategory() throws SQLException {
        Connection con=null;
        con = JdbcUtil.getConnection();
        SCMService scmService = new SCMService();
        List list =scmService.ScmSelectSettleCategory("js001");
        System.out.println(list);
    }

    @Test
    public void scmaddSettleCategory() {
    }

    @Test
    public void scmselectupdateSettleCategory() {
    }

    @Test
    public void scmupdateSettleCategorySave() {
    }

    @Test
    public void scmdeleteSettleCategory() {
    }
}