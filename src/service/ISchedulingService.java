package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ISchedulingService {
    /*读取当前排班信息*/
    public List schedInfoNow(Date date1, Date date2) throws SQLException;
    /*读取有效科室*/
    public List depaEffctive() throws SQLException;
    /*读取有效挂号级别*/
    public List registCode() throws SQLException;
    /*按条件读取排版规则*/
    public List ruler(int DeptID);
}
