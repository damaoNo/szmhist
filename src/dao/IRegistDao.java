package dao;

import vo.RegistLevel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Cro
 */
public interface IRegistDao {

    void setConnection(Connection con);
    /**
     * 读取收费员当前最大发票号
     * @param userid 收费员ID
     * @return 收费员下一个可用发票号
     */
    String selectMaxInvoiceNum(int userid) throws SQLException;

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
    List selectDoctorInfo() throws SQLException;
}
