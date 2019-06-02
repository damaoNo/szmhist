/**
 * * @ClassName: DrugManageService
 * * @description: 药品管理 业务
 * * @author: cro
 * * @create: 2019-06-02 14:51
 **/

package service;

import dao.DrugManageDao;
import dao.IDrugManageDao;
import dao.IRegistDao;
import dao.RegistDao;
import util.JdbcUtil;
import vo.ConstantItem;
import vo.Drugs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugManageService implements IDrugManageService{
    /**
     * 分页查询药品列表
     *
     * @param mnemonicCode 助记码
     * @param page         页码
     * @return 药品对象集合
     */
    @Override
    public List showDrugList(String mnemonicCode, int page) throws SQLException {
        Connection con=null;
        List list=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            list=dd.selectDrugList(mnemonicCode,page);
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
     * 药品列表总页数
     *
     * @param mnemonicCode 助记码
     * @param page         页数
     * @return 总页数
     */
    @Override
    public int drugListPages(String mnemonicCode, int page) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            int pageNum=dd.drugListPages(mnemonicCode,page);
            con.commit();
            return pageNum;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return 0;
    }

    /**
     * 获取有效药品剂型
     *
     * @return 药品剂型集合
     */
    @Override
    public List<ConstantItem> findDrugJixing() throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            List<ConstantItem> list=dd.selectDrugJixing();
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
     * 获取有效药品类型
     *
     * @return 药品类型集合
     */
    @Override
    public List<ConstantItem> findDrugLeixing() throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            List<ConstantItem> list=dd.selectDrugLeixing();
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
}
