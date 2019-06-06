package service.systemInfor;

import vo.ConstantItem;
import vo.ConstantType;

import java.sql.SQLException;
import java.util.List;

/**
 * 常数类别管理
 * @version 0.1
 * @author Vector_Wu
 */

public interface ICCMService {
    //查询所有类别数据
    List<ConstantType> CCMSelectConstantTypeAll(int page) throws SQLException;

    //所有类别数据页码
    int CCMConstantTypeAllPages() throws SQLException;

    //查询常数类别
    List<ConstantType> CCMSelectConstantType(String codeORname) throws SQLException;

    //新增常数类别
    void CCMAddConstantType(String Ccode,String Cname) throws SQLException;

    //查询常数项
    List<ConstantItem> CCMSelectConstantltem(ConstantItem constantItem, int page) throws SQLException;

    //常数项页码
    int CCMConstantltempage() throws SQLException;

    //添加常数项
    void CCMAddConstantltem(String code,String name,int typeID) throws SQLException;

    //编辑常数项
    void CCMUpdateConstantltem(String code,String name,int typeID,int ID) throws SQLException;

    //删除常数项
    void  CCMDelectConstantltem(String[] ID) throws SQLException;
}
