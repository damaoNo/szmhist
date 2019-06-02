package dao;

import vo.Scheduling;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ISchedulingDao {
    /*dao层接受业务层的对象开启连接 */
    public void setConnection(Connection con);
    /*读取当前排班信息*/
    public List schedInfoNow(Date date1, Date date2) throws SQLException;
    /*读取有效科室*/
    public List depaEffctive() throws SQLException;
    /*读取有效挂号级别*/
    public List registCode() throws SQLException;
    /*按条件读取排班规则*/
    public List ruler(int DeptID) throws SQLException;
}
