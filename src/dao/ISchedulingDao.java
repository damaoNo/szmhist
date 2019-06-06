package dao;

import vo.Rule;
import vo.Scheduling;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ISchedulingDao {
    /*dao层接受业务层的对象开启连接 */
    public void setConnection(Connection con);
    /*读取当前排班信息*/
    public List schedInfoNow(Date date1, Date date2, int page) throws SQLException;
    /*医生排班总页数*/
    public int scinPages(Date date1, Date date2) throws SQLException;
    /*读取有效科室*/
    public List depaEffctive() throws SQLException;
    /*读取有效挂号级别*/
    public List registCode() throws SQLException;
    /*按条件读取排班规则*/
    public List ruler(int DeptID) throws SQLException;
    /*新增排班规则*/
    public void addRuler(Rule rule) throws SQLException;
    /*选取规则生成排班计划*/
    public void addScheduling(Scheduling scheduling) throws SQLException;
    /*批量删除排班计划*/
    public void delScheduling(String[] ID) throws SQLException;
    /**
     * 组合查询医生名称RealName
     * 科室名称DeptName
     * 或者挂号级别RegistName
     */
    public List selectRN(String DeptName,String RegistName) throws SQLException;
}
