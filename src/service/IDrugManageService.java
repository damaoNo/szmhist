/**
 * * @ClassName: IDrugManageService
 * * @description: 药品管理业务
 * * @author: cro
 * * @create: 2019-06-02 14:50
 **/

package service;

import vo.ConstantItem;

import java.sql.SQLException;
import java.util.List;

public interface IDrugManageService {
    /**
     * 分页查询药品列表
     * @param mnemonicCode 助记码
     * @param page 页码
     * @return 药品对象集合
     */
    List showDrugList(String mnemonicCode, int page) throws SQLException;
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
    List<ConstantItem> findDrugJixing() throws SQLException;

    /**
     * 获取有效药品类型
     * @return  药品类型集合
     */
    List<ConstantItem> findDrugLeixing() throws SQLException;
}
