package service;

import dao.IUserDao;
import dao.UserDao;
import vo.ConstantItem;
import vo.Department;
import vo.RegistLevel;
import vo.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements  IUserService{
    IUserDao dao=null;

    public UserService(){
        dao=new UserDao();
    }


    @Override
    public User userfindUserByName(String usern, String realn) throws SQLException {
        return dao.findUserByName(usern,realn);
    }

    @Override
    public List<Department> usergetAllValideDepartment() throws SQLException {
        return dao.getAllValideDepartment();
    }

    @Override
    public List<ConstantItem> usergetAllValideDoctorTitle() throws SQLException {
        return dao.getAllValideDoctorTitle();
    }

    @Override
    public List<RegistLevel> usergetAllValideLevel() throws SQLException {
        return dao.getAllValideLevel();
    }

    @Override
    public void useraddNewUser(String usern, String pass, String realn, int usert, int dectitleid, char issche, int deptid, int regisLeid, int delmark) throws SQLException {
        dao.addNewUser(usern,pass,realn, usert, dectitleid, issche, deptid, regisLeid, delmark);
    }

    @Override
    public User usergetUserById(int id) throws SQLException {
        return dao.getUserById(id);
    }

    @Override
    public void userupdateUserByName(String usern, String pass, String realn, int usert, int dectitleid, char issche, int deptid, int regisLeid, int delmark) throws SQLException {
        dao.updateUserByName(usern,pass,realn, usert, dectitleid, issche, deptid, regisLeid, delmark);
    }

    @Override
    public void userdeleteUserByID(int id) throws SQLException {
        dao.deleteUserByID(id);
    }
}
