package dao;

import vo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Cro
 */
public interface IRegistDao {

    void setConnection(Connection con);


    /**
     * 读取当前最大病历号
     * @return 返回下一个可用的病历号
     */
    String selectMaxCaseNum() throws SQLException;

    /**
     * 读取有效结算类别
     * @return id、编码、结算名称
     */
    List selectSettleCategories() throws SQLException;

    /**
     * 选择有效挂号级别
     * @return list-registlevel
     */
    List selectRegistLevels() throws SQLException;


    /**
     * 根据ID获取挂号费和初始号额
     * @param id registLevel-id
     * @return 返回一个封装了挂号费和初始号额的registlevel对象
     */
    RegistLevel selectRegistLevelByID(int id) throws SQLException;

    /**
     * 读取有效临床科室
     * @return list-department对象，id,registcode,registname
     */
    List selectDepartment() throws SQLException;

    /**
     * 根据看诊日期,午别,排班科室,挂号级别读取当天出诊医生ID,姓名
     * @return list-User对象-id,realname
     */
    List selectDoctorInfo(SchedDoctor sd) throws SQLException;
    /**
    * @Description:  根据选中医生读取当日已用号额
    * @Param: [userId] 医生ID
    * @return: int 已用号额，当天共有多少人已预约
    * @Author: cro
    * @Date: 2019/6/1
    */
    int selectDoctorUsedId(Register reg) throws SQLException;
    /**
    * @Description: 插入挂号记录,挂号时间为系统当前时间(需要设置)
    * @Param: [reg]
    * @return: java.lang.Boolean 是否插入成功
    * @Author: cro
    * @Date: 2019/6/1
    */
    Boolean insertRegist(Register reg) throws SQLException;


    /**
     * 根据挂号ID 修改对应数据VisitState属性1-已挂号 2-医生接诊 3-看诊结束 4-已退号
     * @param regID 挂号ID
     */
    void updateVisitState(int regID,int state) throws SQLException;
    //通过病历号查找对象
    public Register getRegisterByCaseNumber(String casen) throws SQLException;
    //通过id查找注册对象
    public Invoice getInfByRegistid(int registid) throws SQLException;

    /**
     * 根据病历号和创建时间获取挂号id
     * @param creatTime 创建时间
     * @param caseNum 病历号
     * @return 挂号id
     */
    int selectRegistIDByTime(String  creatTime, String caseNum) throws SQLException;

    /**
     * 根据科室名获取id
     * @param deptname 科室名称
     */
    int getDeptIDbyName(String deptname) throws SQLException;

    /**
     * 根据id获取科室名
     * @param id
     * @return
     */
    String getDeptNameByID(int id) throws SQLException;


}
