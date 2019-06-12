package test;

import dao.*;
import service.consult.ConsultService;
import service.consult.IConsultService;
import service.medicaltreatment.IPatientExaminationService;
import service.medicaltreatment.PatientExaminationService;
import vo.ExpenseClass;
import vo.Fmeditem;

import java.sql.SQLException;
import java.util.List;

public class LDaoTest {
    public static void main(String[] args) throws SQLException {
        IFmeditemDao ser=new FmeditemDao();
        System.out.println(ser.getFemdByItemc("120200003"));
    }

}
