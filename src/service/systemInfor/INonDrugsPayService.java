package service.systemInfor;


import vo.NonDrugsPay;

import java.sql.SQLException;
import java.util.List;

public interface INonDrugsPayService {

    /**
     * 查询当前有效非药品收费项目
     * @param ItemCode 非药品收费项目编码或名称
     * @return
     * @throws SQLException
     */
    public List nonDrugsEffective(String ItemCode,int page) throws SQLException;

    /**
     * 读取有效费用分类
     * @return
     * @throws SQLException
     */
    public List payEffective() throws SQLException;

    /**
     * 读取有效科室执行科室
     * @return
     * @throws SQLException
     */
    public List depEffective() throws SQLException;

    /**
     * 判断项目编码是否重复
     * @param ItemCode
     * @return
     */
     public  int countId(String ItemCode) throws SQLException;

    /**
     * 如项目编码不重复，则修改非药品收费信息
     * @param ItemCode
     * @param nonDrugsPay
     * @throws SQLException
     */
    public void fixNonDrugsInfo(String ItemCode,NonDrugsPay nonDrugsPay) throws SQLException;

    /**
     * 根据ID读取药品信息
     * @param ID
     * @return
     * @throws SQLException
     */
    public List nonDrugsInfo(int ID) throws SQLException;
}
