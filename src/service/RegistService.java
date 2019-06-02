/**
 * @program: szmhist
 * * @description: 现场挂号-业务
 * * @author:cro
 * * @create: 2019-05-31 16:10
 **/

package service;

import dao.IRegistDao;
import dao.RegistDao;
import util.JdbcUtil;
import vo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RegistService implements IRegistService{


    /**
     * 读取收费员当前最大发票号
     * @param userId 当前收费员ID
     * @return 收费员最大发票号
     */
    @Override
    public String findMaxInvoiceNum(int userId) throws SQLException {
        Connection con=null;
        String maxInvoiceNum=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            maxInvoiceNum=registDao.selectMaxInvoiceNum(userId);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return maxInvoiceNum;
    }

    /**
     * 查询最大病历号
     *
     * @return 返回下一个可用的病历号
     */
    @Override
    public String findMaxCaseNum() throws SQLException {
        Connection con=null;
        String caseNum=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            caseNum=registDao.selectMaxCaseNum();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return caseNum;
    }

    /**
     * 读取所有可用结算类别
     *
     * @return list-settlecategories类 id，结算编号，结算类别名字
     */
    @Override
    public List readSettleCategories() throws SQLException {
        Connection con=null;
        List settleCategories=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            settleCategories=registDao.selectSettleCategories();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return settleCategories;
    }

    /**
     * 读取有效挂号级别
     *
     * @return list-registlevel-id,registcode,registname
     */
    @Override
    public List readRegistLevels() throws SQLException {
        Connection con=null;
        List list=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            list=registDao.selectRegistLevels();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /**
     * 根据ID获取挂号费和初始号额
     *
     * @param id registLevel-id
     * @return 返回一个封装了挂号费和初始号额的registlevel对象
     */
    @Override
    public RegistLevel findRegistLevelByID(int id) throws SQLException {
        Connection con=null;
        RegistLevel rl=new RegistLevel();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            rl=registDao.selectRegistLevelByID(id);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return rl;
    }

    /**
     * 读取有效临床科室
     *
     * @return list-department对象，id,registcode,registname
     */
    @Override
    public List findDepartment() throws SQLException {
        Connection con=null;
        List list=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            list=registDao.selectDepartment();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /**
     * 根据看诊日期,午别,排班科室,挂号级别读取当天出诊医生ID,姓名
     *
     * @param reg
     * @return list-User对象-id,realname
     */
    @Override
    public List findDoctorInfo(SchedDoctor sd) throws SQLException {
        Connection con=null;
        List list=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            list=registDao.selectDoctorInfo(sd);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /**
     * @param reg
     * @Description: 根据选中医生读取当日已用号额
     * @Param: [userId] 医生ID
     * @return: int 已用号额，当天共有多少人已预约
     * @Author: cro
     * @Date: 2019/6/1
     */
    @Override
    public int findDoctorUsedId(Register reg) throws SQLException {
        Connection con=null;
        int allUsedId=0;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            allUsedId=registDao.selectDoctorUsedId(reg);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return allUsedId;
    }

    /**
     * @param reg
     * @Description: 现场挂号,挂号时间为系统当前时间
     * @Param: [reg]
     * @return: java.lang.Boolean 是否插入成功
     * @Author: cro
     * @Date: 2019/6/1
     */
    @Override
    public boolean regist(Register reg) throws SQLException {
        Connection con=null;
        int allUsedId=0;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            registDao.insertRegist(reg);
            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return false;
    }

    /**
     * @param iv
     * @Description: 使用发票记录,创建时间自动设置为当前系统时间
     * @Param: [iv]
     * @return: boolean 是否插入成功
     * @Author: cro
     * @Date: 2019/6/1
     */
    @Override
    public boolean useInvoice(Invoice iv) throws SQLException {
        Connection con=null;
        int allUsedId=0;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            registDao.insertInvoice(iv);
            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return false;
    }

    /**
     * @param pc
     * @Description: 记录患者费用明细,创建时间和付钱时间需要设置
     * @Param: [pc]
     * @return: void
     * @Author: cro
     * @Date: 2019/6/1
     */
    @Override
    public boolean newPatientCosts(PatientCosts pc) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao registDao=new RegistDao();
            registDao.setConnection(con);
            registDao.insertPatientCosts(pc);
            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return false;
    }
}
