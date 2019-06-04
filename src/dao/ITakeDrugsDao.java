package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ITakeDrugsDao {
    /**
     * 设置连接
     *
     */
    public void setConnection(Connection con);

    /**
     * 药房取药
     */
    public List takeDrugs(int CaseNumber, Date PrescriptionTime, int State) throws SQLException;
    /**
     * 刷新药方状态
     *
     */
    public void freshPrescription(int State, int PrescriptionID) throws SQLException;

}
