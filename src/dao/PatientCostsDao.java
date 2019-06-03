/**
 * * @ClassName: PatientCostsDao
 * * @description: 患者消费
 * * @author: cro
 * * @create: 2019-06-03 11:37
 **/

package dao;

import util.JdbcUtil;
import vo.PatientCosts;
import vo.PatientCostsBack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientCostsDao implements IPatientCostsDao{

    Connection con=null;

    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }

    /**
     * 根据病历号查询病人的消费信息
     *
     * @return
     */
    @Override
    public List<PatientCostsBack> selectPatientCosts(String caseNum) throws SQLException {
        String sql="select r.RealName,r.IDnumber,r.HomeAddress,r.CaseNumber," +
                "p.`Name`,p.Price,p.Amount,p.Createtime,r.VisitState " +
                "from register r,patientcosts p " +
                "where r.RegisterID=p.RegisterID " +
                "and r.CaseNumber=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,caseNum);
        ResultSet rs=pstm.executeQuery();
        List<PatientCostsBack> list=new ArrayList<>();
        PatientCostsBack pcb=null;
        while (rs.next()){
            pcb=new PatientCostsBack();
            pcb.setRealName(rs.getString(1));
            pcb.setIdNum(rs.getInt(2));
            pcb.setHomeAdd(rs.getString(3));
            pcb.setCaseNum(rs.getString(4));
            pcb.setName(rs.getString(5));
            pcb.setPrice(rs.getDouble(6));
            pcb.setAmount(rs.getDouble(7));
            pcb.setCreateTime(rs.getDate(8));
            pcb.setVisitState(rs.getInt(9));
            list.add(pcb);
        }
        JdbcUtil.release(null,pstm,null);
        return list;
    }

    /**
     * @param pc
     * @Description: 记录患者费用明细,创建时间和付钱时间需要设置
     * @Param: [pc]
     * @return: void
     * @Author: cro
     * @Date: 2019/6/1
     */
    @Override
    public void insertPatientCosts(PatientCosts pc) throws SQLException {
        String sql ="insert into " +
                "patientcosts(RegistID,InvoiceID,ItemID,ItemType,Name,Price," +
                "Amount,DeptID,Createtime,CreateOperID,PayTime,RegisterID,FeeType,BackID) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,pc.getRegistID());
        pstm.setInt(2,pc.getInvoiceID());
        pstm.setInt(3,pc.getItemID());
        pstm.setInt(4,pc.getItemType());
        pstm.setString(5,pc.getName());
        pstm.setDouble(6,pc.getPrice());
        pstm.setDouble(7,pc.getAmount());
        pstm.setInt(8,pc.getDeptID());
        Timestamp creatTime=new Timestamp(System.currentTimeMillis());
        pstm.setTimestamp(9,creatTime);
        pstm.setInt(10,pc.getCreateOperID());
        pstm.setTimestamp(11,pc.getPayTime());
        pstm.setInt(12,pc.getRegisterID());
        pstm.setInt(13,pc.getFeeType());
        pstm.setInt(14,pc.getBackID());
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }
}
