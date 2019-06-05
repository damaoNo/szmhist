package service.systemInfor;

import dao.ISchedulingDao;
import dao.SchedulingDao;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SchedulingService implements ISchedulingService {
    /**
     * 读取当前排班信息
     * @param date1 开始日期
     * @param date2 结束日期
     * @param page
     * @return
     */
    @Override
    public List schedInfoNow(Date date1, Date date2, int page) throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISchedulingDao service2=new SchedulingDao();
            service2.setConnection(con);
            list=service2.schedInfoNow(date1,date2,page);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
                con.rollback();

        }finally {
            JdbcUtil.release(con,null,null);
        }

        return list;
    }



    /**
     * 批量删除排班计划
     */
    @Override
    public void deleteSchedInfo(String[] ID) throws SQLException {
        Connection con=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISchedulingDao service2=new SchedulingDao();
            service2.setConnection(con);
            service2.delScheduling(ID);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }

    }



    /**
     * 读取有效科室
     * @return
     * @throws SQLException
     */
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

    /**
     * 读取有效挂号级别
     * @return
     * @throws SQLException
     */
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

    /**
     * 按科室ID读取排班规则
     * @param DeptID
     * @return
     */
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

    /**
     * 组合查询医生名称RealName
     * 科室名称DeptName
     * 或者挂号级别RegistName
     *
     * @param DeptName
     * @param RegistName
     */
    @Override
    public List selectRN(String DeptName, String RegistName) throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISchedulingDao ischedulingservice=new SchedulingDao();
            ischedulingservice.setConnection(con);
            list=ischedulingservice.selectRN(DeptName,RegistName);
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
