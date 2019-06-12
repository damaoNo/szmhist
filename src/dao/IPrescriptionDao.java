package dao;


import vo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import vo.Prescription;
import java.sql.SQLException;

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
    List<PrescriptionDetailed> selectDrugs(int id) throws SQLException;

    /**
     * 新增一条Prescriton记录 处方
     *-病历号、挂号ID、医生ID、处方名称、处方状态，处方创建时间自动设置为系统当前时间
     * @param p medicalid,registid,userid,pres..name,pres..time,pres..state
     */
    void insertPrescription(Prescription p) throws SQLException;

    /**
     * 新增一条药房明细记录
     * * 处方id          药品ID   药品用法    药品计量 频次         数量 状态
     *      *PrescriptionID,DrugsID,DrugsUsage,Dosage,Frequency,Amount,State 2-已开立 3-已交费 4-已发药 5-已退药 6-已退费
     * @param pd 药方明细对象
     */
    void  insertPresDetailed(PrescriptionDetailed pd) throws SQLException;

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
    void updatePresState(int id,int state) throws SQLException;

    /**
     * 修改处方状态，如果是开立，会自动更新开立时间为系统当前时间
     *
     * @param id    处方id
     * @param state 修改成为什么state
     */
    public void updatePSB(int[] id, int state) throws SQLException;

    //通过registId获取处方对象
    Prescription getInfByRegistId(int registId)throws SQLException;

    /**
     * 通过caseNum-搜索所有开立药方
     * @param caseNum
     * @return
     */
    List<PrescriptionMore> selectPrescriptionByCaseNum(String  caseNum,int state) throws SQLException;

    Register selectUserByCaseNum(String caseNum) throws SQLException;

}
