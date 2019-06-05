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

    /*
    查询所有类别数据
    返回常熟类list集合
     */
    @Override
    public List<ConstantType> CCMSelectConstantTypeAll(int page) throws SQLException {
        Connection con =null;
        List list =null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.setConnection(con);
            list = iccmDao.SelectConstantTypeAll(page);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /*
    查询常数类别
     */
    @Override
    public List<ConstantType> CCMSelectConstantType(String codeORname) throws SQLException {
        Connection con=null;
        List list = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.setConnection(con);
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

    /*
    新增常数类别
     */
    @Override
    public void CCMAddConstantType(String Ccode, String Cname) throws SQLException {
        Connection con=null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.setConnection(con);
            iccmDao.AddConstantType(Ccode,Cname);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /*
    查询常数项
     */
    @Override
    public List<ConstantItem> CCMSelectConstantltem(ConstantItem constantItem, int page) throws SQLException {
        Connection con=null;
        List list = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.setConnection(con);
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

    /*
    添加常数项
     */
    @Override
    public void CCMAddConstantltem(String code, String name, int typeID) throws SQLException {
        Connection con=null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ICCMDao iccmDao = new CCMDao();
            iccmDao.setConnection(con);
            iccmDao.AddConstantltem(code,name,typeID);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /*
    编辑常数项
     */
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

    /*
    删除常数项
     */
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
