package dao;

import vo.RegistLevel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 挂号级别管理 接口
 * @version 0.1
 * @author Vector_Wu
 */

public interface IRLMDao {
    //建立连接
    void setConnection(Connection con);

    //根据条件查询有效挂号级别列表
    List<RegistLevel> SelectRegistLevel(String code) throws SQLException;

    //新增挂号级别
    void AddRegistLevel(String Rcode,String Rname,int Rno,double Rfee,int Rquota) throws SQLException;

    //编辑挂号级别
    RegistLevel SelectupdateRegistLevel(int id) throws SQLException;

    //编辑挂号级别保存
    void UpdatesaveRegistLevel(String Rcode,String Rname,int Rno,Double Rfee,int Rquota) throws SQLException;

    //删除挂号
    void DeleteRegistLevel(int id) throws SQLException;
}
