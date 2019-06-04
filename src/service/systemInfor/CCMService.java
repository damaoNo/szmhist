package service.systemInfor;

import dao.CCMDao;
import dao.ICCMDao;
import util.JdbcUtil;
import vo.ConstantItem;
import vo.ConstantType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 常数类别管理
 * @version 0.1
 * @author Vector_Wu
 */

public class CCMService implements ICCMService{

    @Override
    public List<ConstantType> CCMSelectConstantTypeAll() throws SQLException {
        Connection con =null;
        List list =null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            list = iccmDao.SelectConstantTypeAll();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    @Override
    public List<ConstantType> CCMSelectConstantType(String codeORname) throws SQLException {
        Connection con=null;
        List list = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.SelectConstantType(codeORname);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    @Override
    public void CCMAddConstantType(String Ccode, String Cname) throws SQLException {
        Connection con=null;
        List list = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.AddConstantType(Ccode,Cname);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public List<ConstantItem> CCMSelectConstantltem(ConstantItem constantItem, int page) throws SQLException {
        Connection con=null;
        List list = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.SelectConstantltem(constantItem,page);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    @Override
    public void CCMAddConstantltem(String code, String name, int typeID) throws SQLException {
        Connection con=null;
        List list = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.AddConstantltem(code,name,typeID);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public void CCMUpdateConstantltem(String code, String name, int typeID, int ID) throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.setConnection(con);
            iccmDao.UpdateConstantltem(code,name,typeID,ID);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public void CCMDelectConstantltem(String[] ID) throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.setConnection(con);
            iccmDao.DelectConstantltem(ID);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }
}
