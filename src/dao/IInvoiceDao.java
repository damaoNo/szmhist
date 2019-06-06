package dao;

import vo.Invoice;

import java.sql.Connection;
import java.sql.SQLException;

public interface IInvoiceDao {
    void setConnection(Connection con);
    /**
     * 读取收费员当前最大发票号
     * @param userid 收费员ID
     * @return 收费员下一个可用发票号
     */
    String selectMaxInvoiceNum(int userid) throws SQLException;

    /**
     * @Description: 插入使用发票记录,创建时间自动设置为当前系统时间
     * @Param: [iv]
     * @return: void
     * @Author: cro
     * @Date: 2019/6/1
     */
    void insertInvoice(Invoice iv) throws SQLException;
    /**
     * 打印冲红发票，金额为负值,查出的对象传入，金额已做修改为负值处理
     * @param iv
     * @
     */
    void insertURInvoice(Invoice iv) throws SQLException;

    /**
     * 根据发票号查询id
     * @param num 发票号
     * @return
     */
    Invoice selectInvoiceByNum(String num) throws SQLException;
}
