/**
 * * @ClassName: RegistStateService
 * * @description: 更改挂号状态（退号）
 * * @author: cro
 * * @create: 2019-06-02 10:52
 **/

package service.regist;

import dao.IRegistStateDao;
import dao.RegistStateDao;
import util.JdbcUtil;
import vo.Invoice;
import vo.RegisterRecord;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistStateService implements IRegistStateService{
    /**
     * @param caseNum
     * @Description: 按病历号读取当前状态为未看诊的挂号记录
     * @Param: [caseNum]
     * @return: vo.RegisterRecord
     * @Author: cro
     * @Date: 2019/6/2
     */
    @Override
    public RegisterRecord findRegistByCaseNumber(String caseNum) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistStateDao rsd=new RegistStateDao();
            rsd.setConnection(con);
            RegisterRecord rr=rsd.selectRegistByCaseNumber(caseNum);
            con.commit();
            return rr;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 更新挂号状态（退号）
     *
     * @param id 挂号id
     * @
     * @author : cro
     */
    @Override
    public boolean updateRegistState(int id) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistStateDao rsd=new RegistStateDao();
            rsd.setConnection(con);
            rsd.updateRegistState(id);
            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return false;
    }

    /**
     * 打印冲红发票，金额为负值,查出的对象传入，金额已做修改为负值处理
     *
     * @param iv
     */
    @Override
    public boolean newURInvoice(Invoice iv) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IRegistStateDao rsd=new RegistStateDao();
            rsd.setConnection(con);
            rsd.insertURInvoice(iv);
            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return false;
    }
}
