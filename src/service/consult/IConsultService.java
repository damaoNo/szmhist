package service.consult;

import vo.*;

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

    void consulted(int regID,int state) throws SQLException;


    /**
     * 根据类型（int）查询所有项目(此方法也可提供模糊查询)- 只设置ndp的type为查询该类型所有项目，可设置助记码和药品名
     * @param ndp ndp
     * @return list
     * @throws SQLException
     */
    List<NonDrugsPay> findNDrugByType(NonDrugsPay ndp) throws SQLException;

    /**
     * 新增检查/检验/处置项目
     * @param ca checkapply
     */
    void newCheckApply(CheckApply ca) throws SQLException;

    /**
     * 查询个人的检查/检验/处置 申请
     * @param registID   挂号ID
     * @param recordType 类型 1-检查 2-检验 3-处置
     * @return id，name,itemName,deptname,isurgent,state,price,result,
     */
    List<PatientCheckApply> findPatientCA(int registID, int recordType) throws SQLException;

    /***************************************************************************
     *  更改个人的检查/检验/处置 申请状态
     *  需要设置 id，state   -1-暂存 2-已开立 3-已交费 4-已登记 5-执行完 6-已退费 0-已作废
     * @param ids    申请表ID
     * @param state 申请表状态
     * @throws SQLException
     ***************************************************************************/
    void changeCAState(int[] ids,int state) throws SQLException;

    /*************************************************************************
     * 确诊 - 修改病历首页内容
     * @param mr 对象，需设置-registid（挂号id）、checkresult、diagnosis、handling
     ************************************************************************/
    void diagnosis(MedicalRecord mr) throws SQLException;

    /*----------------------------成药处方界面----------------------------------*/
    /**
     * 根据userid,registID选择该医生开的处方
     * @param userID   医生ID
     * @param registID 挂号ID
     * @return id，medicalid,registid,userid,prescriptionname,state
     */
    List<Prescription> findPreByUserID(int userID,int registID) throws SQLException;
    /**
     * 查询当前处方中有的药品
     * @param userID
     * @param registID
     * @return pd.ID,d.DrugsName,d.DrugsFormat,d.DrugsPrice,pd.DrugsUsage,pd.Dosage,pd.Frequency
     */
    List<PrescriptionDetailed> findDrugsinPre(int userID, int registID) throws SQLException;

    /**
     * 分页查询可用药品列表，每页十条，可选用助记码查询，可直接传空串全部查询
     * @param mnemonicCode 助记码
     * @param page 页码
     * @return 药品对象集合
     */
    List allDrugs(String mnemonicCode,int page) throws SQLException;

    /**
     * 新增一条药房明细记录
     * 处方id          药品ID   药品用法    药品计量 频次         数量 状态
     *PrescriptionID,DrugsID,DrugsUsage,Dosage,Frequency,Amount,State 2-已开立 3-已交费 4-已发药 5-已退药 6-已退费
     * @param pd 药方明细对象
     */
    void  newPresDetailed(PrescriptionDetailed pd) throws SQLException;

    /**
     * 批量删除药品-处方明细表id
     * @param drugids
     */
    void deletDrugs(int[] pdids) throws SQLException;

    /**
     * 修改处方状态,如果是开立，会自动更新开立时间为系统当前时间
     * @param id    处方id
     * @param state     修改成为什么state
     */
    void changePresState(int id,int state) throws SQLException;

    /**
     * 根据病历号查询病人的消费信息
     * @param caseNum 病历号
     * @return 返回：姓名    身份证       家庭住址        病历号         项 目名称   单价    数量       开立时间       状态
     *          r.RealName,r.IDnumber,r.HomeAddress,r.CaseNumber,"p.`Name`,p.Price,p.Amount,p.Createtime,r.VisitState
     */
    List<PatientCostsBack> findPatientCosts(String caseNum) throws SQLException;


}
