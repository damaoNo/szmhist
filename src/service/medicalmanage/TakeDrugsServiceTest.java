package service.medicalmanage;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class TakeDrugsServiceTest {

    @Test
    public void changeState() throws SQLException {
        ITakeDrugsService TD=new TakeDrugsService();
        String a="14,15,16";
        String[] id=a.split(",");
        TD.changeState(4,id);
    }
}