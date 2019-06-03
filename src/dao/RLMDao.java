package dao;

import util.JdbcUtil;
import vo.RegistLevel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 挂号级别管理
 * @version 0.1
 * @author Vector_Wu
 */

public class RLMDao implements IRLMDao {
    Connection con = null;

    public void setConnection(Connection con){
        this.con = con;
    }

    @Override
    public List<RegistLevel> selectRegistLevel(String code) throws SQLException {
        String sql ="select ID,RegistCode,RegistName,SequenceNo,RegistFee,RegistQuota,DelMark\n" +
                "FROM registlevel \n" +
                "where (RegistCode like \"%\"?\"%\" Or RegistName like \"%\"?\"%\")\n" +
                "and DelMark = 1\n" +
                "order by SequenceNo";

        con = JdbcUtil.getConnection();
        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1,code);
        pstm.setString(2,code);
        ResultSet rs = pstm.executeQuery();
        List<RegistLevel> RegistLevelList = new ArrayList<>();
        while (rs.next()){
            RegistLevel rl = new RegistLevel();
            rl.setId(rs.getInt(1));
            rl.setRegistCode(rs.getString(2));
            rl.setRegistName(rs.getString(3));
            rl.setSequenceNo(rs.getInt(4));
            rl.setRegistFree(rs.getDouble(5));
            rl.setRegistquota(rs.getInt(6));
            rl.setDelMark(rs.getInt(7));
            RegistLevelList.add(rl);
        }
        JdbcUtil.release(null,pstm,null);
        return RegistLevelList;
    }

    @Override
    public void addRegistLevel(String regisc) throws SQLException {
            String sql ="SELECT count(id) \n" +
                    "FROM RegistLevel\n" +
                    "where RegistCode = ?\n" +
                    "and DelMark = 1";
            String sql2="INSERT INTO RegistLevel(ID,RegistCode,RegistName,SequenceNo,RegistFee,RegistQuota,DelMark)\n" +

                    /*获取网页的数值*///////////////////
                    "VALUES()";

        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1,regisc);
        ResultSet rs = pstm.executeQuery();
        int num=0;
        while(rs.next()){
            num=rs.getInt(1);
        }
        if(num>8){
            PreparedStatement pstm2= con.prepareStatement(sql2);
            int i=pstm2.executeUpdate();
        }else{
            JdbcUtil.release(null,null,null);
        }
        JdbcUtil.release(null,pstm,null);
    }

    @Override
    public RegistLevel updateRegistLevel(int id) throws SQLException {
        String sql="select ID,RegistCode,RegistName,SequenceNo,RegistFee,RegistQuota,DelMark\n" +
                "FROM RegistLevel \n" +
                "where ID=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,id);
      //  RegistLevel user = new RegistLevel();
        ResultSet rs = pstm.executeQuery();
        RegistLevel uprl = new RegistLevel();
        while (rs.next()){
            uprl.setId(rs.getInt(1));
            uprl.setRegistCode(rs.getString(2));
            uprl.setRegistName(rs.getString(3));
            uprl.setSequenceNo(rs.getInt(4));
            uprl.setRegistFree(rs.getDouble(5));
            uprl.setRegistquota(rs.getInt(6));
            uprl.setDelMark(rs.getInt(7));
        }
        JdbcUtil.release(null,pstm,null);
        return uprl;
    }

    @Override
    public RegistLevel saveRegistLevel(String code) throws SQLException {
        String sql="SELECT count(id) \n" +
                "FROM RegistLevel\n" +
                "where RegistCode = ?\n" +
                "and DelMark = 1";

        String sql2="update  RegistLevel\n" +

                /*修改信息来自网页*//////////////////////
                "Set ….\n" +

                "WHERE id = ?";

        PreparedStatement pstm= con.prepareStatement(sql);
        pstm.setString(1,code);
        ResultSet rs = pstm.executeQuery();
        int num = 0;

        while(rs.next()){
            num = rs.getInt(1);

        }if (num>8){
            PreparedStatement pstm2= con.prepareStatement(sql2);
            pstm2.executeUpdate();

        }else {
            JdbcUtil.release(null,pstm,null);
        }
        JdbcUtil.release(null,pstm,null);
        return null;
    }

    @Override
    public void deleteRegistLevel(int id) throws SQLException {
        String sql="update  RegistLevel\n" +
                "set DelMark = 0 \n" +
                "WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,id);
        JdbcUtil.release(null,pstm,null);
    }
}