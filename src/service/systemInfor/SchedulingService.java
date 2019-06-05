package service.systemInfor;

import dao.ISchedulingDao;
import dao.SchedulingDao;
import util.JdbcUtil;
import vo.Rule;
import vo.Scheduling;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SchedulingService implements ISchedulingService {
    /**
     * 读取当前排班信息
     * @param begin 开始日期
     * @param end 结束日期
     * @param page  第几页
     * @return
     */
    @Override
    public List schedInfoNow(Date begin, Date end, int page) throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISchedulingDao service2=new SchedulingDao();
            service2.setConnection(con);
            list=service2.schedInfoNow(begin,end,page);
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

    /**
     * 新增排班规则
     *
     * @param rule
     */
    @Override
    public void addRuler(Rule rule) throws SQLException {
        Connection con=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISchedulingDao ischedulingservice=new SchedulingDao();
            ischedulingservice.setConnection(con);
            ischedulingservice.addRuler(rule);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }

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
     * 选取规则生成排班计划
     *
     * @param scheduling
     */
    @Override
    public void addScheduling(Scheduling scheduling) throws SQLException {
        Connection con=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISchedulingDao ischedulingservice=new SchedulingDao();
            ischedulingservice.setConnection(con);
            ischedulingservice.addScheduling(scheduling);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }

    }


}
