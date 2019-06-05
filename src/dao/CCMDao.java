package dao;

import com.sun.javafx.binding.StringFormatter;
import com.sun.xml.internal.bind.v2.model.core.ID;
import util.JdbcUtil;
import vo.ConstantItem;
import vo.ConstantType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * dsxdcd
 */

public class CCMDao implements ICCMDao{
    Connection con=null;

    @Override
    public void setConnection(Connection con) {
        this.con = con;
    }


    @Override
    public List<ConstantType> SelectConstantTypeAll(int page) throws SQLException {
        String sql="select ConstanttypeCode,constanttypeName,ID \n" +
                    "FROM constanttype\n" +
                    "WHERE DelMark=1\n" +
                    "ORDER BY ID" +
                    "limit ?,10";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,(page-1)*10);
        ResultSet rs = pstm.executeQuery();
        List<ConstantType> ConstantTypeList = new ArrayList<>();
        while (rs.next()){
            ConstantType ct=new ConstantType();
            ct.setConstantTypeCode(rs.getString(1));
            ct.setConstantTypeName(rs.getString(2));
            ct.setId(rs.getInt(3));
            ConstantTypeList.add(ct);
        }
        JdbcUtil.release(null,pstm,null);
        return ConstantTypeList;
    }


    @Override
    public List<ConstantType> SelectConstantType(String codeORname) throws SQLException {
        String sql="select ConstanttypeCode,constanttypeName,ID \n" +
                "FROM constanttype\n" +
                "WHERE (ConstantTypeName like \"%\"?\"%\" or ConstantTypeCode like \"%\"?\"%\")\n" +
                "and DelMark=1\n" +
                "ORDER BY ID";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,codeORname);
        pstm.setString(2,codeORname);
        ResultSet rs = pstm.executeQuery();
        List<ConstantType> constantTypeList = new ArrayList<>();
        while (rs.next()){
            ConstantType st = new ConstantType();
            st.setConstantTypeCode(rs.getString(1));
            st.setConstantTypeName(rs.getString(2));
            st.setId(rs.getInt(3));
            constantTypeList.add(st);
        }
        JdbcUtil.release(null,pstm,null);
        return constantTypeList;
    }


    @Override
    public void AddConstantType(String Ccode, String Cname) throws SQLException {
        String sql="INSERT INTO constanttype(ConstanttypeCode,ConstanttypeName)\n" +
                "VALUES(?,?)";
        PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,Ccode);
            pstm.setString(2,Cname);
            pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }


    @Override
    public List<ConstantItem> SelectConstantltem(ConstantItem constantItem,int page) throws SQLException {
        String sql=" SELECT ConstantCode,ConstantName,ConstantTypeID,ID\n" +
                "FROM constantitem\n" +
                "where DelMark = 1\n";
        if(constantItem.getContantCode()!=null&&constantItem.getContantCode().length()!=0) {
            sql += " and ConstantCode='"+constantItem.getContantCode()+"'";
        }
        if(constantItem.getConstantName()!=null&&constantItem.getConstantName().length()!=0) {
            sql += " and ConstantName='"+constantItem.getConstantName()+"'";
        }
        if(constantItem.getConstantTypeID()!=0) {
            sql += " and ConstantType="+constantItem.getConstantTypeID();
        }
        sql+=" limit ?,10";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,(page-1)*10);
        ResultSet rs = pstm.executeQuery();
        List<ConstantItem> constantItemList=new ArrayList<>();
        while (rs.next()){
            ConstantItem ct = new ConstantItem();
            ct.setContantCode(rs.getString(1));
            ct.setConstantName(rs.getString(2));
            ct.setConstantTypeID(rs.getInt(3));
            ct.setId(rs.getInt(4));
            constantItemList.add(ct);
        }
        JdbcUtil.release(null,pstm,null);
        return constantItemList;
    }


    @Override
    public void AddConstantltem(String code, String name, int typeID) throws SQLException {
        String sql="INSERT INTO constantitem(ConstantCode,ConstantName,ConstantTypeID)\n" +
                "VALUES(?,?,?)";
        PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,code);
            pstm.setString(2,name);
            pstm.setInt(3,typeID);
            pstm.executeUpdate();
            JdbcUtil.release(null,pstm,null);
    }


    @Override
    public void UpdateConstantltem(String code, String name, int typeID,int ID) throws SQLException {
        String sql="UPDATE constantitem SET ConstantCode=?,ConstantName=?,ConstantTypeID=?\n" +
                "WHERE ID = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,code);
        pstm.setString(2,name);
        pstm.setInt(3,typeID);
        pstm.setInt(4,ID);
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }


    @Override
    public void DelectConstantltem(String[] ID) throws SQLException {
        String sql="UPDATE constantitem\n" +
                "set DelMark = 0\n" +
                "WHERE ID =?";
        PreparedStatement pstm = con.prepareStatement(sql);
        for(int i=0;i<ID.length;i++){
            pstm.setString(1,ID[i]);
            pstm.addBatch();
            if(i%10==0){
                pstm.executeBatch();
            }
        }
        pstm.executeBatch();
        JdbcUtil.release(null,pstm,null);
    }

    /**
     * 根据挂号级别名字查id
     *
     * @param regLeName 挂号级别名称
     * @return 返回挂号级别id
     */
    @Override
    public int selectRegLeIDByName(String regLeName) throws SQLException {
        String sql="select id from registlevel where RegistName=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,regLeName);
        ResultSet rs=pstm.executeQuery();
        int id=0;
        while (rs.next()){
            id=rs.getInt(1);
        }
        JdbcUtil.release(null,pstm,null);
        return id;
    }
}
