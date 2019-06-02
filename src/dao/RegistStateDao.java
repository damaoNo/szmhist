/**
 * * @ClassName: RegistStateDao
 * * @description:
 * * @author: cro
 * * @create: 2019-06-01 22:52
 **/

package dao;

import util.JdbcUtil;
import vo.Invoice;
import vo.RegisterRecord;

import java.sql.*;

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
    public RegisterRecord selectRegistByCaseNumber(String caseNum) throws SQLException {
        String sql ="select r.ID,r.CaseNumber,r.RealName,r.Gender" +
                ",r.IDnumber,r.BirthDate,r.Age,r.AgeType,r.HomeAddress," +
                "r.VisitDate,r.Noon,r.DeptID,r.UserID,r.RegistLeID,r.SettleID," +
                "r.IsBook,r.RegistTime,r.RegisterID,r.VisitState,d.DeptName " +
                "from register r,department d " +
                "where CaseNumber=? and r.DeptID = d.ID and VisitState = 1";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,caseNum);
        ResultSet rs=pstm.executeQuery();
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
        }
        JdbcUtil.release(null,pstm,null);
        return rr;
    }

    /**
     * 更新挂号状态（退号）
     * @param id 挂号id
     * @
     * @author : cro
     */
    @Override
    public void updateRegistState(int id) throws SQLException {
        String sql ="update register set VisitState = 4 where ID = ?;";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,id);
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }

    /**
     * 打印冲红发票，金额为负值,查出的对象传入，金额已做修改为负值处理
     *
     * @param rr 查出的挂号记录对象，利用挂号记录对象的发票号和金额做处理
     * @
     */
    @Override
    public void insertURInvoice(Invoice iv) throws SQLException {
        String sql ="insert into " +
                "invoice(InvoiceNum,Money,State,CreationTime,UserID,RegistID,FeeType,Back,DailyState) " +
                "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,iv.getInvoiceNum());
        pstm.setDouble(2,iv.getMoney());
        pstm.setInt(3,iv.getState());
        pstm.setDate(4,new Date(System.currentTimeMillis()));
        pstm.setInt(5,iv.getUserID());
        pstm.setInt(6,iv.getRegistID());
        pstm.setInt(7,iv.getFeeType());
        pstm.setString(8,iv.getBack());
        pstm.setInt(9,iv.getDailyState());
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }


}
