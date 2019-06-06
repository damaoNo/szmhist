package test;

import dao.NonDrugsPayDao;
import org.junit.Test;
import util.JdbcUtil;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class NonDrugsPayDaoTest {

    @Test
    public void setConnection() {
    }

    @Test
    public void nonDrugsEffective() throws SQLException {
        NonDrugsPayDao n=new NonDrugsPayDao();
        n.setConnection(JdbcUtil.getConnection());
        System.out.println(n.nonDrugsEffective("大",5));
    }
}