/**
 * * @ClassName: IMedicalRecordService
 * * @description: 病历首页相关服务
 * * @author: cro
 * * @create: 2019-06-03 13:57
 **/

package service.consult;

import vo.MedicalRecord;
import vo.NonDrugsPay;

import java.sql.SQLException;
import java.util.List;

public interface IMedRecordService {

    /**
     * 根据regid查询相关病历记录
     * @param regID 挂号ID
     * @return 病历记录对象
     * @throws SQLException
     */
    MedicalRecord findMedRecord(int regID) throws SQLException;

    /**
     * 根据类型（int）查询所有项目
     * @param ndp ndp
     * @return list
     * @throws SQLException
     */
    List<NonDrugsPay> findNDrugByType(NonDrugsPay ndp) throws SQLException;
}
