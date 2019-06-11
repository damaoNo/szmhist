package test;

import dao.TakeDrugsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.JdbcUtil;

import static org.junit.Assert.*;


public class TakeDrugsDaoTest {


    @Test
    public void takeDrugs() {

    }

    @Test
    public void changeState() {
        TakeDrugsDao TD=new TakeDrugsDao();
        TD.setConnection(JdbcUtil.getConnection());
    }
}
