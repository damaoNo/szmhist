

package service.regist;

import dao.*;
import util.JdbcUtil;
import vo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * * @ClassName: RegistService
 * * @description: 现场挂号
 * * @author: cro
 * * @create: 2019-06-05 09:34
 **/
public class RegistService implements IRegistService {
    /**
     * 挂号界面加载-下一个可用发票号、下一个可用病历号、结算类别、
     *
     * @param userid 挂号员id
     * @return 下一个可用发票号、下一个可用病历号、结算类别
     */
    @Override
    public List invoiceCaseNumPay(int userid) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        List list1=new ArrayList();
        List list3=new ArrayList();
        List list2=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao rd=new RegistDao();
            rd.setConnection(con);
            IInvoiceDao id=new InvoiceDao();
            id.setConnection(con);
            String invoiceNum=id.selectMaxInvoiceNum(userid);
            String caseNum=rd.selectMaxCaseNum();

            list1=rd.selectSettleCategories();
//            list2=rd.selectDepartment();
            list3=rd.selectRegistLevels();
            list.add(invoiceNum);
            list.add(caseNum);


            list.add(list1);
//            list.add(list2);
            list.add(list3);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    @Override
    public List getDeptNames(Date date, String noon) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao rd=new RegistDao();
            rd.setConnection(con);
            list=rd.selectDeptNameByDate(date,noon);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /**
     * 选择日期-午别-排班科室后
     * 根据看诊日期,午别,排班科室,挂号级别读取当天出诊医生ID,姓名
     * @param sd
     * @return list-User对象-id,realname
     */
    @Override
    public List findDoctorInfo(String date,String noon,int deptID,int regLeID) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao rd=new RegistDao();
            rd.setConnection(con);
            SchedDoctor sc=new SchedDoctor();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            sc.setSchedDate(df.parse(date));
            sc.setNoon(noon);
            sc.setDeptID(deptID);
            sc.setRegistLeID(regLeID);
            list=rd.selectDoctorInfo(sc);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 选择医生之后   - 初始号额、可用号额、应收现金
     * @param docID   医生id
     * @param regLeID   挂号级别id
     * @param visitDate     预约日期
     * @return 初始号额、可用号额、应收现金
     * @throws SQLException
     */
    @Override
    public List findMoneyTicket(int docID,int regLeID,String visitDate) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao rd=new RegistDao();
            rd.setConnection(con);
            //查询初始号额、应收现金
            RegistLevel rl=rd.selectRegistLevelByID(regLeID);
            //查询医生的已用号额
            Register reg=new Register();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            reg.setUserID(docID);
            reg.setVisitDate(df.parse(visitDate));
            int usedNum=rd.selectDoctorUsedId(reg);

            list.add(rl);
            list.add(usedNum);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }
    /**
     * 挂号-开发票-插入挂号记录
     * @param reg   CaseNumber-病历号,RealName-患者名,Gender-性别,IDnumber,BirthDate,Age,AgeType-int,
     *      HomeAddress,VisitDate-1,Noon,DeptID,UserID,RegistLeID,SettleID,
     *      IsBook-是否要病历本,RegistTime-系统当前时间（自动设置）,RegisterID,VisitState-1-已挂号（自动设置）
     * @param iv
     * InvoiceNum-发票号,Money-金额,State-1,CreationTime-系统当前时间,UserID-操作人id,RegistID-挂号id,FeeType-收费方式,Back-空,DailyState-空日结
     *
     * @param pc    RegistID-挂号ID,InvoiceID-发票ID,ItemID-物品ID（1）,ItemType（1）,Name（挂号费）,Price-价格," +
     * "Amount（1）,DeptID（1）,Createtime-开立时间,CreateOperID-开立人员id,PayTime（缴费时间-当前时间）,RegisterID（收退费人员id）,FeeType（51）,BackID
     * @throws SQLException
     */
    @Override
    public void regist(Register reg, Invoice iv) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao rd=new RegistDao();
            rd.setConnection(con);
            IPatientCostsDao pcd=new PatientCostsDao();
            pcd.setConnection(con);
            IInvoiceDao id=new InvoiceDao();
            id.setConnection(con);
            IMedicalRecordDao mr=new MedicalRecordDao();
            mr.setConnection(con);
            //设置注册时间为当前时间
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp now=new Timestamp(System.currentTimeMillis());
            String nowStr=df.format(now);
            Timestamp now2=Timestamp.valueOf(nowStr);

            reg.setRegistTime(now2);
            rd.insertRegist(reg);
            con.commit();
            //查出刚刚注册的id
            int registid=rd.selectRegistIDByTime(nowStr,reg.getCaseNumber());
            //设置iv的挂号id
            iv.setRegistID(registid);
            mr.insertMedicalRecord(registid,reg.getCaseNumber());
            id.insertInvoice(iv);

            //插入患者消费
            PatientCosts pc=new PatientCosts();
            pc.setRegisterID(registid);
            Invoice iv2=id.selectInvoiceByNum(iv.getInvoiceNum());
            pc.setInvoiceID(iv2.getId());
            pc.setItemID(1);
            pc.setItemType(1);
            pc.setName("挂号费");
            pc.setPrice(iv.getMoney());
            pc.setAmount(1);
            pc.setDeptID(1);
            pc.setCreateTime(now);
            pc.setCreateOperID(iv.getUserID());
            pc.setPayTime(now);
            pc.setRegisterID(iv.getUserID());
            pc.setFeeType(iv.getFeeType());
            pcd.insertPatientCosts(pc);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }  finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /**
     * 退号
     * 按病历号读取当前状态为未看诊的挂号记录
     *
     * @param caseNum 病历号
     * @return 挂号记录对象
     * @throws SQLException sqlExp
     */
    @Override
    public List<RegisterRecord> findRegistByCaseNumber(String caseNum) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistStateDao rsd=new RegistStateDao();
            rsd.setConnection(con);
            list=rsd.selectRegistByCaseNumber(caseNum);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 更新挂号状态（退号）
     *
     * @param id    挂号id
     * @param state 状态-4
     */
    @Override
    public void backRegist(int id) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistStateDao rsd=new RegistStateDao();
            rsd.setConnection(con);
            rsd.updateRegistState(id,4);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /**
     * 查询所有收费项目
     *
     * @return 所有收费项目
     */
    @Override
    public List findAllCosts(String caseNum) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pd=new PrescriptionDao();
            pd.setConnection(con);
            INonDrugsPayDao ndpd=new NonDrugsPayDao();
            ndpd.setConnection(con);
            List list1=pd.selectPrescriptionByCaseNum(caseNum,2);

            List list2=ndpd.selectNDbyCaseNum(caseNum,2);
            Register r=pd.selectUserByCaseNum(caseNum);

            list.add(list1);
            list.add(list2);
            list.add(r);

            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 点击确定缴费后加载-下一个可用发票号、结算类别
     *
     * @param userid 当前挂号员id
     * @return 下一个可用发票号、结算类别
     */
    @Override
    public List findInvoicePay(int userid) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        List list1=new ArrayList();
        List list3=new ArrayList();

        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistDao rd=new RegistDao();
            rd.setConnection(con);
            IInvoiceDao id=new InvoiceDao();
            String invoiceNum="invoicenum"+id.selectMaxInvoiceNum(userid);
            list1=rd.selectRegistLevels();
            list3=rd.selectSettleCategories();
            list.add(invoiceNum);
            list.add(list1);
            list.add(list3);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

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
     * @param state 3--缴费    6--退费
     * @throws SQLException
     */
    @Override
    public void pay(int flag,int[] id,Invoice iv,PatientCosts pc,int state) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pd=new PrescriptionDao();
            pd.setConnection(con);
            ICheckApplyDao cad=new CheckApplyDao();
            cad.setConnection(con);
            IPatientCostsDao pcd=new PatientCostsDao();
            pcd.setConnection(con);
            IInvoiceDao iid=new InvoiceDao();
            iid.setConnection(con);
            //6-处方状态已缴费--9-处置表状态已缴费
            int itemID=0;
            if (flag==6){
                //缴费后状态变为已缴费
                pd.updatePSB(id,state);
                itemID=2;
            }
            if (flag==9){
                cad.updateCheckApplyState(id,state);
                itemID=1;
            }
            IInvoiceDao idao=new InvoiceDao();
            iv.setState(1);

            idao.insertInvoice(iv);
            Invoice iv2=iid.selectInvoiceByNum(iv.getInvoiceNum());
            pc.setRegistID(iv.getRegistID());
            pc.setInvoiceID(iv2.getId());
            pc.setItemID(1);//??????????????????????????????????????????????????????????????????????????????????????
            pc.setItemType(itemID);
            pc.setPrice(iv.getMoney());
            Timestamp now=new Timestamp(System.currentTimeMillis());
            pc.setCreateTime(now);
            pc.setCreateOperID(iv.getUserID());
            pc.setPayTime(now);
            pc.setRegisterID(iv.getUserID());
            pc.setFeeType(iv.getFeeType());
            pcd.insertPatientCosts(pc);


            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
    }
    /**
     * 查询所有可退费项目-----处置/../..申请 药方 为已缴费状态
     * @param caseNum
     * @return
     * @throws SQLException
     */
    @Override
    public List backpay(String caseNum) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pd=new PrescriptionDao();
            pd.setConnection(con);
            INonDrugsPayDao ndpd=new NonDrugsPayDao();
            ndpd.setConnection(con);
            List list1=pd.selectPrescriptionByCaseNum(caseNum,3);

            List list2=ndpd.selectNDbyCaseNum(caseNum,3);

            list.add(list1);
            list.add(list2);

            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 根据病历号查询病人的消费信息
     *
     * @param caseNum
     * @return
     */
    @Override
    public List findPatientCosts(String caseNum) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPatientCostsDao pc=new PatientCostsDao();
            pc.setConnection(con);
            list=pc.selectPatientCosts(caseNum);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }


}
