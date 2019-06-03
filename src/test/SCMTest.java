package test;

import dao.SCMDao;
import org.junit.Test;
import util.JdbcUtil;
import vo.SettleCategory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SCMTest {

    @Test
    public void selectSettleCategory() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        SCMDao scmDao =new SCMDao();
        scmDao.setConnection(con);
        String code = "js001";
       List list= scmDao.selectSettleCategory(code);
        System.out.println(list);
    }
}