package service.regist;

import vo.SchedDoctor;

import java.sql.SQLException;
import java.util.List;

/**
 * * @ClassName: RegistService
 * * @description: 现场挂号
 * * @author: cro
 * * @create: 2019-06-05 09:34
 **/
public interface IRegistService {
    /**
     * 挂号界面加载-下一个可用发票号、下一个可用病历号、结算类别
     * @param userid 当前挂号员id
     * @return 下一个可用发票号、下一个可用病历号、结算类别
     */
    List invoiceCaseNumPay(int userid) throws SQLException;

    /**
     * 选择日期-午别-排班科室后
     * 根据看诊日期,午别,排班科室,挂号级别读取当天出诊医生ID,姓名
     * @return list-User对象-id,realname
     */
    List findDoctorInfo(String date,String noon,String  deptName,String  regLeName) throws SQLException;
}
