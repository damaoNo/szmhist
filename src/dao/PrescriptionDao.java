package dao;

import util.JdbcUtil;
import vo.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionDao implements  IPrescriptionDao{
    Connection con=null;

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
}
