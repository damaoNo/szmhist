

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
     * 挂号界面加载-下一个可用发票号、下一个可用病历号、结算类别
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
            String invoiceNum="invoicenum"+rd.selectMaxInvoiceNum(userid);
            String caseNum="casenum"+rd.selectMaxCaseNum();
            list1=rd.selectRegistLevels();
            list3=rd.selectSettleCategories();
            list2=rd.selectDepartment();
            list.add(invoiceNum);
            list.add(caseNum);
            list.add(list1);
            list.add(list2);
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
            pc.setFeeType(51);
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
            List list1=pd.selectPrescriptionByCaseNum(caseNum);

            List list2=ndpd.selectNDbyCaseNum(caseNum);

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


    @Override
    public void pay(int id) throws SQLException {
        Connection con=null;
        List list=new ArrayList();
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPrescriptionDao pd=new PrescriptionDao();
            pd.setConnection(con);
            //缴费后状态变为已缴费
            pd.updatePresState(id,3);




            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            JdbcUtil.release(con,null,null);
        }
    }


}
