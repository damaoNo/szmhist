/**
 * * @ClassName: MedRecordService
 * * @description:
 * * @author: cro
 * * @create: 2019-06-03 13:58
 **/

package service.consult;

import dao.IMedicalRecordDao;
import dao.MedicalRecordDao;
import util.JdbcUtil;
import vo.MedicalRecord;
import vo.NonDrugsPay;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MedRecordService implements IMedRecordService{

    /**
     * 根据病历号查询相关病历记录
     *
     * @return 病历记录对象
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

    /**
     * 根据类型（int）查询所有项目
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
}
