package test;

import org.junit.Test;
import service.systemInfor.RLMService;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class RLMServiceTest {

    @Test
    public void RLMselectRegistLevel() {
    }

    @Test
    public void RLMaddRegistLevel() throws SQLException {
        Connection con= null;
        con =JdbcUtil.getConnection();
        RLMService rlmService = new RLMService();
        rlmService.RLMaddRegistLevel("ii","oo",4,9.9,5);
    }

    @Test
    public void RLMSelectupdateRegistLevel() {
    }

    @Test
    public void RLMUpdatesaveRegistLevel() {
    }

    @Test
    public void RLMdeleteRegistLevel() {
    }
}