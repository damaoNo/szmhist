/**
 * * @ClassName: IMedicineManageDao
 * * @description: 药品管理
 * * @author: cro
 * * @create: 2019-06-02 14:07
 **/

package dao;

import vo.ConstantItem;
import vo.Drugs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDrugManageDao {
    void setConnection(Connection con);

    /**
     * 分页查询可用药品列表
     * @param mnemonicCode 助记码
     * @param page 页码
     * @return 药品对象集合
     */
    List<Drugs> selectDrugList(String mnemonicCode,int page) throws SQLException;

    /**
     * 药品列表总页数
     * @param mnemonicCode 助记码
     * @return 总页数
     */
    int drugListPages(String mnemonicCode) throws SQLException;

    /**
     * 获取有效药品剂型
     * @return 药品剂型集合
     */
    List<ConstantItem> selectDrugJixing() throws SQLException;

    /**
     * 获取有效药品类型
     * @return  药品类型集合
     */
    List<ConstantItem> selectDrugLeixing() throws SQLException;

    /**
     * 查看是否有重复的药品编号
     * @param drugscode 药品编号
     * @return 返回值若为1，则已经存在该药品编号，不能够继续
     */
    int selectDrugID(String  drugscode) throws SQLException;

    /**
     * 新增一条药品记录,最后更新时间为系统当前时间
     * @param drugs 药品对象
     */
    void insertDrug(Drugs drugs) throws SQLException;

    /**
     * 根据药品名查询特定药品信息
     * @param drugName 药品名
     * @return 药品对象
     * @throws SQLException
     */
    Drugs selectDrugByName(String drugName) throws SQLException;
    /**
     * 修改一条药品记录,要先判断更改后的药品编号是否已经更改
     * @param drugs 药品对象
     */
    void updateDrug(Drugs drugs) throws SQLException;

    /**
     * 批量删除药品
     * @param drugNames 要删除的药品名称
     */
    void delDrugs(String[] drugNames) throws SQLException;



}
