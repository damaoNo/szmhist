package service.medicaltreatment;

import vo.PatientExamination;

import java.sql.SQLException;

public interface IPatientExaminationService {
    //获得医技检测明细单
    PatientExamination getPatientExamByCaseNumber(String casen)throws SQLException;

}
