package dao;

import util.JdbcUtil;
import vo.TakeDrugs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 药房发药takeDrugs（int CaseNumber, Date PrescriptionTime）
 * 刷新药方状态freshPrescription(int State,int PrescriptionID)
 *
 */
public class TakeDrugsDao implements ITakeDrugsDao {
    /**
     * 设置连接
     *
     * @param con
     */
    Connection con = null;

    @Override
    public void setConnection(Connection con) {
        this.con = con;
    }

    /**
     * 药房发药takeDrugs（int CaseNumber, Date PrescriptionTime）
     * 传入参数：CaseNumber病历号, PrescriptionTime药方开立时间（模糊查询）
     * 查询到：DrugsName，DrugsPrice，DelMark，Amount，RealName，PrescriptionName，PrescriptionTime，State,PrescriptionID
     * 药品名称，单价，药品状态，数量，医生姓名，药方名称，开立时间，发药状态（药方状态）,成药处方ID
     *
     * @param CaseNumber
     */
    @Override
    public List takeDrugs(int CaseNumber, Date PrescriptionTime,int State) throws SQLException {
        java.sql.Date Date=new java.sql.Date(PrescriptionTime.getTime());
        String sql = "Select  D.DrugsName,D.DrugsPrice, D.DelMark, Pre.Amount,  U.RealName, " +
                "P.PrescriptionName,P.PrescriptionTime, Pre.State, Pre.PrescriptionID, Pre.ID\n" +
                "From drugs D, prescriptiondetailed Pre,user U,prescription P, medicalrecord M\n" +
                " Where M.ID=P.MedicalID AND p.ID=Pre. PrescriptionID and Pre.DrugsID=D.ID and P.UserID=U.ID\n" +
                "      And M.CaseNumber=? and P.PrescriptionTime LIKE \"%\"?\"%\" and Pre.State=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, CaseNumber);
        pstmt.setDate(2, Date);
        pstmt.setInt(3, State);
        ResultSet rs = pstmt.executeQuery();
        List list = new ArrayList();
        TakeDrugs td = null;
        while (rs.next()) {
            td = new TakeDrugs();
            td.setDrugsName(rs.getString(1));
            td.setDrugsPrice(rs.getDouble(2));
            td.setDelMark(rs.getInt(3));
            td.setAmount(rs.getDouble(4));
            td.setRealName(rs.getString(5));
            td.setPrescriptionName(rs.getString(6));
            td.setPrescriptionTime(rs.getDate(7));
            td.setState(rs.getInt(8));
            td.setPrescriptionID(rs.getInt(9));
            td.setID(rs.getInt(10));
            list.add(td);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 刷新成药药方状态（发药，退药，退费）
     * 传入参数：State 2-已开立3-已交费4-已发药5-已退药6-已退费
     * PrescriptionID由药房发药takeDrugs（int CaseNumber, Date PrescriptionTime）查出得到；
     */
    @Override
    public void freshPrescription(int State, int PrescriptionID) throws SQLException {
        String sql = "UPDATE prescriptiondetailed SET State=? where PrescriptionID=? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, State);
        pstmt.setInt(2, PrescriptionID);
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }

    /**
     * 改变成药处方状态
     *批量
     * @param State
     * @param ID
     */
    @Override
    public void changeState(int State, String[] ID) throws SQLException {
        String sql = "UPDATE prescriptiondetailed SET State=? where ID=? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        for (int i=0;i<ID.length;i++){
            pstmt.setInt(1, State);
            pstmt.setInt(2, Integer.parseInt(ID[i]));
            pstmt.addBatch();
            if (i%10==0){
                pstmt.executeBatch();
            }
        }
       pstmt.executeBatch();
        JdbcUtil.release(null, pstmt, null);
    }


}
