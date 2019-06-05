package dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import util.JdbcUtil;
import vo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    /*查询项目编码是否重复*/
    @Override
    public int countId(String ItemCode) throws SQLException {
        String sql="SELECT count(id) \n" +
                "FROM fmeditem\n" +
                "where ItemCode = ?\n" +
                "and DelMark = 1\n";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,ItemCode);
        //查询
        /*返回一个结果集*/
        ResultSet rs=pstmt.executeQuery();
        int counts=0;
        while(rs.next()){
           counts=rs.getInt(1);

        }
        return  counts;
    }
    /*修改非药品收费项目信息*/
    @Override
    public void fixNonDrugsInfo(NonDrugsPay nonDrugsPay) throws SQLException {
        String sql="update fmeditem set ItemCode=?,ItemName=?,Format=?,Price=?," +
                "ExpClassID=?,DeptID=?,MnemonicCode=?,LastUpdateDate=?,RecordType=? where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,nonDrugsPay.getItemCode());
        pstmt.setString(2,nonDrugsPay.getItemName());
        pstmt.setString(3,nonDrugsPay.getFormat());
        pstmt.setDouble(4,nonDrugsPay.getPrice());
        pstmt.setInt(5,nonDrugsPay.getExpClassID());
        pstmt.setInt(6,nonDrugsPay.getDeptID());
        pstmt.setString(7,nonDrugsPay.getMnemonicCode());
        //Util.Date转Sql.Date
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date Date=new java.sql.Date(nonDrugsPay.getLastUpdateDate().getTime());
        pstmt.setDate(8,Date);
        pstmt.setInt(9,nonDrugsPay.getRecordType());
        pstmt.setInt(10,nonDrugsPay.getID());
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }
    /*置待删除记录状态为无效*/
    @Override
    public void delMark(String[] ID) throws SQLException {
        String sql="update  fmeditem\n" +
                "set DelMark = 0 \n" +
                "WHERE id = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        for (int i=0;i<ID.length;i++){
            pstmt.setString(1,ID[i]);
            pstmt.addBatch();
            if (i%10==0){
                pstmt.executeBatch();
            }
        }
        pstmt.executeBatch();
        JdbcUtil.release(null,pstmt,null);
    }

    /**
     * 通过病历号查询已开立的非药品项目
     *
     * @param caseNum
     * @return
     */
    @Override
    public List selectNDbyCaseNum(String caseNum) throws SQLException {
        String sql="SELECT f.ItemName,f.Price,c.CreationTime,c.State\n" +
                "FROM checkapply c,medicalrecord m,fmeditem f\n" +
                "WHERE c.MedicalID=m.ID\n" +
                "AND c.ItemID=f.ID\n" +
                "AND m.CaseNumber=?\n" +
                "AND c.State=2";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,caseNum);
        ResultSet rs=pstmt.executeQuery();
        List list=new ArrayList();
        CheckApplyMore cam=null;
        while (rs.next()){
            cam=new CheckApplyMore();
            cam.setItemName(rs.getString(1));
            cam.setPrice(rs.getDouble(2));
            cam.setCreationTime(rs.getTimestamp(3));
            cam.setState(rs.getInt(4));
            list.add(cam);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;
    }

}

