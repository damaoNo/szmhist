package test;

import dao.ExpenseClassDao;
import vo.ExpenseClass;

import java.sql.SQLException;

public class LDaoTest {
    public static void main(String[] args) throws SQLException {
//        System.out.println("ss");
        ExpenseClassDao dao=new ExpenseClassDao();
        ExpenseClass us=dao.readEffectiveCostSubject("XYF","");
        System.out.println(us);
        System.out.println("this is it");
    }

}
