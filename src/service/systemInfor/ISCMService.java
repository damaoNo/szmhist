package service.systemInfor;

import vo.SettleCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * 挂号级别管理
 * @version 0.1
 * @author Vector_Wu
 */

public interface ISCMService {
    //查询结算类别
    List<SettleCategory> ScmSelectSettleCategory(String code) throws SQLException;
    //新增结算类别保存
    void ScmaddSettleCategory(String Scode,String Sname,int Sno) throws SQLException;
    //编辑结算类别
    SettleCategory  ScmselectupdateSettleCategory(int id) throws SQLException;
    //修改结算类
    void ScmupdateSettleCategorySave(String Scode,String Sname,int Sno) throws SQLException;
    //删除结算类别
    void  ScmdeleteSettleCategory(int id) throws SQLException;
}
