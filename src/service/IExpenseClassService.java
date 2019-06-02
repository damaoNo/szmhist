package service;

import vo.ExpenseClass;

import java.sql.SQLException;

public interface IExpenseClassService {
    //读取当前有效费用科目
    ExpenseClass ExpenseClassfindInfByCodeorName(String expc, String expn)throws SQLException;
    //新增费用科目
    void ExpenseClassaddExpenseclass(String expc)throws SQLException;
    //通过id读取科目信息
    ExpenseClass ExpenseClassfindInfByID(int id)throws SQLException;
    //判断费用科目信息是否重复
    void ExpenseClasssetExpenseclassByExpC(String expc)throws SQLException;
    //置删除记录状态无效
    void ExpenseClassinvalideDelMarkByID(int id)throws SQLException;
}
