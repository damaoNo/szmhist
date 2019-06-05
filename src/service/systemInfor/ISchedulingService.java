package service.systemInfor;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public interface ISchedulingService {
    /*读取当前排班信息*/
    public List schedInfoNow(Date date1, Date date2, int page) throws SQLException;
    /*批量删除排班计划*/
    public void deleteSchedInfo(String[] ID) throws SQLException;
    /*读取有效科室*/
    public List depaEffctive() throws SQLException;
    /*读取有效挂号级别*/
    public List registCode() throws SQLException;
    /*按条件读取排版规则*/
    public List ruler(int DeptID);
    /**
     * 组合查询医生名称RealName
     * 科室名称DeptName
     * 或者挂号级别RegistName
     */
    public List selectRN(String DeptName, String RegistName) throws SQLException;
}
