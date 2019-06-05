package service.regist;

import vo.Invoice;
import vo.Register;
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
     * 选择日期-午别-排班科室-挂号级别后
     * 根据看诊日期,午别,排班科室,挂号级别读取当天出诊医生ID,姓名
     * @return list-User对象-id,realname
     */
    List findDoctorInfo(String date,String noon,int deptid,int RegLeID) throws SQLException;

    /**
     * 选择医生之后   - 初始号额、可用号额、应收现金
     * @param docID   医生id
     * @param regLeID   挂号级别id
     * @param visitDate     预约日期
     * @return 初始号额、可用号额、应收现金
     * @throws SQLException
     */
    List findMoneyTicket(int docID,int regLeID,String visitDate) throws SQLException;

    /**
     * 挂号-开发票-插入挂号记录
     * @param reg   CaseNumber-病历号,RealName-患者名,Gender-性别,IDnumber,BirthDate,Age,AgeType-int,
     *      HomeAddress,VisitDate-1,Noon,DeptID,UserID,RegistLeID,SettleID,
     *      IsBook-是否要病历本,RegistTime-系统当前时间（自动设置）,RegisterID,VisitState-1-已挂号（自动设置）
     * @param iv
     * InvoiceNum-发票号,Money-金额,State-1,CreationTime-系统当前时间,UserID-操作人id,RegistID-挂号id,FeeType-收费方式,Back-空,DailyState-空日结
     *
     * @throws SQLException
     */
    //挂号
    void regist(Register reg, Invoice iv) throws SQLException;


}
