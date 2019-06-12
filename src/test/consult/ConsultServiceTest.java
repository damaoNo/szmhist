package test.consult;

import dao.IPrescriptionDao;
import dao.PrescriptionDao;
import oracle.jdbc.proxy.annotation.Pre;
import org.junit.Test;
import service.consult.ConsultService;
import service.consult.IConsultService;
import vo.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ConsultServiceTest {
    IConsultService service=new ConsultService();

    @Test
    public void findUnVisitPatient() throws SQLException {
        List<Register> list=service.findUnVisitPatient(1);
        for (Register r:list){
            System.out.println(r);
        }
    }

    @Test
    public void findVisitedPatient() throws SQLException {
        List<Register> list=service.findVisitedPatient(1);
        for (Register r:list){
            System.out.println(r);
        }
    }

    @Test
    public void findDocUVP() throws SQLException {
        List<Register> list=service.findDocUVP(1);
        for (Register r:list){
            System.out.println(r);
        }
    }

    @Test
    public void findDocVP() throws SQLException {
        List<Register> list=service.findDocVP(1);
        for (Register r:list){
            System.out.println(r);
        }
    }

    @Test
    public void findMedicalRecord() throws SQLException {
        MedicalRecord mr=service.findMedRecord(3);
        System.out.println(mr);
    }

    @Test
    public void consulting() throws SQLException {
        MedicalRecord mr=new MedicalRecord();
        mr.setRegisterID(33);
        mr.setReadme("Test23");
        service.consulting(33,2,mr);
    }

    @Test
    public void findNDrugByType() throws SQLException {
        NonDrugsPay ndp=new NonDrugsPay();
        ndp.setRecordType(2);
//        ndp.setMnemonicCode("njf");
        ndp.setItemName("蛋");
        List<NonDrugsPay> l=service.findNDrugByType(ndp);
        for (NonDrugsPay d:l){
            System.out.println(d);
        }
    }

    @Test
    public void findPatientCA() throws SQLException {
        List<PatientCheckApply> l=service.findPatientCA(26,1);
        for (PatientCheckApply p:l){
            System.out.println(p);
        }
    }

    @Test
    public void changeCaState() throws SQLException {
        int[] ids={1,6,7};
        service.changeCAState(ids,2);
    }

    @Test
    public void diagnosis() throws SQLException {
        MedicalRecord mr=new MedicalRecord();
        mr.setRegisterID(33);
        mr.setCheckResult("没有救了");
        mr.setDiagnosis("等死吧");
        mr.setHandling("放弃吧");
        service.diagnosis(mr);
    }

    @Test
    public void findPreByUserID() throws SQLException {
        List<Prescription> list=service.findPreByUserID(1,37);
        for (Prescription p:list){
            System.out.println(p);
        }
    }

    @Test
    public void findDrugsinPre() throws SQLException {
        List<PrescriptionDetailed> list=service.findDrugsinPre(1);
        for (PrescriptionDetailed p:list){
            System.out.println(p);
        }
    }

    @Test
    public void allDrugs() throws SQLException {
        List list=service.allDrugs("",1);
        System.out.println(list.toString());
    }

    @Test
    public void newPresDetailed() throws SQLException {
        /*
         * 新增一条药房明细记录
         * 处方id          药品ID   药品用法    药品计量 频次         数量 状态
         *PrescriptionID,DrugsID,DrugsUsage,Dosage,Frequency,Amount,State 2-已开立 3-已交费 4-已发药 5-已退药 6-已退费
         */
        PrescriptionDetailed pd=new PrescriptionDetailed();
        pd.setPrescriptionID(1);
        pd.setDrugsID(10);
        pd.setDrugsUsage("test");
        pd.setDosage("ttes");
        pd.setFrequency("每天100此");
        pd.setAmount(10d);
        pd.setState(2);
        service.newPresDetailed(pd);
    }

    @Test
    public void deletDrugs() throws SQLException {
        int[] s={30};
        service.deletDrugs(s);
    }
}