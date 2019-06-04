package service.medicaltreatment;

import dao.*;
import vo.PatientExamination;
import vo.Prescription;
import vo.Register;

import java.sql.SQLException;

public class PatientExaminationService implements IPatientExaminationService{
    @Override
    public PatientExamination getPatientExamByCaseNumber(String casen) throws SQLException {
        PatientExamination user=new PatientExamination();
        IRegistDao dao=new RegistDao();
        IDepartmentDao dao1=new DepartmentDao();
        IMedicalRecordDao dao2=new MedicalRecordDao();
        IPrescriptionDao dao3=new PrescriptionDao();
        IUserDao dao4=new UserDao();


        Register reg=dao.getRegisterByCaseNumber(casen);
        user.setCaseNumber(reg.getCaseNumber());
        user.setName(reg.getRealName());
        user.setAge(reg.getAge());
        user.setSettileId((reg.getSettLeID()==1)?"自费":"医保");
        user.setDepartmentName(dao1.getInfByID(reg.getDeptID()).getDeptName());
        user.setPrescriptionState(  dao3.getInfByRegistId(dao2.getInfByCaseNumber(reg.getCaseNumber()).getRegisterID()).getPrescriptionState());
        user.setVisitDate(reg.getVisitDate().toString());
        user.setUserName( dao4.getUserById(reg.getUserID()).getUserName());
        user.setInvoiceNum(dao.getInfByRegistid(dao2.getInfByCaseNumber(reg.getCaseNumber()).getRegisterID()).getInvoiceNum());
        return user;
    }
}
