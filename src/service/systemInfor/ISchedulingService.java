package service.systemInfor;

import vo.Rule;
import vo.Scheduling;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public interface ISchedulingService {

    /**
     * 读取当前排班信息
     * @param begin 开始日期
     * @param end 结束日期
     * @param page 第几页页数
     * @return
     * @throws SQLException
     */
    public List schedInfoNow(Date begin, Date end, int page) throws SQLException;
    /**/

    /**
     * 批量删除排班计划
     * @param ID 排班计划ID
     * @throws SQLException
     */
    public void deleteSchedInfo(String[] ID) throws SQLException;

    /**
     * 读取有效科室
     * @return
     * @throws SQLException
     */
    public List depaEffctive() throws SQLException;

    /**
     * 读取有效挂号级别
     * @return
     * @throws SQLException
     */
    public List registCode() throws SQLException;
    /**
     * 组合查询医生名称RealName
     * 科室名称DeptName
     * 或者挂号级别RegistName
     */
    public List selectRN(String DeptName, String RegistName) throws SQLException;

    /**
     * 新增排班规则
     * @param rule
     */
    public void addRuler(Rule rule) throws SQLException;

    /**
     * 按科室ID读取排班规则
     * @param DeptID
     * @return
     */
    public List ruler(int DeptID);
    /**
     * 选取规则生成排班计划
     */
    public void addScheduling(Scheduling scheduling) throws SQLException;

}
