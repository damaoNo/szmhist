/**
 * * @ClassName: IRegistStateService
 * * @description: 更改挂号状态（退号）
 * * @author: cro
 * * @create: 2019-06-01 22:51
 **/
package service;


import vo.Invoice;
import vo.RegisterRecord;

import java.sql.SQLException;

public interface IRegistStateService {
    /**
     * @Description: 按病历号读取当前状态为未看诊的挂号记录
     * @Param: [caseNum]
     * @return: vo.RegisterRecord
     * @Author: cro
     * @Date: 2019/6/2
     */
    RegisterRecord findRegistByCaseNumber(String caseNum) throws SQLException;

    /**
     * 更新挂号状态（退号）
     * @param id 挂号id
     * @
     * @author : cro
     */
    boolean updateRegistState(int id) throws SQLException;

    /**
     * 打印冲红发票，金额为负值,查出的对象传入，金额已做修改为负值处理
     * @param iv 记录对象
     * @
     */
    boolean newURInvoice(Invoice iv) throws SQLException;


}
