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
            mr.setRegisterID(regID);
            rd.setConnection(con);
            mrd.setConnection(con);
            rd.updateVisitState(regID,state);
            mrd.updateMedRecord(mr);
            mrd.updateCaseState(regID,state);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public void consulted(int regID, int state) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            IRegistDao rd=new RegistDao();

            rd.setConnection(con);
            mrd.setConnection(con);
            rd.updateVisitState(regID,3);

            mrd.updateCaseState(regID,3);
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

    /*************************************************************************
     * 确诊 - 修改病历首页内容
     * @param mr 对象，需设置-registid（挂号id[必要]）、checkresult、diagnosis、handling
     ************************************************************************/
    @Override
    public void diagnosis(MedicalRecord mr) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            mrd.updateMR(mr);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /**
     * 根据userid,registID选择该医生开的处方
     *
     * @param userID   医生ID
     * @param registID 挂号ID
     * @return id，medicalid,registid,userid,prescriptionname,state
     */
    @Override
    public List<Prescription> findPreByUserID(int userID, int registID) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pd=new PrescriptionDao();
            pd.setConnection(con);
            List<Prescription> list=pd.selectPreByUserID(userID,registID);
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

    @Override
    public void newpres(Prescription p) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pd=new PrescriptionDao();
            pd.setConnection(con);
            pd.insertPrescription(p);
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }

    }

    /**
     * 查询当前处方中有的药品
     *
     * @param userID
     * @param registID
     * @return pd.ID, d.DrugsName, d.DrugsFormat, d.DrugsPrice, pd.DrugsUsage, pd.Dosage, pd.Frequency
     */
    @Override
    public List<PrescriptionDetailed> findDrugsinPre(int id) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pd=new PrescriptionDao();
            pd.setConnection(con);
            List<PrescriptionDetailed> list=pd.selectDrugs(id);
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
     * 分页查询可用药品列表
     *
     * @param mnemonicCode 助记码
     * @param page         页码
     * @return 药品对象集合
     */
    @Override
    public List allDrugs(String mnemonicCode, int page) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dmd=new DrugManageDao();
            dmd.setConnection(con);
            List list=dmd.selectDrugList(mnemonicCode,page);
            int pages=dmd.drugListPages(mnemonicCode);
            list.add(pages);
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
     * 新增一条药房明细记录
     * 处方id          药品ID   药品用法    药品计量 频次         数量 状态
     *PrescriptionID,DrugsID,DrugsUsage,Dosage,Frequency,Amount,State 2-已开立 3-已交费 4-已发药 5-已退药 6-已退费
     * @param pd 药方明细对象
     */
    @Override
    public void newPresDetailed(PrescriptionDetailed pd) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pdao=new PrescriptionDao();
            pdao.setConnection(con);
            pdao.insertPresDetailed(pd);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }

    }

    /**
     * 批量删除药品-处方明细表id
     * @param pdids
     */
    @Override
    public void deletDrugs(int[] pdids) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pdao=new PrescriptionDao();
            pdao.setConnection(con);
            pdao.deletDrugs(pdids);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /**
     * 修改处方状态,如果是开立，会自动更新开立时间为系统当前时间
     *
     * @param id    处方id
     * @param state 修改成为什么state
     */
    @Override
    public void changePresState(int id, int state) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pdao=new PrescriptionDao();
            pdao.setConnection(con);
            pdao.updatePresState(id,state);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }
    /**
     * 根据病历号查询病人的消费信息
     * @param caseNum 病历号
     * @return 返回：姓名    身份证       家庭住址        病历号         项 目名称   单价    数量       开立时间       状态
     *          r.RealName,r.IDnumber,r.HomeAddress,r.CaseNumber,"p.`Name`,p.Price,p.Amount,p.Createtime,r.VisitState
     */
    @Override
    public List<PatientCostsBack> findPatientCosts(String caseNum) throws SQLException {
        Connection con=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            IPatientCostsDao pcd=new PatientCostsDao();
            pcd.setConnection(con);
            pcd.selectPatientCosts(caseNum);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }
}
