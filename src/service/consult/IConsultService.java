package service.consult;

import vo.MedicalRecord;
import vo.NonDrugsPay;
import vo.Register;

import java.sql.SQLException;
import java.util.List;

/**
 * * @ClassName: IConsult
 * * @description: 门诊服务
 * * @author: cro
 * * @create: 2019-06-04 10:28
 **/
public interface IConsultService {
    /*************************左侧边栏-查找科室和个人患者*****************************************/
    /**
     * 查询科室所有未诊断的患者挂号信息
     * @param deptId 部门编号
     * @return list
     * @throws SQLException sql
     */
    List<Register> findUnVisitPatient(int deptId) throws SQLException;
    /**
     * 查询科室所有已经诊断的患者挂号信息
     * @param deptId 部门编号
     * @return list
     * @throws SQLException sql
     */
    List<Register> findVisitedPatient(int deptId) throws SQLException;

    /**
     * 查询当前医生所有未诊断的患者挂号信息
     * @param userID 医生userID
     * @return list
     * @throws SQLException sql
     */
    List<Register> findDocUVP(int userID) throws SQLException;

    /**
     * 查询当前医生所有已经诊断的患者挂号信息
     * @param userID 医生userID
     * @return list
     * @throws SQLException sql
     */
    List<Register> findDocVP(int userID) throws SQLException;
    /*****************************查询病历首页*********************************************/
    /**
     * 根据挂号ID查询相关病历记录
     * @param regID 挂号ID
     * @return 病历记录对象
     * @throws SQLException
     */
    MedicalRecord findMedRecord(int regID) throws SQLException;
    /***************************点击提交-更新挂号状态为看诊-2；更新病历首页
     * @param regID 挂号ID
     * @param state 更改状态为2（register-VisitState）
     * @param mr 病历首页对象
     * *********************/

    void consulting(int regID,int state,MedicalRecord mr) throws SQLException;


    /**
     * 根据类型（int）查询所有项目(此方法也可提供模糊查询)- 只设置ndp的type为查询该类型所有项目，可设置助记码和药品名
     * @param ndp ndp
     * @return list
     * @throws SQLException
     */
    List<NonDrugsPay> findNDrugByType(NonDrugsPay ndp) throws SQLException;
}
