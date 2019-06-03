/**
 * * @ClassName: IMedicalRecordService
 * * @description: 病历首页
 * * @author: cro
 * * @create: 2019-06-03 13:57
 **/

package service.consult;

import vo.MedicalRecord;

import java.sql.SQLException;

public interface IMedRecordService {

    /**
     * 根据regid查询相关病历记录
     * @param regID 挂号ID
     * @return 病历记录对象
     * @throws SQLException
     */
    MedicalRecord findMedRecord(int regID) throws SQLException;
}
