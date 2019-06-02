package service;

import vo.ConstantItem;
import vo.Department;

import java.sql.SQLException;

public interface IDepartmentService {
    //读取当前有效费用科目
    Department DepartmentfindInfByCodeorName(String deptc, String deptn)throws SQLException;
    //从常熟表中读取所有科室分类
    ConstantItem DepartmentgetConstantItem()throws SQLException;
    //新增科室
    void DepartmentaddNewDepartment(String deptc, String deptn, int deptcat, int deptt, int delmark)throws SQLException;
    //根据ID读取科室信息
    Department DepartmentgetInfByID(int id)throws SQLException;
    //更新科室信息
    void DepartmentupdateDepartment(String deptc, String deptn, int deptcat, int deptt, int delmark)throws SQLException;
    //删除科室
    void DepartmentinvalideDepartment(int id)throws SQLException;
}
