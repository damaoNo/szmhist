package dao;

import vo.ExpenseClass;

import java.sql.SQLException;

public interface IExpenseClassDao {
    //读取当前有效费用科目
    ExpenseClass readEffectiveCostSubject(String expc,String expn)throws SQLException;
    //判断费用科目编发是否重复,不重复则新增
    boolean isSubjectRepeatandAdd(int id)throws SQLException;
    //根据id读取费用科目信息
    ExpenseClass readInfobyId(int id)throws SQLException;
    //判断科目编码是否重复,不重复则修改
    boolean isSubjectRepeatandUp(int id)throws  SQLException;
    //置待删除的记录状态为无效
    void setDelMarkInvalid()throws SQLException;
}
