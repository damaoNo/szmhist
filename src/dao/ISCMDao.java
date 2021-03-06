package dao;

import vo.SettleCategory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 结算类别管理 接口
 * @version 0.1
 * @author Vector_Wu
 */

public interface ISCMDao {

    //建立链接
    void setConnection(Connection con);

    //查询结算类别
    List<SettleCategory> selectSettleCategory(String code) throws SQLException;

    //新增结算类别保存
    void addSettleCategory(String Scode,String Sname,int Sno) throws SQLException;

    //编辑结算类别
    List<SettleCategory>  SelectupdateSettleCategory(int id) throws SQLException;

    //修改结算类别
    void   updateSettleCategorySave(String Scode,String Sname,int Sno) throws SQLException;

    //删除结算类别
    void  deleteSettleCategory(int id) throws SQLException;
}
