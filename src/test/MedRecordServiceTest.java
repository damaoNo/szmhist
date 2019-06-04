package test;

import org.junit.Test;
import service.consult.MedRecordService;
import vo.MedicalRecord;
import vo.NonDrugsPay;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class MedRecordServiceTest {
    MedRecordService service=new MedRecordService();
    @Test
    public void findMedRecord() throws SQLException {
        MedicalRecord mr=service.findMedRecord(6);
        System.out.println(mr);
    }

    @Test
    public void findDrugByType() throws SQLException {
        NonDrugsPay n=new NonDrugsPay();
        n.setRecordType(2);
//        n.setMnemonicCode("PTTS");
        n.setItemName("唐氏");
        List<NonDrugsPay> list=service.findNDrugByType(n);
        for (NonDrugsPay n1:list){
            System.out.println(n1);
        }
    }
}