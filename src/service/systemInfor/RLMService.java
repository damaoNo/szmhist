package service.systemInfor;

import dao.IRLMDao;
import dao.RLMDao;
import util.JdbcUtil;
import vo.RegistLevel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 挂号级别管理
 * @version 0.1
 * @author Vector_Wu
 */

public class RLMService implements IRLMService{
    /*
    根据条件查询有效挂号级别列表
     */
    @Override
    public List<RegistLevel> RLMselectRegistLevel(String code) throws SQLException {
        Connection con = null;
        List list = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            IRLMDao irlmDao = new RLMDao();
            list = irlmDao.SelectRegistLevel(code);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /*
    新增挂号级别
     */
    @Override
    public void RLMaddRegistLevel(String Rcode, String Rname, int Rno, double Rfee, int Rquota) throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            IRLMDao irlmDao = new RLMDao();
            irlmDao.setConnection(con);
            irlmDao.AddRegistLevel(Rcode,Rname,Rno,Rfee,Rquota);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /*
    编辑挂号级别
     */
    @Override
    public RegistLevel RLMSelectupdateRegistLevel(int id) throws SQLException {
        Connection con= null;
        con =JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            IRLMDao irlmDao = new RLMDao();
            irlmDao.setConnection(con);
            irlmDao.SelectupdateRegistLevel(id);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /*
    修改挂号级别
     */
    @Override
    public void RLMUpdatesaveRegistLevel(String Rcode, String Rname, int Rno, Double Rfee, int Rquota) throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            IRLMDao irlmDao = new RLMDao();
            irlmDao.setConnection(con);
            irlmDao.UpdatesaveRegistLevel(Rcode,Rname,Rno,Rfee,Rquota);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /*
    删除挂号级别
     */
    @Override
    public void RLMdeleteRegistLevel(int id) throws SQLException {
        Connection con = null;
        con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            IRLMDao irlmDao = new RLMDao();
            irlmDao.setConnection(con);
            irlmDao.DeleteRegistLevel(id);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }
}
