package test;

import dao.ExpenseClassDao;
import dao.IUserDao;
import dao.UserDao;
import service.medicaltreatment.IPatientExaminationService;
import service.medicaltreatment.PatientExaminationService;
import vo.ExpenseClass;

import java.sql.SQLException;

public class LDaoTest {
    public static void main(String[] args) throws SQLException {
        IPatientExaminationService ser=new PatientExaminationService();
        System.out.println(ser.getPatientExamByCaseNumber("600606"));

    }

}
