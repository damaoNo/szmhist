/**
 * @program: szmhist
 * * @description: 现场挂号
 * * @author:cro
 * * @create: 2019-05-31 15:55
 **/

package dao;

import util.JdbcUtil;
import vo.Department;
import vo.RegistLevel;
import vo.SettleCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistDao implements IRegistDao{

    Connection con = null;

    public void setConnection(Connection con) {
        this.con = con;
    }

    /**
     * 读取收费员当前最大发票号
     * @param userid 收费员ID
     * @return 收费员下一个可用发票号
     */
    @Override
    public String selectMaxInvoiceNum(int userid) throws SQLException {
        String sql ="select max(InvoiceNum)+1 from invoice where UserID=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,userid);
        ResultSet rs = pstm.executeQuery();
        String invoiceNum=null;
        while (rs.next()){
            invoiceNum=rs.getString(1);
        }
        JdbcUtil.release(null,pstm,null);
        return invoiceNum;
    }


    /**
     * 读取当前最大病历号
     *
     * @return 返回下一个可用的病历号
     */
    @Override
    public String selectMaxCaseNum() throws SQLException {
        String sql ="select max(CaseNumber)+1 from register";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        String caseNum=null;
        while (rs.next()){
            caseNum=rs.getString(1);
        }
        JdbcUtil.release(null,pstm,null);
        return caseNum;
    }

    /**
     * 读取有效结算类别
     * @return id、编码、结算名称
     */
    @Override
    public List selectSettleCategories() throws SQLException {
        String sql ="select id,SettleCode,SettleName from settlecategory where DelMark=1 order by SequenceNo";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<SettleCategory> settleCategories=new ArrayList<>();
        SettleCategory category=new SettleCategory();
        while (rs.next()){
            category=null;
            category.setId(rs.getInt(1));
            category.setSettleCode(rs.getString(2));
            category.setSettleName(rs.getString(3));
            settleCategories.add(category);
        }
        JdbcUtil.release(null,pstm,null);
        return settleCategories;
    }

    /**
     * 选择有效挂号级别
     *
     * @return list-registlevel
     */
    @Override
    public List selectRegistLevels() throws SQLException {
        String sql ="select id,RegistCode,RegistName from registlevel where DelMark=1 order by SequenceNo";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<RegistLevel> list=new ArrayList<>();
        RegistLevel rl=new RegistLevel();
        while (rs.next()){
            rl=null;
            rl.setId(rs.getInt(1));
            rl.setRegistCode(rs.getString(2));
            rl.setRegistName(rs.getString(3));
            list.add(rl);
        }
        JdbcUtil.release(null,pstm,null);
        return list;
    }

    /**
     * 根据ID获取挂号费和初始号额
     *
     * @param id registLevel-id
     * @return 返回一个封装了挂号费和初始号额的registlevel对象
     */
    @Override
    public RegistLevel selectRegistLevelByID(int id) throws SQLException {
        String sql ="select RegistFee,RegistName from registlevel where id=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1,id);
        ResultSet rs = pstm.executeQuery();
        RegistLevel rl=new RegistLevel();
        while (rs.next()){
            rl=null;
            rl.setRegistFree(rs.getDouble(1));
            rl.setRegistName(rs.getString(2));
        }
        JdbcUtil.release(null,pstm,null);
        return rl;
    }

    /**
     * 读取有效临床科室
     *
     * @return list-department对象，id,registcode,registname
     */
    @Override
    public List selectDepartment() throws SQLException {
        String sql ="select id,deptcode,deptname from department where Depttype=1 and DelMark=1";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Department> list=new ArrayList<>();
        Department dpmt=new Department();
        while (rs.next()){
            dpmt=null;
            dpmt.setId(rs.getInt(1));
            dpmt.setDeptCode(rs.getString(2));
            dpmt.setDeptName(rs.getString(3));
            list.add(dpmt);
        }
        JdbcUtil.release(null,pstm,null);
        return list;
    }

    /**
     * 根据看诊日期,午别,排班科室,挂号级别读取当天出诊医生ID,姓名
     *
     * @return list-User对象-id,realname
     */
    @Override
    public List selectDoctorInfo() throws SQLException {
        String sql ="select user.id,user.realname from scheduling,user "
                +"where scheduling.UserId = user.DeptId "
                +"and scheduling.SchedDate = ? "
                +"and scheduling.Noon = ? "
                +"and scheduling.DeptID = ? "
                +"and user.RegistLeID = ? "
                ;
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Department> list=new ArrayList<>();
        Department dpmt=new Department();
        while (rs.next()){
            dpmt=null;
            dpmt.setId(rs.getInt(1));
            dpmt.setDeptCode(rs.getString(2));
            dpmt.setDeptName(rs.getString(3));
            list.add(dpmt);
        }
        JdbcUtil.release(null,pstm,null);
        return list;
    }



}
