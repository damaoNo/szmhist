package service.systemInfor;

import vo.ConstantItem;
import vo.Department;
import vo.RegistLevel;
import vo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    //通过username或者realname查找用户
    User userfindUserByName(String usern, String realn)throws SQLException;
    //从科室表中读取所有有效科室
    List<Department> usergetAllValideDepartment()throws SQLException;
    //从常数表读取有效医生职称
    List<ConstantItem> usergetAllValideDoctorTitle()throws SQLException;
    //从挂号表级别表中读取所有有效挂号级别
    List<RegistLevel> usergetAllValideLevel()throws SQLException;
    //新增用户名
    void useraddNewUser(String usern, String pass, String realn, int usert, int dectitleid, char issche, int deptid, int regisLeid, int delmark)throws SQLException;
    //根据id读取用户名
    User usergetUserById(int id)throws SQLException;
    //修改指定用户的信息
    void userupdateUserByName(String usern, String pass, String realn, int usert, int dectitleid, char issche, int deptid, int regisLeid, int delmark)throws SQLException;
    //删除用户
    void userdeleteUserByID(int id)throws SQLException;

}
