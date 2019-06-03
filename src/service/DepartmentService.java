package service;



import dao.DepartmentDao;
import dao.IDepartmentDao;
import vo.ConstantItem;
import vo.Department;

import java.sql.SQLException;

public class DepartmentService implements IDepartmentService{
    IDepartmentDao dao=null;

    public DepartmentService() {
        dao=new DepartmentDao();
    }

    @Override
    public Department DepartmentfindInfByCodeorName(String deptc, String deptn) throws SQLException {
        return dao.findInfByCodeorName(deptc,deptn);
    }

    @Override
    public ConstantItem DepartmentgetConstantItem() throws SQLException {
        return dao.getConstantItem();
    }

    @Override
    public void DepartmentaddNewDepartment(String deptc, String deptn, int deptcat, int deptt, int delmark) throws SQLException {
        dao.addNewDepartment(deptc,deptn,deptcat,deptt,delmark);
    }

    @Override
    public Department DepartmentgetInfByID(int id) throws SQLException {
        return dao.getInfByID(id);
    }

    @Override
    public void DepartmentupdateDepartment(String deptc, String deptn, int deptcat, int deptt, int delmark) throws SQLException {
        dao.updateDepartment(deptc,deptn,deptcat,deptt,delmark);
    }

    @Override
    public void DepartmentinvalideDepartment(int id) throws SQLException {
        dao.invalideDepartment(id);
    }
}
