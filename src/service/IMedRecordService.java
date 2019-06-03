/**
 * * @ClassName: IMedicalRecordService
 * * @description: 病历首页
 * * @author: cro
 * * @create: 2019-06-03 13:57
 **/

package service;

import vo.MedicalRecord;

import java.sql.SQLException;

public interface IMedRecordService {

    /**
     * 根据病历号查询相关病历记录
     * @param caseNum 病历号
     * @return 病历记录对象
     */
    MedicalRecord findMedRecord(String caseNum) throws SQLException;
}
