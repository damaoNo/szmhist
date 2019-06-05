package test;

import org.junit.Test;
import service.systemInfor.CCMService;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class CCMServiceTest2 {

    @Test
    public void CCMSelectConstantTypeAll() throws SQLException {
        CCMService CC=new CCMService();
        int page =3;
        CC.CCMSelectConstantTypeAll(page);
    }
}