package service.systemInfor;

import dao.ISchedulingDao;
import dao.SchedulingDao;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class SchedulingService implements ISchedulingService {
    /*读取当前排版信息*/
    @Override
    public List schedInfoNow(Date date1, Date date2) {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISchedulingDao service2=new SchedulingDao();
            service2.setConnection(con);
            list=service2.schedInfoNow(date1,date2);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }finally {
            JdbcUtil.release(con,null,null);
        }

        return list;
    }
    /*读取有效科室*/
    @Override
    public List depaEffctive() throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();

        con.setAutoCommit(false);
        ISchedulingDao ischeduling=new SchedulingDao();
        ischeduling.setConnection(con);
        list=ischeduling.depaEffctive();
        con.commit();
        JdbcUtil.release(con,null,null);
        return list;
    }
    /*读取有效挂号级别*/
    @Override
    public List registCode() throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();

        con.setAutoCommit(false);
        ISchedulingDao ischedulingservice=new SchedulingDao();
        ischedulingservice.setConnection(con);
        list=ischedulingservice.registCode();
        con.commit();
        JdbcUtil.release(con,null,null);
        return list;

    }

    @Override
    public List ruler(int DeptID) {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();

        try {
            con.setAutoCommit(false);
            ISchedulingDao ischedulingservice=new SchedulingDao();
            ischedulingservice.setConnection(con);
            list=ischedulingservice.ruler(DeptID);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;

    }

}
