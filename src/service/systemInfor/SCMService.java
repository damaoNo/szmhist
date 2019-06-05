package service.systemInfor;

import dao.ISCMDao;
import dao.SCMDao;
import util.JdbcUtil;
import vo.SettleCategory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SCMService implements ISCMService{
    /*
    查询结算类别
     */
    @Override
    public List<SettleCategory> ScmSelectSettleCategory(String code) throws SQLException {
        Connection con= null;
        List list =null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISCMDao iscmDao = new SCMDao();
            iscmDao.setConnection(con);
            list=iscmDao.selectSettleCategory(code);
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
    新增结算类别保存
     */
    @Override
    public void ScmaddSettleCategory(String Scode, String Sname, int Sno) throws SQLException {
        Connection con= null;
        con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISCMDao iscmDao = new SCMDao();
            iscmDao.setConnection(con);
            iscmDao.addSettleCategory(Scode,Sname,Sno);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /*
    编辑结算类别
     */
    @Override
    public SettleCategory ScmselectupdateSettleCategory(int id) throws SQLException {
        Connection con=null;
        con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISCMDao iscmDao = new SCMDao();
            iscmDao.setConnection(con);
            iscmDao.SelectupdateSettleCategory(id);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /*
    修改结算类
     */
    @Override
    public void ScmupdateSettleCategorySave(String Scode, String Sname, int Sno) throws SQLException {
        Connection con =null;
        con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISCMDao iscmDao = new SCMDao();
            iscmDao.setConnection(con);
            iscmDao.updateSettleCategorySave(Scode,Sname,Sno);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }

    /*
    删除结算类
     */
    @Override
    public void ScmdeleteSettleCategory(int id) throws SQLException {
        Connection con = null;
        con =JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ISCMDao iscmDao = new SCMDao();
            iscmDao.setConnection(con);
            iscmDao.deleteSettleCategory(id);
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
    }
}
