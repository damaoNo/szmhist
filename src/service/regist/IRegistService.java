package service.regist;

import vo.*;

import java.sql.SQLException;
import java.util.Date;
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

//获取有班的科室名字和对应id
    List getDeptNames(Date date,String noon) throws SQLException;

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
    void regist(Register reg, Invoice iv) throws SQLException;


    /**
     * 退号-查询
     * 按病历号读取当前状态为未看诊的挂号记录
     * @param caseNum 病历号
     * @return 挂号记录对象
     * @throws SQLException sqlExp
     */
    List<RegisterRecord> findRegistByCaseNumber(String caseNum) throws SQLException;



    /**
     * 更新挂号状态（退号）
     * @param id 挂号id
     * @param state 状态-4
     */
    void backRegist(int id) throws SQLException;


//收费

    /**
     * 查询所有收费项目
     * @return 所有收费项目
     */
    List findAllCosts(String caseNum) throws SQLException;
    /**
     * 点击确定缴费后加载-下一个可用发票号、结算类别
     * @param userid 当前挂号员id
     * @return 下一个可用发票号、结算类别
     */
    List findInvoicePay(int userid) throws SQLException;

    /**
     * 交费
     * @param flag 6-处方状态已缴费--9-处置表状态已缴费
     * @param id 对应的批量id
     * @param iv 发票对象\InvoiceNum-需设置,Money-需设置,State-自动设置,
     *           CreationTime-自动设置,UserID-挂号员id 需设置,RegistID-需设置,
     *           FeeType-需设置，结账类别,Back,DailyState
     * @param pc RegistID-挂号id（iv获取）,InvoiceID-自动设置,ItemID-需设置？？,ItemType-自动设置 1-非2-药,
     *           Name-需设置,Price-需设置,Amount,DeptID,Createtime-自动设置,
     *           CreateOperID-开立人员ID,PayTime-支付时间,RegisterID-（iv.userID）,
     *           FeeType-（iv.feetype）,BackID
     *
     * @param state 3--缴费    6--退费
     * @throws SQLException
     */
    void pay(int flag,int[] id,Invoice iv,PatientCosts pc,int state) throws SQLException;
//退费
    /**
     * 查询所有可退费项目-----处置/../..申请 药方 为已缴费状态
     * @param caseNum
     * @return
     * @throws SQLException
     */
    List backpay(String caseNum) throws SQLException;

    /**
     * 根据病历号查询病人的消费信息
     * @return
     */
    List findPatientCosts(String caseNum) throws SQLException;





}
