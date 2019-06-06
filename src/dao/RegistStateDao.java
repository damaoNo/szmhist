/**
 * * @ClassName: RegistStateDao
 * * @description:
 * * @author: cro
 * * @create: 2019-06-01 22:52
 **/

package dao;

import util.JdbcUtil;
import vo.Invoice;
import vo.PatientCosts;
import vo.PatientCostsBack;
import vo.RegisterRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistStateDao implements IRegistStateDao {
    Connection con=null;

    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }

    /**
     * @param caseNum
     * @description: 按病历号读取当前状态为未看诊的挂号记录
     * @param: [caseNum]
     * @return: vo.RegisterRecord
     * @author: cro
     * @date: 2019/6/2
     */
    @Override
    public List<RegisterRecord> selectRegistByCaseNumber(String caseNum) throws SQLException {
        String sql ="select r.ID,r.CaseNumber,r.RealName,r.Gender," +
                "r.IDnumber,r.BirthDate,r.Age,r.AgeType,r.HomeAddress," +
                "r.VisitDate,r.Noon,r.DeptID,r.UserID,r.RegistLeID,r.SettleID," +
                "r.IsBook,r.RegistTime,r.RegisterID,r.VisitState,d.DeptName " +
                "from register r,department d " +
                "where CaseNumber=? and r.DeptID = d.ID and VisitState = 1";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,caseNum);
        ResultSet rs=pstm.executeQuery();
        List<RegisterRecord> list=new ArrayList<>();
        RegisterRecord rr=null;
        while (rs.next()){
            rr=new RegisterRecord();
            rr.setId(rs.getInt(1));
            rr.setCaseNumber(rs.getString(2));
            rr.setRealName(rs.getString(3));
            rr.setGender(rs.getInt(4));
            rr.setIdNumber(rs.getString(5));
            rr.setBirthDate(rs.getDate(6));
            rr.setAge(rs.getInt(7));
            char ageType=rs.getString(8).charAt(0);
            rr.setAgeTpye(ageType);
            rr.setHomeAddress(rs.getString(9));
            rr.setVisitDate(rs.getDate(10));
            rr.setNoon(rs.getString(11));
            rr.setDeptID(rs.getInt(12));
            rr.setUserID(rs.getInt(13));
            rr.setRegistLeID(rs.getInt(14));
            rr.setSettLeID(rs.getInt(15));
            rr.setIsBook(rs.getString(16).charAt(0));
            rr.setRegistTime(rs.getDate(17));
            rr.setRegistLeID(rs.getInt(18));
            rr.setVisitState(rs.getInt(19));
            rr.setDeptName(rs.getString(20));
            list.add(rr);
        }
        JdbcUtil.release(null,pstm,null);
        return list;
    }

    /**
     * 更新挂号状态（退号）
     * @param id 挂号id
     * @
     * @author : cro
     */
    @Override
    public void updateRegistState(int id,int state) throws SQLException {
        String sql ="update register set VisitState = ? where ID = ?;";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,state);
        pstm.setInt(2,id);
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }




}
