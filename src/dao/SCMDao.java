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
    public void addSettleCategory(int id) throws SQLException {
        String sql="SELECT count(id) \n" +
                "FROM SettleCategory\n" +
                "where SettleCode = ?\n" +
                "and DelMark = 1";
        String sql2="INSERT SettleCategory(ID,SettleCode,SettleName,SequenceNo,DelMark)\n" +

                /*网页传入*/
                "VALUES(…………….)";

        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setInt(1,id);
        ResultSet rs= pstm.executeQuery();
        int num = 0;
        while (rs.next()){
            num = rs.getInt(1);
        }
        if (num>9){
            PreparedStatement pstm2 = con.prepareStatement(sql2);
            int i=pstm2.executeUpdate();
        }else {
            JdbcUtil.release(null,pstm,null);
        }
        JdbcUtil.release(null,pstm,null);
    }

    @Override
    public SettleCategory updateSettleCategory(int id) throws SQLException {
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
    public SettleCategory updateSettleCategorySave(String code) throws SQLException {
        String sql="SELECT count(id) \n" +
                "FROM SettleCategory\n" +
                "where SettleCode = ?\n" +
                "and DelMark = 1";
        String sql2="update  SettleCategory\n" +

                /*网页传入*/
                "Set ….\n" +

                "WHERE id = ?";

        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1,code);
        ResultSet rs = pstm.executeQuery();
        int num =0;
        while (rs.next()){
            num = rs.getInt(1);
            if(num > 9){
                PreparedStatement pstm2=con.prepareCall(sql2);
                pstm2.executeUpdate();
                JdbcUtil.release(null,pstm2,null);
            }else{
                JdbcUtil.release(null,pstm,null);
            }
            JdbcUtil.release(null,pstm,null);
        }
        return null;
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
