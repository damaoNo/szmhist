package test;

import dao.CCMDao;
import org.junit.Test;
import service.systemInfor.CCMService;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CCMServiceTest {

    @Test
    public void CCMSelectConstantTypeAll() throws SQLException {
        CCMService ccmService = new CCMService();
        List list =ccmService.CCMSelectConstantTypeAll();
        System.out.println(list);
    }

    @Test
    public void CCMSelectConstantType() {
    }

    @Test
    public void CCMAddConstantType() {
    }

    @Test
    public void CCMSelectConstantltem() {
    }

    @Test
    public void CCMAddConstantltem() {
    }

    @Test
    public void CCMUpdateConstantltem() {
    }

    @Test
    public void CCMDelectConstantltem() {
    }
}