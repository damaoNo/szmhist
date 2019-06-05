package dao;

import util.JdbcUtil;
import vo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * 设置连接 setConnection（Connectin con）
 * 查询当前排班信息 schedInfoNow(Date begin, Date end)
 * 读取有效科室 depaEffctive()
 * 读取有效挂号级别 registCode()
 * 读取排班规则 ruler(int DeptID)
 * 新增排班规则 addRuler(Rule ruler)
 * 选取规则生产排班计划 addScheduling(Scheduling scheduling)
 * 批量删除排班计划 delScheduling(String[] ID)
 *
 */
public class SchedulingDao implements ISchedulingDao {
    Connection con=null;

    /**
     *设置连接
     * @param con
     */
    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }


    /**
     * 根据“排班日期”查询当前排班信息:排班表（id 排班日期 排班午别） 科室表（科室名称）
     * 用户表（真实姓名） 挂号级别表（号别名称(挂号级别) 挂号限额）
     * @param begin
     * @param end
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public List schedInfoNow(Date begin,Date end, int page) throws SQLException {
        java.sql.Date begin1=new java.sql.Date(begin.getTime());
        java.sql.Date end1=new java.sql.Date(end.getTime());

        String sql ="select S.ID,S.SchedDate,S.Noon,D.DeptName,U.RealName,R.RegistName,R.RegistQuota\n" +
                "FROM scheduling S,department D,user U,registlevel R\n" +
                "where S.DeptID = D.ID\n" +
                "and S.UserID = U.ID\n" +
                "and U.RegistLeID = R.ID\n" +
                "and S.SchedDate between ? And ? " +
                "limit ?,10";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setDate(1,begin1);
        pstmt.setDate(2,end1);
        pstmt.setInt(3,(page-1)*10);
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

    /**
     *
     * @param date1 开始日期
     * @param date2 结束日期
     * @return
     * @throws SQLException
     */
    @Override
    public int scinPages(Date begin, Date end) throws SQLException {
        java.sql.Date begin1=new java.sql.Date(begin.getTime());
        java.sql.Date end1=new java.sql.Date(end.getTime());

        String sql ="select count(S.ID)\n" +
                "FROM scheduling S,department D,user U,registlevel R\n" +
                "where S.DeptID = D.ID\n" +
                "and S.UserID = U.ID\n" +
                "and U.RegistLeID = R.ID\n" +
                "and S.SchedDate between ? And ? ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setDate(1,begin1);
        pstmt.setDate(2,end1);
        //查询
        /*返回一个结果集*/
        ResultSet rs=pstmt.executeQuery();
        int pages=0;
        while(rs.next()){
            pages=rs.getInt(1);
        }
        int pagenum=0;
        if (pages%10==0){
            pagenum=pages/10;
        }else {
            pagenum=pages/10+1;
        }
        return pagenum;
    }



    /**
     * 读取有效科室
     * 查询ID,科室编码，科室名称，科室分类，科室类型，删除标记
     * @return
     * @throws SQLException
     */
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


    /**
     * 读取有效挂号级别
     * 查询 挂号级别表id,号别编码RegistCode，号别级别RegistName
     * @return
     * @throws SQLException
     */
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

    /**
     * 读取排班规则
     * 查询 排版规则ID ，规则名称RuleName， 科室ID DeptID， 医生ID UserID，
     * 星期Week 14位1和0 组成的字符串，1代表有班 0代表无班 14位字符串从左到右依次表示表示星期1-星期日，每天两位表示上下午
     * 删除标记DelMark
     * @param DeptID
     * @return
     * @throws SQLException
     */
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
    /**
     * 组合查询医生名称RealName，科室ID  DeptID
     * 科室名称DeptName
     * 或者挂号级别RegistName
     */
    @Override
    public List selectRN(String DeptName, String RegistName) throws SQLException {
        String sql="SELECT U.RealName,D.DeptID FROM user U, registlevel R, department D\n" +
                " WHERE U. RegistLeID =R.ID AND U. DeptID=D. ID \n";
        if (DeptName !=null && DeptName.length()!=0){
            sql +=" AND DeptName='"+ DeptName +"'";
        }
        if (RegistName!=null && RegistName.length()!=0){
            sql +=" AND RegistName='" + RegistName +"'";
        }
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        User user=null;
        List list=new ArrayList();
        while (rs.next()){
            user=new User();
            user.setRealName(rs.getString(1));
            user.setDeptid(rs.getInt(2));
            list.add(user);
        }
        JdbcUtil.release(null,pstmt,null);
        return list;
    }


    /**
     * 新增排班规则
     * 插入：RuleName,DeptID,UserID,Week
     * @param ruler
     * @throws SQLException
     */
    @Override
    public void addRuler(Rule ruler) throws SQLException {
      String sql="INSERT INTO rule (RuleName,DeptID,UserID,Week) VALUES " +
              "(?,?,?,?)";
      PreparedStatement pstmt=con.prepareStatement(sql);
      pstmt.setString(1,ruler.getRuleNmae());
      pstmt.setInt(2,ruler.getDeptID());
      pstmt.setInt(3,ruler.getUserID());
      pstmt.setString(4,ruler.getWeek());
      pstmt.executeUpdate();
      JdbcUtil.release(null,pstmt,null);
    }
    /**/

    /**
     * 选取规则生产排班计划
     * 插入：ScheDate排班日期,DeptID科室ID,UserID医生ID,Noon午别,RuleID排班规则ID
     * @param scheduling
     * @throws SQLException
     */
    @Override
    public void addScheduling(Scheduling scheduling) throws SQLException {
        String sql="INSERT INTO scheduling (ScheDate,DeptID,UserID,Noon,RuleID) " +
                "VALUES (?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        java.sql.Date Date=new java.sql.Date(scheduling.getSchedDate().getTime());
        pstmt.setDate(1,Date);
        pstmt.setInt(2,scheduling.getDeptID());
        pstmt.setInt(3,scheduling.getUserID());
        pstmt.setString(4,scheduling.getNoon());
        pstmt.setInt(5,scheduling.getRuleID());
        pstmt.executeUpdate();
        JdbcUtil.release(null,pstmt,null);
    }

    /**
     * 批量删除排班计划
     * 删除：
     * @param ID
     * @throws SQLException
     */
    @Override
    public void delScheduling(String[] ID) throws SQLException {
        String sql="delete \n" +
                "FROm scheduling\n" +
                "where ID= ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        for (int i=0;i<ID.length;i++){
            pstmt.setString(1,ID[i]);
            pstmt.addBatch();
            if(i%10==0){
                pstmt.executeBatch();
            }
        }
        pstmt.executeBatch();
        JdbcUtil.release(null, pstmt, null);
    }


}
