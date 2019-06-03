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
        List list = rlmDao.selectRegistLevel(code);
        System.out.println(list);
    }
}