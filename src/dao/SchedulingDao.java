package dao;

import util.JdbcUtil;
import vo.Department;
import vo.DoctorInfo;
import vo.RegistLevel;
import vo.Rule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchedulingDao implements ISchedulingDao {
    Connection con=null;
    //设置连接

    /**
     *
     * @param con
     */
    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }
    /*根据“排班日期”查询当前排班信息:排班表（id 排班日期 排班午别） 科室表（科室名称）用户表（真实姓名）
                    挂号级别表（号别名称(挂号级别) 挂号限额）*/
    @Override
    public List schedInfoNow(Date date1, Date date2) throws SQLException {
        String sql ="select S.ID,S.SchedDate,S.Noon,D.DeptName,U.RealName,R.RegistName,R.RegistQuota\n" +
                "FROM scheduling S,department D,user U,registlevel R\n" +
                "where S.DeptID = D.ID\n" +
                "and S.UserID = U.ID\n" +
                "and U.RegistLeID = R.ID\n" +
                "and S.SchedDate between ? And ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setDate(1,date1);
        pstmt.setDate(2,date2);
        //查询
        /*返回一个结果集*/
        ResultSet rs=pstmt.executeQuery();
        System.out.println("rs  :"+rs.next());
        /*从结果集里面取出来封装到DoctorInfo对象*/
        DoctorInfo di=null;
        //初始化一个List用来存放di数据
        List list=new ArrayList();
        while(rs.next()){
            di=new DoctorInfo();
            di.setID(rs.getInt(1));
            di.setSchedDate(rs.getDate(2));
            di.setNoon(rs.getString(3));
            di.setDeptName(rs.getString(4));
            di.setRealName(rs.getString(5));
            di.setRegistName(rs.getString(6));
            di.setRegistQuota(rs.getInt(7));
            list.add(di);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;
    }

    /*读取有效科室*/
    //查询ID,科室编码，科室名称，科室分类，科室类型，删除标记
    @Override
    public List depaEffctive() throws SQLException {
        String sql="select ID,DeptCode,DeptName,DeptCategoryID,DeptType,DelMark \n" +
                "FROM department \n" +
                "where DelMark = 1\n";
        PreparedStatement pstmt=con.prepareStatement(sql);
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

    /*读取有效挂号级别*/
    @Override
    public List registCode() throws SQLException {
        String sql="select id,RegistCode,RegistName \n" +
                "from RegistLevel  \n" +
                "where DelMark = 1 \n" +
                "order by SequenceNo\n";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        RegistLevel reg=null;
        List list=new ArrayList();
        while (rs.next()){
            reg=new RegistLevel();
            reg.setId(rs.getInt(1));
            reg.setRegistCode(rs.getString(2));
            reg.setRegistName(rs.getString(3));
            list.add(reg);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;

    }
    /*读取排班规则*/
    @Override
    public List ruler(int DeptID) throws SQLException {
        String sql="select ID,RuleName,DeptID,UserID,Week,DelMark\n" +
                "from rule\n" +
                "where DeptID = ?\n" +
                "And DelMark=1";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,DeptID);
        ResultSet rs=pstmt.executeQuery();
        Rule rule=null;
        List list=new ArrayList();
        while (rs.next()){
            rule=new Rule();
            rule.setId(rs.getInt(1));
            rule.setRuleNmae(rs.getString(2));
            rule.setDeptID(rs.getInt(3));
            rule.setUserID(rs.getInt(4));
            rule.setWeek(rs.getString(5));
            rule.setDelMark(rs.getInt(6));
            list.add(rule);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;
    }


}
