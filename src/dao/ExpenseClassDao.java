package dao;

import util.JdbcUtil;
import vo.ExpenseClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseClassDao implements IExpenseClassDao{
    Connection con=null;

    @Override
    public vo.ExpenseClass readEffectiveCostSubject(String expc,String expn) throws SQLException {
        String sql="select id,expcode,expname,delmark from expenseclass where (expcode like ? or expname like ?) delmark=1";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,expc);
        ps.setString(2,expn);
        ResultSet rs=ps.executeQuery();
        ExpenseClass pense=new ExpenseClass();
        while(rs.next()){
           pense.setId(rs.getInt(1));
           pense.setExpCode(rs.getString(2));
           pense.setExpName(rs.getString(3));
           pense.setDelMark(rs.getInt(4));
        }
        JdbcUtil.release(con,ps,rs);
        return pense;
    }

    @Override
    public boolean isSubjectRepeatandAdd(int id) throws SQLException {
        return false;
    }

    @Override
    public vo.ExpenseClass readInfobyId(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean isSubjectRepeatandUp(int id) throws SQLException {
        return false;
    }

    @Override
    public void setDelMarkInvalid() throws SQLException {

    }
}
