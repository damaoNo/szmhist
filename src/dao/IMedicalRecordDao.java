/**
 * * @ClassName: IMedicalRecordDao
 * * @description: 医药信息
 * * @author: cro
 * * @create: 2019-06-03 13:29
 **/

package dao;

import vo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IMedicalRecordDao {

    void setConnection(Connection con);

    /**
     * 根据挂号ID查询相关病历记录
     * @param regID 挂号ID
     * @return 病历记录对象
     * @throws SQLException
     */
    MedicalRecord selectMedRecord(int regID) throws SQLException;

    /**
     * 更新病历首页内容
     *
     * @param mr 诊断结果对象
     */
    void updateMedRecord(MedicalRecord mr) throws SQLException;

    /**
     * 更新确诊页面数据内容到对应注册ID处
     * @param mr  medicalrecord
     */
    void updateMR(MedicalRecord mr) throws SQLException;



    /**
     * 根据挂号ID 修改对应数据CaseState属性 1-暂存 2-已提交 3-诊毕
     * @param regID 挂号ID
     */
    void updateCaseState(int regID,int state) throws SQLException;

    /**
     * 查询科室所有未诊断的患者挂号信息
     * @return list
     */
    List<Register> selectUnVisitPatient(int deptId) throws SQLException;
    /**
     * 查询科室所有已经诊断的患者挂号信息
     * @return list
     */
    List<Register> selectVisitedPatient(int deptId) throws SQLException;

    /**
     * 查询当前医生所有未诊断的患者挂号信息
     * @return list
     */
    List<Register> selectDocUVP(int userID) throws SQLException;

    /**
     * 查询当前医生所有已经诊断的患者挂号信息
     * @return list
     */
    List<Register> selectDocVP(int userID) throws SQLException;

    /**
     * 根据类型（int）查询所有项目
     * @param ndp ndp
     * @return list
     * @throws SQLException
     */
    List<NonDrugsPay> selectNDrugByType(NonDrugsPay ndp) throws SQLException;



}
