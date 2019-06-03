/**
 * * @ClassName: IRegistStateDao
 * * @description: 更改挂号状态（退号）
 * * @author: cro
 * * @create: 2019-06-01 22:51
 **/

package dao;



import vo.Invoice;
import vo.RegisterRecord;

import java.sql.Connection;
import java.sql.SQLException;

public interface IRegistStateDao {

    void setConnection(Connection con);

    /**
     * 按病历号读取当前状态为未看诊的挂号记录
    * @param caseNum 病历号
     * @return 挂号记录对象
     * @throws SQLException sqlExp
    */
    RegisterRecord selectRegistByCaseNumber(String caseNum) throws SQLException;
    /**
     * 更新挂号状态（退号）
    * @param id 挂号id
    * @
    * @author : cro
    */
    void updateRegistState(int id) throws SQLException;

    /**
     * 打印冲红发票，金额为负值,查出的对象传入，金额已做修改为负值处理
     * @param iv
     * @
     */
    void insertURInvoice(Invoice iv) throws SQLException;



}
