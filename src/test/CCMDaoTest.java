package test;

import dao.CCMDao;
import org.junit.Test;
import util.JdbcUtil;
import vo.ConstantItem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CCMDaoTest {

    @Test
    public void selectConstantTypeAll() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        List list=ccmDao.SelectConstantTypeAll();
        System.out.println(list);
    }

    @Test
    public void selectConstantType() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        String codeORname = "DocTitle";
        List list = ccmDao.SelectConstantType(codeORname);
        System.out.println(list);
    }

    @Test
    public void addConstantType() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        String code = "wagh";
        String name = "实施";
        ccmDao.AddConstantType(code,name);
    }

    @Test
    public void selectConstantltem() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        ConstantItem ct = new ConstantItem();
        ct.setContantCode("xlk");
        int p = 9;
        List list=ccmDao.SelectConstantltem(ct,p);
        System.out.println(list);
    }

    @Test
    public void addConstantltem() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        String code = "xlk";
        String name = "心理科";
        int typeID = 1;
        ccmDao.AddConstantltem(code,name,typeID);
    }

    @Test
    public void updateConstantltem() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        String code = "xlk";
        String name = "放射科";
        int typeID = 1;
        int ID = 19;
        ccmDao.UpdateConstantltem(code,name,typeID,ID);
    }

    @Test
    public void delectConstantltem1() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        String[] ID={"11","12","15","16"};
        ccmDao.DelectConstantltem(ID);
    }

    @Test
    public void selectConstantTypeAll1() throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        CCMDao ccmDao = new CCMDao();
        ccmDao.setConnection(con);
        List list=ccmDao.SelectConstantTypeAll();
        System.out.println(list.toString());
    }
}