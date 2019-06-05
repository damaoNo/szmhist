/**
 * * @ClassName: InvoiceDao
 * * @description: 发票表Dao
 * * @author: cro
 * * @create: 2019-06-05 15:52
 **/

package dao;

import util.JdbcUtil;
import vo.Invoice;

import java.sql.*;

public class InvoiceDao implements IInvoiceDao {
    Connection con = null;

    public void setConnection(Connection con) {
        this.con = con;
    }
    /**
     * @param iv
     * @Description: 插入使用发票记录,创建时间自动设置为当前系统时间
     * @Param: [iv]
     * @return: void
     * @Author: cro
     * @Date: 2019/6/1
     */
    @Override
    public void insertInvoice(Invoice iv) throws SQLException {
        String sql ="insert into " +
                "invoice(InvoiceNum,Money,State,CreationTime,UserID,RegistID,FeeType,Back,DailyState) " +
                "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,iv.getInvoiceNum());
        pstm.setDouble(2,iv.getMoney());
        pstm.setInt(3,iv.getState());
        Timestamp t=new Timestamp(System.currentTimeMillis());
        pstm.setTimestamp(4,t);
        pstm.setInt(5,iv.getUserID());
        pstm.setInt(6,iv.getRegistID());
        pstm.setInt(7,iv.getFeeType());
        pstm.setString(8,iv.getBack());
        pstm.setInt(9,iv.getDailyState());
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }
    /**
     * 打印冲红发票，金额为负值,查出的对象传入，金额已做修改为负值处理
     *
     * @param iv 发票对象
     * @
     */
    @Override
    public void insertURInvoice(Invoice iv) throws SQLException {
        String sql ="insert into " +
                "invoice(InvoiceNum,Money,State,CreationTime,UserID,RegistID,FeeType,Back,DailyState) " +
                "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,iv.getInvoiceNum());
        pstm.setDouble(2,iv.getMoney());
        pstm.setInt(3,iv.getState());
        pstm.setDate(4,new Date(System.currentTimeMillis()));
        pstm.setInt(5,iv.getUserID());
        pstm.setInt(6,iv.getRegistID());
        pstm.setInt(7,iv.getFeeType());
        pstm.setString(8,iv.getBack());
        pstm.setInt(9,iv.getDailyState());
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }

    /**
     * 根据发票号查询id
     *
     * @param num 发票号
     * @return
     */
    @Override
    public Invoice selectInvoiceByNum(String num) throws SQLException {
        String sql ="select * from invoice where InvoiceNum=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,num);
        ResultSet rs=pstm.executeQuery();
        Invoice i=new Invoice();
        while (rs.next()){
            i.setId(rs.getInt(1));
            i.setInvoiceNum(rs.getString(2));
            i.setMoney(rs.getDouble(3));
            i.setState(rs.getInt(4));
            i.setCreationTime(rs.getTimestamp(5));
            i.setUserID(rs.getInt(6));
            i.setRegistID(rs.getInt(7));
            i.setFeeType(rs.getInt(8));
            i.setBack(rs.getString(9));
            i.setDailyState(rs.getInt(10));
        }
        JdbcUtil.release(null,pstm,null);
        return i;
    }
}
