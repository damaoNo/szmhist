package dao;

import vo.Drugs;
import vo.Prescription;
import vo.PrescriptionDetailed;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 处方Dao
 */
public interface IPrescriptionDao {

    void setConnection(Connection con);

    /**
     * 根据userid,registID选择该医生开的处方
     * @param userID   医生ID
     * @param registID 挂号ID
     * @return id，medicalid,registid,userid,prescriptionname,state
     */
    List<Prescription> selectPreByUserID(int userID,int registID) throws SQLException;

    /**
     * 查询当前处方中有的药品
     * @param userID
     * @param registID
     * @return pd.ID,d.DrugsName,d.DrugsFormat,d.DrugsPrice,pd.DrugsUsage,pd.Dosage,pd.Frequency
     */
    List<PrescriptionDetailed> selectDrugs(int userID, int registID) throws SQLException;

    /**
     * 新增一条Prescriton记录 处方
     *-病历号、挂号ID、医生ID、处方名称、处方状态，处方创建时间自动设置为系统当前时间
     * @param p medicalid,registid,userid,pres..name,pres..time,pres..state
     */
    void insertPrescription(Prescription p) throws SQLException;

    /**
     * 新增一条药房明细记录
     * @param pd 药方明细对象
     */
    void  insertPresDetailed(PrescriptionDetailed pd) throws SQLException;

    /**
     * 批量删除药品
     * @param drugids
     */
    void deletDrugs(String[] drugids) throws SQLException;

    /**
     * 修改处方状态,如果是开立，会自动更新开立时间为系统当前时间
     * @param id    处方id
     * @param state     修改成为什么state
     */
    void updatePresState(int id,int state) throws SQLException;
}
