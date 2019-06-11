package test;


import org.junit.Test;
import service.medicalmanage.TakeDrugsService;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TakeDrugsServiceTest {
    TakeDrugsService tds=new TakeDrugsService();

  @Test
    public void takeDrugs() throws SQLException {
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        List list=null;
        try {
          Date  date = format.parse("2019-03-29");
         list=tds.takeDrugs(600607,date,3);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(list.toString());


    }
/*
    @Test
    public void freshPrescription() throws SQLException {
        tds.freshPrescription(5,55);

    }*/
}
