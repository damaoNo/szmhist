/**
 * @program: szmhist
 * * @description:
 * * @author:cro
 * * @create: 2019-05-31 16:08
 **/

package service.regist;

import vo.*;

import java.sql.SQLException;
import java.util.List;

public interface IRegistService {

    /**
     * 读取收费员当前最大发票号
     * @param userId 当前收费员ID
     * @return 收费员最大发票号
     */
    String findMaxInvoiceNum(int userId) throws SQLException;

    /**
     * 查询最大病历号
     * @return 返回下一个可用的病历号
     */
    String findMaxCaseNum() throws SQLException;

    /**
     * 读取所有可用结算类别
     * @return list-settlecategories类 id，结算编号，结算类别名字
     */
    List readSettleCategories() throws SQLException;

    /**
     * 读取有效挂号级别
     * @return list-registlevel-id,registcode,registname
     */
    List readRegistLevels() throws SQLException;

    /**
     * 根据ID获取挂号费和初始号额
     * @param id registLevel-id
     * @return 返回一个封装了挂号费和初始号额的registlevel对象
     */
    RegistLevel findRegistLevelByID(int id) throws SQLException;

    /**
     * 读取有效临床科室
     * @return list-department对象，id,registcode,registname
     */
    List findDepartment() throws SQLException;

    /**
     * 根据看诊日期,午别,排班科室,挂号级别读取当天出诊医生ID,姓名
     * @return list-User对象-id,realname
     */
    List findDoctorInfo(SchedDoctor sd) throws SQLException;

    /**
     * @Description:  根据选中医生读取当日已用号额
     * @Param: [userId] 医生ID
     * @return: int 已用号额，当天共有多少人已预约
     * @Author: cro
     * @Date: 2019/6/1
     */
    int findDoctorUsedId(Register reg) throws SQLException;

    /**
     * @Description: 现场挂号,挂号时间为系统当前时间
     * @Param: [reg]
     * @return: java.lang.Boolean 是否插入成功
     * @Author: cro
     * @Date: 2019/6/1
     */
    boolean regist(Register reg) throws SQLException;

    /**
     * @Description: 使用发票记录,创建时间自动设置为当前系统时间
     * @Param: [iv]
     * @return: boolean 是否插入成功
     * @Author: cro
     * @Date: 2019/6/1
     */
    boolean useInvoice(Invoice iv) throws SQLException;

}
