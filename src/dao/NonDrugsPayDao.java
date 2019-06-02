package dao;

import util.JdbcUtil;
import vo.Department;
import vo.ExpenseClass;
import vo.Fmeditem;
import vo.NonDrugsPay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NonDrugsPayDao implements INonDrugsPayDao {
    Connection con=null;
    /*设置连接*/
    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }
    /*查询当前有效非药品收费项目*/
    @Override
    public List nonDrugsEffective(String ItemCode) throws SQLException {
        String sql="SELECT F.ID,F.ItemCode,F.ItemName,F.Format,F.Price,F.ExpClassID,F.DeptID," +
                "F.MnemonicCode,F.CreationDate,F.LastUpdateDate,F.RecordType,F.DelMark,E.ExpName,D.DeptName\n" +
                "FROm fmeditem F,expenseClass E,department  D\n" +
                "where F.ExpClassID = E.ID\n" +
                "and F.DeptID = D.ID\n" +
                "and F.DelMark=1\n" +
                "and (F.ItemCode like '%"+ItemCode+"%' or F.ItemName like \"%\"?\"%\")";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,ItemCode);
//        pstmt.setString(2,ItemCode);
        //查询
        /*返回一个结果集*/
        ResultSet rs=pstmt.executeQuery();
        NonDrugsPay nonDrugs=null;
        List list=new ArrayList();
        while(rs.next()){
            nonDrugs=new NonDrugsPay();
            nonDrugs.setID(rs.getInt(1));
            nonDrugs.setItemCode(rs.getString(2));
            nonDrugs.setItemName(rs.getString(3));
            nonDrugs.setFormat(rs.getString(4));
            nonDrugs.setPrice(rs.getDouble(5));
            nonDrugs.setExpClassID(rs.getInt(6));
            nonDrugs.setMnemonicCode(rs.getString(8));
            nonDrugs.setCreationDate(rs.getDate(9));
            nonDrugs.setLastUpdateDate(rs.getDate(10));
            nonDrugs.setRecordType(rs.getInt(11));
            nonDrugs.setDelMark(rs.getInt(12));
            nonDrugs.setExpName(rs.getString(13));
            nonDrugs.setDeptName(rs.getString(14));
            list.add(nonDrugs);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;

    }
    /*查询有效费用*/
    @Override
    public List payEffective() throws SQLException {
        String sql="SELECT ID,ExpCode,ExpName,DelMark \n" +
                "FROM expenseclass\n" +
                "WHERE DelMark = 1\n";
        PreparedStatement pstmt=con.prepareStatement(sql);
        //查询
        /*返回一个结果集*/
        ResultSet rs=pstmt.executeQuery();
        ExpenseClass exp=null;
        List list=new ArrayList();
        while(rs.next()){
            exp =new ExpenseClass();
            exp.setId(rs.getInt(1));
            exp.setExpCode(rs.getString(2));
            exp.setExpName(rs.getString(3));
            exp.setDelMark(rs.getInt(4));
            list.add(exp);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;
    }
    /*读取有效执行科室*/
    @Override
    public List depEffective() throws SQLException {
        String sql="SELECT ID,DeptCode,DeptName,DeptCategoryID,DeptType,DelMark\n" +
                "FROM department \n" +
                "WHERE DeptType = 2\n" +
                "and DelMark = 1";
        PreparedStatement pstmt=con.prepareStatement(sql);
        //查询
        /*返回一个结果集*/
        ResultSet rs=pstmt.executeQuery();
        Department dep=null;
        List list=new ArrayList();
        while (rs.next()){
            dep=new Department();
            dep.setId(rs.getInt(1));
            dep.setDeptCode(rs.getString(2));
            dep.setDeptName(rs.getString(3));
            dep.setDeptCategoryID(rs.getInt(4));
            dep.setDeptType(rs.getInt(5));
            dep.setDelMark(rs.getInt(6));
            list.add(dep);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;
    }
    /*判断项目编码是否重复*/
    /*如不重复，则修改*/
    /*根据ID读取非药品信息*/
    @Override
    public List nonDrugsInfo(int ID) throws SQLException {
       String sql="SELECT F.ID,F.ItemCode,F.ItemName,F.Format,F.Price,F.ExpClassID,F.DeptID," +
               "F.MnemonicCode,F.CreationDate,F.LastUpdateDate,F.RecordType,F.DelMark\n" +
               "FROm fmeditem F\n" +
               "where F.ID = ?\n";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,ID);
        //查询
        /*返回一个结果集*/
        ResultSet rs=pstmt.executeQuery();
        Fmeditem fme=null;
        List list=new ArrayList();
        while (rs.next()){
            fme=new Fmeditem();
            fme.setId(rs.getInt(1));
            fme.setItemCode(rs.getString(2));
            fme.setItemName(rs.getString(3));
            fme.setFormat(rs.getString(4));
            fme.setPrice(rs.getDouble(5));
            fme.setExpClassID(rs.getInt(6));
            fme.setDeptID(rs.getInt(7));
            fme.setMnemonicCode(rs.getString(8));
            fme.setCreationDate(rs.getDate(9));
            fme.setLastUpdateDate(rs.getDate(10));
            fme.setRecordType(rs.getInt(11));
            fme.setDelMark(rs.getInt(12));
            list.add(fme);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;
    }

}

