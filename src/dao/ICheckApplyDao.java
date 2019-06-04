package dao;

import vo.CheckApply;
import vo.PatientCheckApply;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 处置/检查/检验
 * @author cro
 *
 */
public interface ICheckApplyDao {

    void setConnection(Connection con);

    /**
     * 新增检查项目
     * @param ca checkapply
     */
    void insertCheckApply(CheckApply ca) throws SQLException;

    /**
     * 查询个人的检查/检验/处置 申请
     * @param registID   挂号ID
     * @param recordType 类型 1-检查 2-检验 3-处置
     * @return id，name,itemName,deptname,isurgent,state,price,result,
     */
    List<PatientCheckApply> selectPatientCA(int registID, int recordType) throws SQLException;


    /**
     *  更改个人的检查/检验/处置 申请状态
     *  需要设置 id，state   -1-暂存 2-已开立 3-已交费 4-已登记 5-执行完 6-已退费 0-已作废
     * @param ids    申请表ID
     * @param state 申请表状态
     * @throws SQLException
     */
    void updateCheckApplyState(int[] ids,int state) throws SQLException;
}
