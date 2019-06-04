package dao;

import vo.Prescription;

import java.sql.SQLException;

public interface IPrescriptionDao {
    //通过registId获取处方对象
    Prescription getInfByRegistId(int registId)throws SQLException;
}
