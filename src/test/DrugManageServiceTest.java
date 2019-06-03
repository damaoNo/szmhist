package test;

import org.junit.Test;
import service.DrugManageService;
import service.IDrugManageService;
import vo.ConstantItem;
import vo.Drugs;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DrugManageServiceTest {
    IDrugManageService service=new DrugManageService();
    @Test
    public void showDrugList() throws SQLException {
        List<Drugs> list=service.showDrugList("H",1);
        for (Drugs d:list){
            System.out.println(d);
        }
    }

    @Test
    public void drugListPages() throws SQLException {
        int i=service.drugListPages("");
        System.out.println(i);
    }

    @Test
    public void findDrugJixing() throws SQLException {
        List<ConstantItem> list=service.findDrugJixing();
        for (ConstantItem c:list){
            System.out.println(c);
        }
    }

    @Test
    public void findDrugLeixing() throws SQLException {
        List<ConstantItem> list=service.findDrugLeixing();
        for (ConstantItem c:list){
            System.out.println(c);
        }
    }

    @Test
    public void findDrugByID() throws SQLException {
        int i=service.findDrugByID("86979474000208");
        System.out.println(i);
    }

    @Test
    public void newDrug() throws SQLException {
        Drugs drugs=new Drugs();
        drugs.setDrugCode("testDrug");
        drugs.setDrugName("test");
        drugs.setDrugsPrice(11.11);

        drugs.setCreationDate(new Date());
        service.newDrug(drugs);
    }

    @Test
    public void findDrugByName() throws SQLException {
        Drugs drugs=service.findDrugByName("test");
        System.out.println(drugs);
    }

    @Test
    public void changeDrugInfo() throws SQLException {
        Drugs drugs=new Drugs();
        drugs.setDrugCode("testDrug");
        drugs.setDrugName("test");
        drugs.setDrugsPrice(22.22);
        service.changeDrugInfo(drugs);
    }

    @Test
    public void delDrugs() throws SQLException {
        String[] s={"testDrug","1111"};
        service.delDrugs(s);
    }
}