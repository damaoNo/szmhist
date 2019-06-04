package dao;

import vo.NonDrugsPay;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface INonDrugsPayDao {
    /*dao层接受业务层的对象开启连接 */
    public void setConnection(Connection con);
    /*查询当前有效非药品收费项目*/
    public List nonDrugsEffective(String ItemCode)throws SQLException;
    /*读取有效费用分类*/
    public List payEffective() throws SQLException;
    /*读取有效执行科室*/
    public List depEffective() throws SQLException;
    /*如不重复，则修改*/
    /*根据ID读取当前非药品信息*/
    public  List nonDrugsInfo(int ID) throws SQLException;
    /*判断项目编码是否重复*/
    public  int countId(String ItemCode) throws SQLException;
    /*修改非药品收费项目表的信息*/
    public void fixNonDrugsInfo(NonDrugsPay nonDrugsPay) throws SQLException;
    /*置待删除记录状态为无效*/
    public void delMark(String[] ID) throws SQLException;
}
