package test;

import dao.RLMDao;
import org.junit.Test;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RLMDaoTest {

    @Test
    public void selectRegistLevel() throws SQLException {
        Connection con =null;
        con = JdbcUtil.getConnection();
        RLMDao rlmDao = new RLMDao();
        rlmDao.setConnection(con);
        String code = "zjh";
        List list = rlmDao.SelectRegistLevel(code);
        System.out.println(list);
    }

    @Test
    public void addRegistLevel() throws SQLException {
        Connection con =null;
        con = JdbcUtil.getConnection();
        RLMDao rlmDao = new RLMDao();
        rlmDao.setConnection(con);
        String code = "zzzz";
        String name = "yisad";
        int no = 1;
        double fee=1.2;
        int quota = 1;
        int mark =1;
        rlmDao.AddRegistLevel(code,name,no,fee,quota);
    }

    @Test
    public void selectupdateRegistLevel() {
    }

    @Test
    public void updatesaveRegistLevel() {
    }

    @Test
    public void deleteRegistLevel() {
    }
}