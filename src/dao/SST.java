package dao;

import util.JdbcUtil;
import vo.Scheduling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SST {

    public void addScheduling(Scheduling scheduling,List l) throws SQLException {
        String sql="INSERT INTO scheduling (SchedDate,DeptID,UserID,Noon,RuleID) " +
                "VALUES (?,?,?,?,?)";
        Connection con=JdbcUtil.getConnection();
        PreparedStatement pstmt=con.prepareStatement(sql);

        for (int i=0;i<l.size();i++){
            Date date=(Date) l.get(i);
            java.sql.Date date1=new java.sql.Date(date.getTime());
            pstmt.setDate(1,date1);
            pstmt.setInt(2,scheduling.getDeptID());
            pstmt.setInt(3,scheduling.getUserID());
            pstmt.setString(4,scheduling.getNoon());
            pstmt.setInt(5,scheduling.getRuleID());
            pstmt.addBatch();
            if (i%20==0){
                pstmt.executeBatch();
            }
        }
        pstmt.executeBatch();
        JdbcUtil.release(con,pstmt,null);
    }
}
