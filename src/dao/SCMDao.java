package dao;

import oracle.jdbc.proxy.annotation.Pre;
import util.JdbcUtil;
import vo.RegistLevel;
import vo.SettleCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 结算类别管理
 * @version 0.1
 * @author Vector_Wu
 */

public class SCMDao implements ISCMDao{

    Connection con = null;

    @Override
    public void setConnection(Connection con) {this.con = con;}

    @Override
    public List<SettleCategory> selectSettleCategory(String code) throws SQLException {
        String sql="select ID,SettleCode,SettleName,SequenceNo,DelMark\n" +
                "FROM SettleCategory \n" +
                "where (SettleCode like \"%\"?\"%\" Or SettleName like \"%\"?\"%\" )\n" +
                "and DelMark = 1\n" +
                "order by SequenceNo";

        con =JdbcUtil.getConnection();
        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1,code);
        pstm.setString(2,code);
        ResultSet rs = pstm.executeQuery();
        List<SettleCategory> SettleCategoryList = new ArrayList<>();
        while (rs.next()){
            SettleCategory sc = new SettleCategory();
            sc.setId(rs.getInt(1));
            sc.setSettleCode(rs.getString(2));
            sc.setSettleName(rs.getString(3));
            sc.setSequenceNo(rs.getInt(4));
            sc.setDelMark(rs.getInt(5));
            SettleCategoryList.add(sc);
        }
        JdbcUtil.release(null,pstm,null);
        return SettleCategoryList;
    }

    @Override
    public void addSettleCategory(String Scode,String Sname,int Sno,int Smark) throws SQLException {
        String sql="SELECT count(id) \n" +
                "FROM SettleCategory\n" +
                "where SettleCode = ?\n" +
                "and DelMark = 1";
        String sql2="INSERT SettleCategory(SettleCode,SettleName,SequenceNo,DelMark)\n" +
                "VALUES(?,?,?,?)";

        con=JdbcUtil.getConnection();
        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1,Scode);
        ResultSet rs= pstm.executeQuery();
        int num = 0;
        while (rs.next()){
            num = rs.getInt(1);
        }
        if (num==0){
            pstm = con.prepareStatement(sql2);
            pstm.setString(1,Scode);
            pstm.setString(2,Sname);
            pstm.setInt(3,Sno);
            pstm.setInt(4,Smark);
            pstm.executeUpdate();
        }else {
            JdbcUtil.release(null,pstm,null);
        }
        JdbcUtil.release(null,pstm,null);
    }

    @Override
    public SettleCategory SelectupdateSettleCategory(int id) throws SQLException {
        String sql="select ID,SettleCode,SettleName,SequenceNo,DelMark\n" +
                "FROM SettleCategory \n" +
                "where ID=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,id);
        //  RegistLevel user = new RegistLevel();
        ResultSet rs = pstm.executeQuery();
        SettleCategory sc = new SettleCategory();
        while (rs.next()){
            sc.setId(rs.getInt(1));
            sc.setSettleCode(rs.getString(2));
            sc.setSettleName(rs.getString(3));
            sc.setSequenceNo(rs.getInt(4));
            sc.setDelMark(rs.getInt(5));
        }
        JdbcUtil.release(null,pstm,null);
        return sc;
    }

    @Override
    public void updateSettleCategorySave(String Scode,String Sname,int Sno,int Smark) throws SQLException {
        String sql="SELECT count(id) \n" +
                "FROM SettleCategory\n" +
                "where SettleCode = ?\n" +
                "and DelMark = 1";
        String sql2="update  SettleCategory Set SettleCode=?,SettleName=?,SequenceNo=?,DelMark=?\n" +
                "WHERE id = ?";

        con=JdbcUtil.getConnection();
        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1,Scode);
        ResultSet rs = pstm.executeQuery();
        int num =0;
        while (rs.next()){
            num = rs.getInt(1);
            if(num==1){
                pstm=con.prepareCall(sql2);
                pstm.executeUpdate();
                pstm.setString(1,Scode);
                pstm.setString(2,Sname);
                pstm.setInt(3,Sno);
                pstm.setInt(4,Smark);
                JdbcUtil.release(null,pstm,null);
            }else{
                JdbcUtil.release(null,pstm,null);
            }
        }

    }

    @Override
    public void deleteSettleCategory(int id) throws SQLException {
        String sql="update  SettleCategory\n" +
                "set DelMark = 0 \n" +
                "WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,id);
        JdbcUtil.release(null,pstm,null);
    }
}
