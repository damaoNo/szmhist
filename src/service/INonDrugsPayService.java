package service;


import java.sql.SQLException;
import java.util.List;

public interface INonDrugsPayService {
    /*查询当前有效非药品收费项目*/
    public List nonDrugsEffective(String ItemCode) throws SQLException;
    /*读取有效费用分类*/
    public List payEffective() throws SQLException;
    /*读取有效科室执行科室*/
    public List depEffective() throws SQLException;
    /*判断项目编码是否重复*/
    /*如不重复，则修改*/
    /*根据ID读取药品信息*/
    public List nonDrugsInfo(int ID) throws SQLException;
}
