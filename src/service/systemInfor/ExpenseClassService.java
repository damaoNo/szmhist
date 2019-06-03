package service.systemInfor;

import dao.ExpenseClassDao;
import dao.IExpenseClassDao;
import vo.ExpenseClass;

import java.sql.SQLException;

public class ExpenseClassService implements IExpenseClassService{
    IExpenseClassDao dao=null;

    public ExpenseClassService() {
        dao=new ExpenseClassDao();
    }

    @Override
    public ExpenseClass ExpenseClassfindInfByCodeorName(String expc, String expn) throws SQLException {
        return dao.findInfByCodeorName(expc,expn);
    }

    @Override
    public void ExpenseClassaddExpenseclass(String expc) throws SQLException {
        dao.addExpenseclass(expc);

    }

    @Override
    public ExpenseClass ExpenseClassfindInfByID(int id) throws SQLException {
        return dao.findInfByID(id);
    }

    @Override
    public void ExpenseClasssetExpenseclassByExpC(String expc) throws SQLException {
        dao.setExpenseclassByExpC(expc);
    }

    @Override
    public void ExpenseClassinvalideDelMarkByID(int id) throws SQLException {
        dao.invalideDelMarkByID(id);
    }
}
