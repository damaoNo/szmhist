/**
 * * @ClassName: Consult
 * * @description:
 * * @author: cro
 * * @create: 2019-06-04 10:30
 **/

package service.consult;

import dao.*;
import util.JdbcUtil;
import vo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConsultService implements IConsultService {

    /**
     * 查询科室所有未诊断的患者挂号信息
     *
     * @param deptId 部门编号
     * @return list
     * @throws SQLException sql
     */
    @Override
    public List<Register> findUnVisitPatient(int deptId) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            List<Register> list=mrd.selectUnVisitPatient(deptId);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 查询科室所有已经诊断的患者挂号信息
     *
     * @param deptId 部门编号
     * @return list
     * @throws SQLException sql
     */
    @Override
    public List<Register> findVisitedPatient(int deptId) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            List<Register> list=mrd.selectVisitedPatient(deptId);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 查询当前医生所有未诊断的患者挂号信息
     *
     * @param userID 医生userID
     * @return list
     * @throws SQLException sql
     */
    @Override
    public List<Register> findDocUVP(int userID) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            List<Register> list=mrd.selectDocUVP(userID);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 查询当前医生所有已经诊断的患者挂号信息
     *
     * @param userID 医生userID
     * @return list
     * @throws SQLException sql
     */
    @Override
    public List<Register> findDocVP(int userID) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            List<Register> list=mrd.selectDocVP(userID);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 根据挂号ID查询相关病历记录
     *
     * @param regID 挂号ID
     * @return 病历记录对象
     * @throws SQLException
     */
    @Override
    public MedicalRecord findMedRecord(int regID) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            MedicalRecord mr=mrd.selectMedRecord(regID);
            con.commit();
            return mr;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /***************************点击提交-更新挂号状态为看诊-2；更新病历首页
     * @param regID 挂号ID
     * @param state 更改状态为2（register-VisitState）
     * @param mr 病历首页对象  ********************/
    @Override
    public void consulting(int regID, int state, MedicalRecord mr) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            IRegistDao rd=new RegistDao();
            rd.setConnection(con);
            mrd.setConnection(con);
            rd.updateVisitState(regID,2);
            mrd.updateMedRecord(mr);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /**
     * 根据类型（int）查询所有项目(此方法也可提供模糊查询)- 只设置ndp的type为查询该类型所有项目，可设置助记码和药品名
     *
     * @param ndp ndp
     * @return list
     * @throws SQLException
     */
    @Override
    public List<NonDrugsPay> findNDrugByType(NonDrugsPay ndp) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            List<NonDrugsPay> list=mrd.selectNDrugByType(ndp);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 新增检查/检验/处置项目
     *
     * @param ca checkapply
     */
    @Override
    public void newCheckApply(CheckApply ca) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            ICheckApplyDao cad=new CheckApplyDao();
            cad.setConnection(con);
            mrd.setConnection(con);
            cad.insertCheckApply(ca);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /**
     * 查询个人的检查/检验/处置 申请
     *
     * @param registID   挂号ID
     * @param recordType 类型 1-检查 2-检验 3-处置
     * @return id，name,itemName,deptname,isurgent,state,price,result,
     */
    @Override
    public List<PatientCheckApply> findPatientCA(int registID, int recordType) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            ICheckApplyDao cad=new CheckApplyDao();
            cad.setConnection(con);
            List<PatientCheckApply> list=cad.selectPatientCA(registID,recordType);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 更改个人的检查/检验/处置 申请状态
     * 需要设置 id，state   -1-暂存 2-已开立 3-已交费 4-已登记 5-执行完 6-已退费 0-已作废
     *
     * @param ids   申请表ID
     * @param state 申请表状态
     * @throws SQLException
     */
    @Override
    public void changeCAState(int[] ids, int state) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            ICheckApplyDao cad=new CheckApplyDao();
            cad.setConnection(con);
            cad.updateCheckApplyState(ids,state);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }
}
