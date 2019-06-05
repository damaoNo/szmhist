package dao;

import vo.NonDrugsPay;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface INonDrugsPayDao {
    /*dao层接受业务层的对象开启连接 */
    public void setConnection(Connection con);
    /**分页查询
     * 查询当前有效非药品收费项目
     * @param ItemCode 非药品收费项目编码或名称
     * @return
     * @throws SQLException
     */
    public List nonDrugsEffective(String ItemCode,int page)throws SQLException;

    /**
     * 页码
     * @param ItemCode
     * @return
     */
    public int ndePagenum(String ItemCode) throws SQLException;
    /*读取有效费用分类*/
    public List payEffective() throws SQLException;
    /*读取有效执行科室*/
    public List depEffective() throws SQLException;
    /*根据ID读取当前非药品信息*/
    public  List nonDrugsInfo(int ID) throws SQLException;
    /*判断项目编码是否重复*/
    public  int countId(String ItemCode) throws SQLException;
    /*修改非药品收费项目表的信息*/
    public void fixNonDrugsInfo(NonDrugsPay nonDrugsPay) throws SQLException;
    /*批量删除记录状态为无效*/
    public void delMark(String[] ID) throws SQLException;
}
