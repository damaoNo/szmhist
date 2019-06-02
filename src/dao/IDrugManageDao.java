/**
 * * @ClassName: IMedicineManageDao
 * * @description: 药品管理
 * * @author: cro
 * * @create: 2019-06-02 14:07
 **/

package dao;

import vo.ConstantItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDrugManageDao {
    void setConnection(Connection con);

    /**
     * 分页查询药品列表
     * @param mnemonicCode 助记码
     * @param page 页码
     * @return 药品对象集合
     */
    List selectDrugList(String mnemonicCode,int page) throws SQLException;

    /**
     * 药品列表总页数
     * @param mnemonicCode 助记码
     * @param page 页数
     * @return 总页数
     */
    int drugListPages(String mnemonicCode,int page) throws SQLException;

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

    int selectDrugID(char d);
}
