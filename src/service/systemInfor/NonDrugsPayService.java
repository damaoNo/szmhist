package service.systemInfor;

import dao.INonDrugsPayDao;
import dao.NonDrugsPayDao;
import util.JdbcUtil;
import vo.NonDrugsPay;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NonDrugsPayService implements INonDrugsPayService {

    /**
     * 查询当前有效非药品收费项目
     * @param ItemCode 非药品收费项目编码或名称
     * @param page
     * @return
     * @throws SQLException
     */
    @Override
    public List nonDrugsEffective(String ItemCode,int page) throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.nonDrugsEffective(ItemCode,page);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }
    /*读取有效费用分类*/
    @Override
    public List payEffective() throws SQLException {
       Connection con=null;
       List list=null;
       con = JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.payEffective();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }
    /*读取有效科室*/
    @Override
    public List depEffective() throws SQLException {
     Connection con=null;
     List list=null;
     con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.depEffective();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /**
     * 判断项目编码是否重复
     *
     * @param ItemCode
     * @return
     */
    @Override
    public int countId(String ItemCode) throws SQLException {
        Connection con=null;
        int repeat=0;
        con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            repeat=service.countId(ItemCode);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return repeat;
    }

    /**
     * 如项目编码不重复，则修改非药品收费信息
     * @param nonDrugsPay
     */
    @Override
    public void fixNonDrugsInfo(String ItemCode,NonDrugsPay nonDrugsPay) throws SQLException {

        NonDrugsPayService non=new NonDrugsPayService();
       if (non.countId(ItemCode)==0){
           Connection con=null;
           con=JdbcUtil.getConnection();
           try {
               con.setAutoCommit(false);
               INonDrugsPayDao service=new NonDrugsPayDao();
               service.setConnection(con);
               service.fixNonDrugsInfo(nonDrugsPay);
               con.commit();
           } catch (SQLException e) {
               e.printStackTrace();
               con.rollback();
           }finally {
               JdbcUtil.release(con,null,null);
           }
       }

    }

    /**
     * 根据ID读取药品信息
     * @param ID
     * @return
     * @throws SQLException
     */
    @Override
    public List nonDrugsInfo(int ID) throws SQLException {
       Connection con=null;
       List list=null;
       con=JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            INonDrugsPayDao service=new NonDrugsPayDao();
            service.setConnection(con);
            list=service.nonDrugsInfo(ID);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);

        }
        return list;
    }

}
