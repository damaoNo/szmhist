/**
 * * @ClassName: MedRecordService
 * * @description:
 * * @author: cro
 * * @create: 2019-06-03 13:58
 **/

package service;

import dao.DrugManageDao;
import dao.IDrugManageDao;
import dao.IMedicalRecordDao;
import dao.MedicalRecordDao;
import util.JdbcUtil;
import vo.MedicalRecord;

import java.sql.Connection;
import java.sql.SQLException;

public class MedRecordService implements IMedRecordService{

    /**
     * 根据病历号查询相关病历记录
     *
     * @param caseNum 病历号
     * @return 病历记录对象
     */
    @Override
    public MedicalRecord findMedRecord(String caseNum) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IMedicalRecordDao mrd=new MedicalRecordDao();
            mrd.setConnection(con);
            MedicalRecord mr=mrd.selectMedRecord(caseNum);
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
}
