/**
 * * @ClassName: MedicalRecordDao
 * * @description:
 * * @author: cro
 * * @create: 2019-06-03 13:45
 **/

package dao;

import util.JdbcUtil;
import vo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDao implements IMedicalRecordDao{

    Connection con=null;

    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }

    /**
     * 根据病历号查询相关病历记录
     *
     * @param caseNum 病历号
     * @return 病历记录对象
     */
    @Override
    public MedicalRecord selectMedRecord(int regID) throws SQLException {
        String sql="SELECT * FROM medicalrecord WHERE RegistID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,regID);
        ResultSet rs=pstmt.executeQuery();
        MedicalRecord mr=new MedicalRecord();
        while (rs.next()){
            mr.setId(rs.getInt(1));
            mr.setCaseNumber(rs.getString(2));
            mr.setRegisterID(rs.getInt(3));
            mr.setReadme(rs.getString(4));
            mr.setPresent(rs.getString(5));
            mr.setPresentTreat(rs.getString(6));
            mr.setHistory(rs.getString(7));
            mr.setAllergy(rs.getString(8));
            mr.setPhysique(rs.getString(9));
            mr.setProposal(rs.getString(10));
            mr.setCareful(rs.getString(11));
            mr.setCheckResult(rs.getString(12));
            mr.setDiagnosis(rs.getString(13));
            mr.setHandling(rs.getString(14));
            mr.setCaseState(rs.getInt(15));
        }
        JdbcUtil.release(null, pstmt, null);
        return mr;
    }

    /**
     * 更新病历首页内容
     *
     * @param mr 诊断结果对象
     */
    @Override
    public void updateMedRecord(MedicalRecord mr) throws SQLException {
        String sql="UPDATE medicalrecord " +
                "SET RegistID=? ";
        if (mr.getReadme()!=null && mr.getReadme().length()!=0){
            sql+=",ReadMe='"+mr.getReadme()+"'";
        }
        if (mr.getPresent()!=null && mr.getPresent().length()!=0){
            sql+=",Present='"+mr.getPresent()+"'";
        }
        if (mr.getPresentTreat()!=null && mr.getPresentTreat().length()!=0){
            sql+=",PresentTreat='"+mr.getPresentTreat()+"'";
        }
        if (mr.getHistory()!=null && mr.getHistory().length()!=0){
            sql+=",History='"+mr.getHistory()+"'";
        }
        if (mr.getPhysique()!=null && mr.getPhysique().length()!=0){
            sql+=",Physique='"+mr.getPhysique()+"'";
        }
        if (mr.getProposal()!=null && mr.getProposal().length()!=0){
            sql+=",Proposal='"+mr.getProposal()+"'";
        }
        if (mr.getCareful()!=null && mr.getCareful().length()!=0){
            sql+=",Careful='"+mr.getCareful()+"'";
        }
        sql+=" where RegistID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,mr.getRegisterID());
        pstmt.setInt(2,mr.getRegisterID());
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }

    /**
     * 更新确诊页面数据内容到对应注册ID处
     *
     * @param mr medicalrecord
     */
    @Override
    public void updateMR(MedicalRecord mr) throws SQLException {
        String sql="UPDATE medicalrecord SET Readme=?,Present=?,PresentTreat=?,History=?,Physique=?,Proposal=?,Careful=? where RegistID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,mr.getReadme());
        pstmt.setString(2,mr.getPresent());
        pstmt.setString(3,mr.getPresentTreat());
        pstmt.setString(4,mr.getHistory());
        pstmt.setString(5,mr.getPhysique());
        pstmt.setString(6,mr.getProposal());
        pstmt.setString(7,mr.getCareful());
        pstmt.setInt(8,mr.getRegisterID());
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }



    /**
     * 根据挂号ID 修改对应数据CaseState属性 1-暂存 2-已提交 3-诊毕
     *
     * @param regID 挂号ID
     */
    @Override
    public void updateCaseState(String regID,int state) throws SQLException {
        String sql="UPDATE medicalrecord set CaseState=? where RegistID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,state);
        pstmt.setString(2,regID);
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }

    /**
     * 查询所有为诊断的患者挂号信息
     *
     * @return list
     */
    @Override
    public List<Register> selectUnVisitPatient(int deptId) throws SQLException {
        String sql="SELECT CaseNumber,RealName,Age,Gender FROM register WHERE VisitState in (1,2) and DeptID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,deptId);
        ResultSet rs=pstmt.executeQuery();
        List<Register> list=new ArrayList<>();
        Register r=null;
        while (rs.next()){
            r=new Register();
            r.setCaseNumber(rs.getString(1));
            r.setRealName(rs.getString(2));
            r.setAge(rs.getInt(3));
            r.setGender(rs.getInt(4));
            list.add(r);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 查询所有已经诊断的患者挂号信息
     *
     * @return list
     */
    @Override
    public List<Register> selectVisitedPatient(int deptId) throws SQLException {
        String sql="SELECT CaseNumber,RealName,Age,Gender FROM register WHERE VisitState in (1,2) and DeptID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,deptId);
        ResultSet rs=pstmt.executeQuery();
        List<Register> list=new ArrayList<>();
        Register r=null;
        while (rs.next()){
            r=new Register();
            r.setCaseNumber(rs.getString(1));
            r.setRealName(rs.getString(2));
            r.setAge(rs.getInt(3));
            r.setGender(rs.getInt(4));
            list.add(r);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 查询当前医生所有未诊断的患者挂号信息
     *
     * @param userID
     * @return list
     */
    @Override
    public List<Register> selectDocUVP(int userID) throws SQLException {
        String sql="SELECT CaseNumber,RealName,Age,Gender FROM register WHERE VisitState=3 and UserID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,userID);
        ResultSet rs=pstmt.executeQuery();
        List<Register> list=new ArrayList<>();
        Register r=null;
        while (rs.next()){
            r=new Register();
            r.setCaseNumber(rs.getString(1));
            r.setRealName(rs.getString(2));
            r.setAge(rs.getInt(3));
            r.setGender(rs.getInt(4));
            list.add(r);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 查询当前医生所有已经诊断的患者挂号信息
     *
     * @param userID
     * @return list
     */
    @Override
    public List<Register> selectDocVP(int userID) throws SQLException {
        String sql="SELECT CaseNumber,RealName,Age,Gender FROM register WHERE VisitState=3 and UserID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,userID);
        ResultSet rs=pstmt.executeQuery();
        List<Register> list=new ArrayList<>();
        Register r=null;
        while (rs.next()){
            r=new Register();
            r.setCaseNumber(rs.getString(1));
            r.setRealName(rs.getString(2));
            r.setAge(rs.getInt(3));
            r.setGender(rs.getInt(4));
            list.add(r);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 根据类型（int）查询所有项目
     *
     * @param type 类型
     * @return list
     */
    @Override
    public List<NonDrugsPay> selectNDrugByType(NonDrugsPay ndp) throws SQLException {
        String mmcode=ndp.getMnemonicCode();
        String name=ndp.getItemName();
        String sql="SELECT F.ID,F.ItemCode,F.ItemName,F.Format,F.Price,F.ExpClassID,F.DeptID," +
                "F.MnemonicCode,F.CreationDate,F.LastUpdateDate,F.RecordType,F.DelMark,E.ExpName,D.DeptName\n" +
                "FROm fmeditem F,expenseClass E,department  D\n" +
                "where F.ExpClassID = E.ID\n" +
                "and F.DeptID = D.ID\n" +
                "and F.DelMark=1\n " +
                "and RecordType = ?\n";

        if (mmcode!=null && mmcode.length()!=0){
            sql+="and F.MnemonicCode like '%"+mmcode+"%'";
        }
        if (name!=null && name.length()!=0){
            sql+="and F.ItemName like '%"+name+"%'";
        }
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,ndp.getRecordType());

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

    /**
     * 新增检查项目,创建时间-系统当前时间
     *
     * @param ca checkapply
     */
    @Override
    public void insertCheckApply(CheckApply ca) throws SQLException {
        String sql1="INSERT INTO checkapply(MedicalID,RegistID,ItemID,Name,CreationTime,DoctorID,State,RecordType) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql1);
        pstmt.setInt(1,ca.getMedicalID());
        pstmt.setInt(2,ca.getRegistID());
        pstmt.setInt(3,ca.getItemID());
        pstmt.setString(4,ca.getName());
        Timestamp createTime=new Timestamp(System.currentTimeMillis());
        pstmt.setTimestamp(5,createTime);
        pstmt.setInt(6,ca.getDoctorID());
        pstmt.setInt(7,ca.getState());
        pstmt.setInt(8,ca.getRecordType());
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);

    }

    /**
     * 查询个人的检查/检验/处置 申请
     *
     * @param registID   挂号ID
     * @param recordType 类型 1-检查 2-检验 3-处置
     * @return
     */
    @Override
    public List<PatientCheckApply> selectPatientCA(int registID, int recordType) throws SQLException {
        String sql1="SELECT c.ID,c.Name,f.ItemName,d.DeptName,c.Isurgent,c.State,f.Price,c.Result " +
                "FROM checkapply c,fmeditem f,department d " +
                "WHERE c.ItemID = f.ID " +
                "and d.ID = f.DeptID " +
                "and c.RegistID = ? " +
                "and c.RecordType = ?";
        PreparedStatement pstmt=con.prepareStatement(sql1);
        pstmt.setInt(1,registID);
        pstmt.setInt(2,recordType);
        ResultSet rs=pstmt.executeQuery();
        List<PatientCheckApply> list=new ArrayList<>();
        PatientCheckApply pca=null;
        while (rs.next()){
            pca=new PatientCheckApply();
            pca.setId(rs.getInt(1));
            pca.setName(rs.getString(2));
            pca.setItemName(rs.getString(3));
            pca.setDeptName(rs.getString(4));
            pca.setIsUrgent(rs.getByte(5));
            pca.setState(rs.getInt(6));
            pca.setPrice(rs.getDouble(7));
            pca.setResult(rs.getString(8));
            list.add(pca);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 更改个人的检查/检验/处置 申请状态
     * 需要设置 id，state   -1-暂存 2-已开立 3-已交费 4-已登记 5-执行完 6-已退费 0-已作废
     *
     */
    @Override
    public void updateCheckApplyState(int id,int state) throws SQLException {
        String sql1="update checkapply set State=? where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql1);
        pstmt.setInt(1,id);
        pstmt.setInt(2,state);
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);
    }


}
