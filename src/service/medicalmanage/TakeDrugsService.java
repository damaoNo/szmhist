package service.medicalmanage;

import dao.ITakeDrugsDao;
import dao.TakeDrugsDao;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TakeDrugsService implements ITakeDrugsService {
    /**
     * 药房取药
     *
     * @param CaseNumber
     * @param PrescriptionTime
     */
    @Override
    public List takeDrugs(int CaseNumber, Date PrescriptionTime,int State) throws SQLException {
        Connection con=null;
        List list=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ITakeDrugsDao itd=new TakeDrugsDao();
            itd.setConnection(con);
            list=itd.takeDrugs(CaseNumber,PrescriptionTime,State);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return list;
    }

    /**
     * 发药退药
     *
     * @param State
     * @param PrescriptionID
     */
    @Override
    public void freshPrescription(int State, int PrescriptionID) throws SQLException {
        Connection con=null;
        con= JdbcUtil.getConnection();
        try {
            con.setAutoCommit(false);
            ITakeDrugsDao itd=new TakeDrugsDao();
            itd.setConnection(con);
            itd.freshPrescription(State,PrescriptionID);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        }finally {
            JdbcUtil.release(con,null,null);
        }

    }
}
