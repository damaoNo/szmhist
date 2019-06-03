/**
 * * @ClassName: DrugManageService
 * * @description: 药品管理 业务
 * * @author: cro
 * * @create: 2019-06-02 14:51
 **/

package service.systemInfor;

import dao.DrugManageDao;
import dao.IDrugManageDao;
import util.JdbcUtil;
import vo.ConstantItem;
import vo.Drugs;

import java.sql.Connection;
import java.sql.SQLException;
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
    public List<Drugs> showDrugList(String mnemonicCode, int page) throws SQLException {
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
     * @return 总页数
     */
    @Override
    public int drugListPages(String mnemonicCode) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            int pageNum=dd.drugListPages(mnemonicCode);
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

    /**
     * 查看是否有重复的药品编号
     *
     * @param drugscode 药品编号
     * @return 返回值若为1，则已经存在该药品编号，不能够继续
     */
    @Override
    public int findDrugByID(String  drugscode) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            int drugs=dd.selectDrugID(drugscode);
            con.commit();
            return drugs;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return 0;
    }

    /**
     * 新增一条药品记录,最后更新时间为系统当前时间
     *
     * @param drugs 药品对象
     */
    @Override
    public boolean newDrug(Drugs drugs) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            dd.insertDrug(drugs);
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
     * 根据药品名查询特定药品信息
     *
     * @param drugName 药品名
     * @return 药品对象
     * @throws SQLException
     */
    @Override
    public Drugs findDrugByName(String drugName) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            Drugs drugs=dd.selectDrugByName(drugName);
            con.commit();
            return drugs;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * 修改一条药品记录
     *
     * @param drugs 药品对象
     */
    @Override
    public boolean changeDrugInfo(Drugs drugs) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            dd.updateDrug(drugs);
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
     * 批量删除药品
     *
     * @param drugNames 要删除的药品名称
     */
    @Override
    public boolean delDrugs(String[] drugNames) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IDrugManageDao dd=new DrugManageDao();
            dd.setConnection(con);
            dd.delDrugs(drugNames);
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
