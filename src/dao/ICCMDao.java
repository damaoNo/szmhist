package dao;

import vo.ConstantItem;
import vo.ConstantType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 常数类别管理
 * @version 0.1
 * @author Vector_Wu
 */

public interface ICCMDao {
    //建立连接
    void setConnection(Connection con);

    //查询所有类别数据
    List<ConstantType> SelectConstantTypeAll(int page) throws SQLException;

    //所有类别数据页码
    int ConstantTypeAllPages() throws SQLException;

    //查询常数类别
    List<ConstantType> SelectConstantType(String codeORname) throws SQLException;

    //新增常数类别
    void AddConstantType(String Ccode,String Cname) throws SQLException;

    //查询常数项
    List<ConstantItem> SelectConstantltem(ConstantItem constantItem,int page) throws SQLException;

    //常数项页码
    int Constantltempage() throws SQLException;

    //添加常数项
    void AddConstantltem(String code,String name,int typeID) throws SQLException;

    //编辑常数项
    void UpdateConstantltem(String code,String name,int typeID,int ID) throws SQLException;

    //删除常数项
    void  DelectConstantltem(String[] ID) throws SQLException;

    /**
     * 根据挂号级别名字查id
     * @param regLeName 挂号级别名称
     * @return 返回挂号级别id
     */
    int selectRegLeIDByName(String regLeName) throws SQLException;
}
