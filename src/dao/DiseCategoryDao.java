package dao;

import util.JdbcUtil;
import vo.DiseCategory;
import vo.Disease;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiseCategoryDao implements  IDiseCategoryDao{
    Connection con=null;

    @Override
    public DiseCategory getDiseCategoryByCorN(String dicac, String dican) throws SQLException {
        String sql="Select ID,DicaCode,DicaName,SequenceNo,DicaType,DelMark\n" +
                "FROM DiseCategory \n" +
                "where (DicaCode like ? Or DicaName like ?) \n" +
                "and DelMark =1 ";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,dicac);
        ps.setString(2,dican);
        ResultSet rs=ps.executeQuery();
        DiseCategory user=new DiseCategory();
        while (rs.next()){
            user.setId(rs.getInt(1));
            user.setDicaCode(rs.getString(2));
            user.setDicaName(rs.getString(3));
            user.setSequenceNo(rs.getInt(1));
            user.setDicaType(rs.getInt(1));
            user.setDelMark(rs.getInt(1));
        }
        JdbcUtil.release(con,ps,rs);
        return user;
    }

    @Override
    public void addNewDiseCategory(String dicac, String dican, int seq, int dicat, int delmark) throws SQLException {
        String sql1="Select  count(id)\n" +
                "FROM DiseCategory\n" +
                "where DicaCode= ?\n" +
                "and DelMark = 1";
        String str="嗯嗯";
        String sql2="INSERT INTO DiseCategory(dicacode,dicaname,sequenceno,dicatype,delmark)\n" +
                "values(?,?,?,?,?)";

        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql1);
        ps.setString(1,dicac);
        ResultSet rs=ps.executeQuery();
        int num=0;
        while(rs.next()){
            num=rs.getInt(1);
        }
        if(num==0){//没有则添加
            ps=con.prepareStatement(sql2);
            ps.setString(1,dicac);
            ps.setString(2,dican);
            ps.setInt(3,seq);
            ps.setInt(4,dicat);
            ps.setInt(5,delmark);
            int i=ps.executeUpdate();
            JdbcUtil.release(con,null,null);
        }else{
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public DiseCategory getDiseByID(int id) throws SQLException {
        String sql = "Select ID,DicaCode,DicaName,SequenceNo,DicaType,DelMark\n" +
                "FROM DiseCategory \n" +
                "where ID = ?";
        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        DiseCategory user = new DiseCategory();
        while (rs.next()) {
            user.setId(rs.getInt(1));
            user.setDicaCode(rs.getString(2));
            user.setDicaName(rs.getString(3));
            user.setSequenceNo(rs.getInt(4));
            user.setDicaType(rs.getInt(5));
            user.setDelMark(rs.getInt(6));
        }
        JdbcUtil.release(con, null, null);
        return user;
    }

    @Override
    public void updateDiseCategory(String dicac, String dican, int seq, int dicat, int delmark) throws SQLException {
        String sql1="SELECT count(id) \n" +
                "FROM DiseCategory\n" +
                "where DicaCode = ?\n" +
                "and DelMark = 1";
        String sql2="update DiseCategory set DicaCode=?,DicaName=?,SequenceNo=?,DicaType=?,DelMark=? where DicaCode=?";
        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1,dicac);
        ResultSet rs = ps.executeQuery();
        int num=0;
        while(rs.next()){
            num=rs.getInt(1);
        }
        if(num==1){ //如果已经有一个，则修改
            ps=con.prepareStatement(sql2);
            ps.setString(1,dicac);
            ps.setString(2,dican);
            ps.setInt(3,seq);
            ps.setInt(4,dicat);
            ps.setInt(5,delmark);
            ps.setString(6,dicac);
            int i=ps.executeUpdate();
            JdbcUtil.release(con,null,null);
        }else{
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public void invalidateDiseCByID(int id) throws SQLException{
        String sql1="update  DiseCategory\n" +
                "set DelMark = 0 where id=?";
        String sql2="update Disease \n" +
                "set DelMark = 0\n" +
                "where DiseCategoryID = ?";

        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setInt(1,id);
        int i=ps.executeUpdate();
        ps=con.prepareStatement(sql2);
        ps.setInt(1,id);
        int j=ps.executeUpdate();
        JdbcUtil.release(con,null,null);
    }

    @Override
    public Disease getvalideDiseCategory(String disec, String disen) throws SQLException {
        String sql="SELECT D1.ID,D1.DiseaseCode,D1.DiseaseName,D1.DiseaseICD,D1.DiseCategoryID,D1.DelMark,D2.DicaName,D2.ID\n" +
                "FROM Disease D1,DiseCategory D2\n" +
                "WHERE D1.DiseCategoryID = D2.ID\n" +
                "and D1.DelMark = 1\n" +
                "and (D1.DiseaseCode like ? Or D1.DiseaseName like ?)";
        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,disec);
        ps.setString(2,disen);
        Disease user=new Disease();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            user.setId(rs.getInt(1));
            user.setDiseaseCode(rs.getString(2));
            user.setDiseaseName(rs.getString(3));
            user.setDiseaseICD(rs.getString(4));
            user.setDiseCategoryID(rs.getInt(5));
            user.setDelMark(rs.getInt(6));
        }
        JdbcUtil.release(con,null,null);
        return user;
    }

    @Override
    public List<DiseCategory> getAllvalideDiseCategory() throws SQLException {
        String sql="Select ID,DicaCode,DicaName,SequenceNo,DicaType,DelMark\n" +
                "FROM DiseCategory \n" +
                "where DelMark =1 ";
        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<DiseCategory> dises=new ArrayList<>();
        while(rs.next()){
            DiseCategory dise=new DiseCategory();
            dise.setId(rs.getInt(1));
            dise.setDicaCode(rs.getString(2));
            dise.setDicaName(rs.getString(3));
            dise.setSequenceNo(rs.getInt(4));
            dise.setDicaType(rs.getInt(5));
            dise.setDelMark(rs.getInt(6));
            dises.add(dise);
        }
        JdbcUtil.release(con,null,null);
        return dises;
    }

    @Override
    public void addNewDisease(String dicac, String dican, String icd, int cateid, int delmark) throws SQLException {
        String sql1="SELECT count(id) \n" +
                "FROM Disease\n" +
                "where DiseaseCode= ?\n" +
                "and DelMark = 1";
        String sql2="SELECT count(id) \n" +
                "FROM Disease\n" +
                "where DiseaseICD= ?\n" +
                "and DelMark = 1";
        String sql3="INSERT INTO Disease(diseasecode,diseasename,diseaseicd,disecategoryid,delmark)\n" +
                "VALUES(?,?,?,?,?)";

        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1,dicac);
        ResultSet rs=ps.executeQuery();
        int num1=0;
        if(rs.next()){
            num1=rs.getInt(1);
        }
        ps=con.prepareStatement(sql2);
        ps.setString(1,icd);
        rs=ps.executeQuery();
        int num2=0;
        if(rs.next()){
            num2=rs.getInt(1);
        }
        if(num1==0&&num2==0){
            ps=con.prepareStatement(sql3);
            ps.setString(1,dicac);
            ps.setString(2,dican);
            ps.setString(3,icd);
            ps.setInt(4,cateid);
            ps.setInt(5,delmark);
            int i=ps.executeUpdate();
            JdbcUtil.release(con,null,null);
        }else{
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public List<Object> findDiseaseByCategoryID(int id) throws SQLException {
        String sql="SELECT  D1.ID, D1.DiseaseCode, D1.DiseaseName, D1.DiseaseICD, D1.DiseCategoryID, D1.DelMark, D2.DicaName, D2.ID\n" +
                "FROM Disease D1 ,DiseCategory D2\n" +
                "WHERE  D1.DiseCategoryID =  D2.ID\n" +
                "and  D1.ID=?";

        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        List<Object> list=new ArrayList<>();
        Disease user=new Disease();
        DiseCategory user2=new DiseCategory();
        while(rs.next()){
            user.setId(rs.getInt(1));
            user.setDiseaseCode(rs.getString(2));
            user.setDiseaseName(rs.getString(3));
            user.setDiseaseICD(rs.getString(4));
            user.setDiseCategoryID(rs.getInt(5));
            user.setDelMark(rs.getInt(6));
            user2.setDicaName(rs.getString(7));
            user2.setId(rs.getInt(8));
            list.add(user);
            list.add(user2);
        }
        JdbcUtil.release(con,null,null);
        return list;
    }

    @Override
    public void updateDisease(String diseaseC, String diseaseI, String diseaseN, int diseCate, int delmark) throws SQLException {
        String sql1="SELECT count(id) \n" +
                "FROM Disease\n" +
                "where DiseaseCode= ?\n" +
                "and DelMark = 1";
        String sql2="SELECT count(id) \n" +
                "FROM Disease\n" +
                "where DiseaseICD= ?\n" +
                "and DelMark = 1";
        String sql3="INSERT INTO Disease(diseasecode,diseasename,diseaseicd,disecategoryid,delmark)\n" +
                "VALUES(?,?,?,?,?)";
        String sql4="update  Disease\n" +
                "Set diseasecode=?,diseasename=?,diseaseicd=?,disecategoryid=?,delmark=?\n" +
                "WHERE diseasecode= ?";
        con = JdbcUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql1);
        ps.setString(1,diseaseC);
        ResultSet rs=ps.executeQuery();
        int num1=0;
        if(rs.next()){
            num1=rs.getInt(1);
        }
        ps=con.prepareStatement(sql2);
        ps.setString(1,diseaseI);
        rs=ps.executeQuery();
        int num2=0;
        if(rs.next()){
            num2=rs.getInt(1);
        }

        if(num1==0&&num2==0){
            ps=con.prepareStatement(sql3);
            int i=ps.executeUpdate();
            JdbcUtil.release(con,null,null);
        }else if(num1==1&&num2==1){
            ps=con.prepareStatement(sql4);
            ps.setString(1,diseaseC);
            ps.setString(2,diseaseN);
            ps.setString(3,diseaseI);
            ps.setInt(4,diseCate);
            ps.setInt(5,delmark);
            ps.setString(6,diseaseC);

            int j=ps.executeUpdate();
            JdbcUtil.release(con,null,null);
        }else{
            JdbcUtil.release(con,null,null);
        }
    }

    @Override
    public void invalideDisease(int id) throws SQLException {
        String sql="update  Disease\n" +
                "set DelMark = 0 \n" +
                "WHERE id = ?";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,id);
        int i=ps.executeUpdate();
        JdbcUtil.release(con,null,null);
    }
}
