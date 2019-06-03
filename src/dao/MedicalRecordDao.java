/**
 * * @ClassName: MedicalRecordDao
 * * @description:
 * * @author: cro
 * * @create: 2019-06-03 13:45
 **/

package dao;

import util.JdbcUtil;
import vo.Department;
import vo.MedicalDisease;
import vo.MedicalRecord;
import vo.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql="SELECT * FROM medicalrecord WHERE CaseNumber=?";
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
     * 将诊断结果更新到数据库中-更新病历首页内容
     *
     * @param mr 诊断结果对象
     */
    @Override
    public void updateMedRecord(MedicalRecord mr) throws SQLException {
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
     * 根据挂号ID 修改对应数据VisitState属性1-已挂号 2-医生接诊 3-看诊结束 4-已退号
     *
     * @param regID 挂号ID
     */
    @Override
    public void updateVisitState(String regID,int state) throws SQLException {
        String sql="UPDATE register set VisitState=? where RegistID=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,state);
        pstmt.setString(2,regID);
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
        String sql="SELECT CaseNumber,RealName,Age,Gender FROM register WHERE VisitState=3 and DeptID=?";
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


}
