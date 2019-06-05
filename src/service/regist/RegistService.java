

package service.regist;

import dao.*;
import util.JdbcUtil;
import vo.Department;
import vo.RegistLevel;
import vo.SchedDoctor;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public List findDoctorInfo(String date,String noon,String  deptName,String  regLeName) throws SQLException {
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
            int deptid=rd.getDeptIDbyName(deptName);
            sc.setDeptID(deptid);

            sc.setRegistLeID(1);
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


}
