
/**
 * * @ClassName: PrescriptionDao
 * * @description: 处方Dao
 * * @author: cro
 * * @create: 2019-06-04 15:07
 **/

package dao;
import util.JdbcUtil;
import vo.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.proxy.annotation.Pre;
import util.JdbcUtil;
import vo.Prescription;
import vo.PrescriptionDetailed;
import vo.PrescriptionMore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDao implements IPrescriptionDao{

    Connection con=null;

    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }

    /**
     * 根据userid,registID选择该医生开的处方
     * @param userID   医生ID
     * @param registID 挂号ID
     * @return id，medicalid,registid,userid,prescriptionname,state
     */
    @Override
    public List<Prescription> selectPreByUserID(int userID, int registID) throws SQLException {
        String sql="SELECT * FROM prescription WHERE RegistID=? AND UserID=? AND PrescriptionState in (1,2)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(2,userID);
        pstmt.setInt(1,registID);
        ResultSet rs=pstmt.executeQuery();
        List<Prescription> list=new ArrayList<>();
        Prescription p=null;
        while (rs.next()){
            p=new Prescription();
            p.setId(rs.getInt(1));
            p.setMedicalID(rs.getInt(2));
            p.setRegitID(rs.getInt(3));
            p.setUserID(rs.getInt(4));
            p.setPrescriptionName(rs.getString(5));
            p.setPrescriptionTime(rs.getDate(6));
            p.setPrescriptionState(rs.getInt(7));
            list.add(p);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 查询当前处方中有的药品
     *
     * @param userID
     * @param registID
     * @return pd.ID, d.DrugsName, d.DrugsFormat, d.DrugsPrice, pd.DrugsUsage, pd.Dosage, pd.Frequency
     */
    @Override
    public List<PrescriptionDetailed> selectDrugs(int userID, int registID) throws SQLException {
        String sql="SELECT pd.ID,d.DrugsName,d.DrugsFormat,d.DrugsPrice,pd.DrugsUsage,pd.Dosage,pd.Frequency " +
                "FROM prescription p,prescriptiondetailed pd,drugs d " +
                "WHERE p.ID=pd.PrescriptionID " +
                "AND pd.DrugsID=d.ID " +
                "AND p.RegistID=? " +
                "AND p.UserID=? ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,registID);
        pstmt.setInt(2,userID);
        ResultSet rs=pstmt.executeQuery();
        List<PrescriptionDetailed> list=new ArrayList<>();
        PrescriptionDetailed pd=null;
        while (rs.next()){
            pd=new PrescriptionDetailed();
            pd.setId(rs.getInt(1));
            pd.setDrugName(rs.getString(2));
            pd.setFormat(rs.getString(3));
            pd.setPrice(rs.getDouble(4));
            pd.setDrugsUsage(rs.getString(5));
            pd.setDosage(rs.getString(6));
            pd.setFrequency(rs.getString(7));
            list.add(pd);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 新增一条Prescriton记录 处方
     *-病历号、挂号ID、医生ID、处方名称、处方状态，处方创建时间自动设置为系统当前时间
     * @param p medicalid,registid,userid,pres..name,pres..time,pres..state
     */
    @Override
    public void insertPrescription(Prescription p) throws SQLException {
        String sql="INSERT INTO prescription(MedicalID,RegistID,UserID,PrescriptionName,PrescriptionTime,PrescriptionState) " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,p.getMedicalID());
        pstmt.setInt(2,p.getRegitID());
        pstmt.setInt(3,p.getUserID());
        pstmt.setString(4,p.getPrescriptionName());
        Timestamp time=new Timestamp(System.currentTimeMillis());
        pstmt.setTimestamp(5,time);
        String state=p.getPrescriptionState();
        int s=0;
        if ("已作废".equals(state)){
            s=0;
        }
        if ("暂存".equals(state)){
            s=1;
        }
        if ("已开立".equals(state)){
            s=2;
        }
        pstmt.setInt(6,s);
        JdbcUtil.release(null, pstmt, null);
    }

    /**
     * 新增一条药房明细记录
     * 处方id          药品ID   药品用法    药品计量 频次         数量 状态
     *PrescriptionID,DrugsID,DrugsUsage,Dosage,Frequency,Amount,State 2-已开立 3-已交费 4-已发药 5-已退药 6-已退费
     * @param pd 药方明细对象
     */
    @Override
    public void insertPresDetailed(PrescriptionDetailed pd) throws SQLException {
        String sql="INSERT INTO prescriptiondetailed(PrescriptionID,DrugsID,DrugsUsage,Dosage,Frequency,Amount) " +
                "VALUES(?,?,?,?,?,?);";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,pd.getPrescriptionID());
        pstmt.setInt(2,pd.getDrugsID());
        pstmt.setString(3,pd.getDrugsUsage());
        pstmt.setString(4,pd.getDosage());
        pstmt.setString(5,pd.getFrequency());
        pstmt.setDouble(6,pd.getAmount());
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }

    /**
     * 批量删除药品-处方明细表id
     * @param drugids
     */
    @Override
    public void deletDrugs(int[] pdids) throws SQLException {
        String sql="DELETE FROM prescriptiondetailed WHERE id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        for (int i=0;i<pdids.length;i++){
            pstmt.setInt(1,pdids[i]);
            pstmt.addBatch();
            if (i%10==0){
                pstmt.executeBatch();
            }
        }
        pstmt.executeBatch();
        JdbcUtil.release(null, pstmt, null);
    }

    /**
     * 修改处方状态，如果是开立，会自动更新开立时间为系统当前时间
     *
     * @param id    处方id
     * @param state 修改成为什么state
     */
    @Override
    public void updatePresState(int id, int state) throws SQLException {
        String sql="UPDATE prescription SET PrescriptionState=?";
        Timestamp time=new Timestamp(System.currentTimeMillis());
        if (state == 2){
            sql+=",PrescriptionTime="+time;
        }
        sql+=" WHERE ID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.setInt(2,state);
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }

    @Override
    public Prescription getInfByRegistId(int registId) throws SQLException {
        String sql="select id,medicalid,registid,userid,prescriptionname,prescriptiontime,prescriptionstate " +
                "from prescription where registid=?";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,registId);
        ResultSet rs=ps.executeQuery();
        Prescription pre=new Prescription();
        while(rs.next()){
            pre.setId(rs.getInt(1));
            pre.setMedicalID(rs.getInt(2));
            pre.setRegitID(rs.getInt(3));
            pre.setUserID(rs.getInt(4));
            pre.setPrescriptionName(rs.getString(5));
            pre.setPrescriptionState(rs.getInt(7));
        }
        JdbcUtil.release(con,null,null);
        return pre;
    }

    /**
     * 通过caseNum-搜索所有开立药方
     *
     * @param caseNum
     * @return
     */
    @Override
    public List<PrescriptionMore> selectPrescriptionByCaseNum(String caseNum) throws SQLException {
        String sql="SELECT p.ID,p.PrescriptionName,p.PrescriptionTime,p.PrescriptionState,g.DrugsName,g.DrugsPrice\n" +
                "FROM prescription p,medicalrecord m,prescriptiondetailed d,drugs g \n" +
                "WHERE p.MedicalID = m.ID\n" +
                "AND d.PrescriptionID=p.ID\n" +
                "AND d.DrugsID=g.id\n" +
                "AND m.CaseNumber=?\n" +
                "AND p.PrescriptionState=2";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,caseNum);
        ResultSet rs=ps.executeQuery();
        List<PrescriptionMore> list=new ArrayList<>();
        PrescriptionMore pm=null;
        while(rs.next()){
            pm=new PrescriptionMore();
            pm.setId(rs.getInt(1));
            pm.setPrescriptionName(rs.getString(2));
            pm.setPrescriptionTime(rs.getDate(3));
            pm.setPrescriptionState(rs.getInt(4));
            pm.setDrugName(rs.getString(5));
            list.add(pm);
        }
        JdbcUtil.release(con,null,null);
        return list;
    }

}
