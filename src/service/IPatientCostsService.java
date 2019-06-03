package service;

import vo.PatientCosts;
import vo.PatientCostsBack;

import java.sql.SQLException;
import java.util.List;

/**
 * 患者消费记录 业务
 */
public interface IPatientCostsService {
    /**
     * 根据病历号查询病人的消费信息
     * @param caseNum 病历号
     * @return 过往消费记录list
     * @throws SQLException sql
     */
    List<PatientCostsBack> selectPatientCosts(String caseNum) throws SQLException;

    /**
     * @Description: 记录患者费用明细,创建时间和付钱时间需要设置
     * @Param: [pc]
     * @return: void
     * @Author: cro
     * @Date: 2019/6/1
     */
    boolean newPatientCosts(PatientCosts pc) throws SQLException;

}
