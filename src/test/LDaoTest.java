package test;

import dao.ExpenseClassDao;
import dao.IUserDao;
import dao.UserDao;
import vo.ExpenseClass;

import java.sql.SQLException;

public class LDaoTest {
    public static void main(String[] args) throws SQLException {
        IUserDao dao=new UserDao();
        System.out.println(dao.findUserByName("hqb",""));

    }

}
