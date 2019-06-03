package dao;

import vo.PatientCosts;
import vo.PatientCostsBack;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author cor
 * 病人消费信息
 */
public interface IPatientCostsDao {

    void setConnection(Connection con);

    /**
     * 根据病历号查询病人的消费信息
     * @return
     */
    List<PatientCostsBack> selectPatientCosts(String caseNum) throws SQLException;


    /**
     * @Description: 记录患者费用明细,创建时间和付钱时间需要设置
     * @Param: [pc]
     * @return: void
     * @Author: cro
     * @Date: 2019/6/1
     */
    void insertPatientCosts(PatientCosts pc) throws SQLException;
}
