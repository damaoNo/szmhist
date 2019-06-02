package service;

import dao.INonDrugsPayDao;
import dao.NonDrugsPayDao;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NonDrugsPayService implements INonDrugsPayService {
    /*查询当前有效非药品收费项目*/
    @Override
    public List nonDrugsEffective(String ItemCode) throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.nonDrugsEffective(ItemCode);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }
    /*读取有效费用分类*/
    @Override
    public List payEffective() throws SQLException {
       Connection con=null;
       List list=null;
       con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.payEffective();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }
    /*读取有效科室*/
    @Override
    public List depEffective() throws SQLException {
     Connection con=null;
     List list=null;
     con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.depEffective();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }
    /*判断项目编码是否重复*/
    /*如不重复，则修改*/
    @Override
    public List nonDrugsInfo(int ID) throws SQLException {
       Connection con=null;
       List list=null;
       con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.nonDrugsInfo(ID);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);

        }
        return list;
    }

}
