package dao;

import dao.ICheckApplyDao;
import util.JdbcUtil;
import vo.CheckApply;
import vo.PatientCheckApply;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * * @ClassName: CheckApply
 * * @description: 处置/检查/检验
 * * @author: cro
 * * @create: 2019-06-04 13:24
 **/

public class CheckApplyDao implements ICheckApplyDao {
    Connection con=null;

    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }

    /**
     * 新增检查项目,创建时间-系统当前时间
     *需要设置的项目：meidicalID-当前病历号，registID-当前挂号ID，ItemID-findNdrugByType id，Name-ItemName，CreationTime-自动设置，DocotorID-当前医生ID，
     * State-1-暂存 2-已开立 3-已交费 4-已登记 5-执行完 6-已退费 0-已作废，RecordType-选择的药品类型
     * @param ca checkapply
     */
    @Override
    public void insertCheckApply(CheckApply ca) throws SQLException {
        String sql1="INSERT INTO checkapply(MedicalID,RegistID,ItemID,Name,CreationTime,DoctorID,State,RecordType) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql1);
        pstmt.setInt(1,ca.getMedicalID());
        pstmt.setInt(2,ca.getRegistID());
        pstmt.setInt(3,ca.getItemID());
        pstmt.setString(4,ca.getName());
        Timestamp createTime=new Timestamp(System.currentTimeMillis());
        pstmt.setTimestamp(5,createTime);
        pstmt.setInt(6,ca.getDoctorID());
        pstmt.setInt(7,ca.getState());
        pstmt.setInt(8,ca.getRecordType());
        pstmt.executeUpdate();
        JdbcUtil.release(null, pstmt, null);

    }

    /**
     * 查询个人的检查/检验/处置 申请
     *
     * @param registID   挂号ID
     * @param recordType 类型 1-检查 2-检验 3-处置
     * @return id，name,itemName,deptname,isurgent,state,price,result,
     */
    @Override
    public List<PatientCheckApply> selectPatientCA(int registID, int recordType) throws SQLException {
        String sql1="SELECT c.ID,c.Name,f.ItemName,d.DeptName,c.Isurgent,c.State,f.Price,c.Result " +
                "FROM checkapply c,fmeditem f,department d " +
                "WHERE c.ItemID = f.ID " +
                "and d.ID = f.DeptID " +
                "and c.RegistID = ? " +
                "and c.RecordType = ?";
        PreparedStatement pstmt=con.prepareStatement(sql1);
        pstmt.setInt(1,registID);
        pstmt.setInt(2,recordType);
        ResultSet rs=pstmt.executeQuery();
        List<PatientCheckApply> list=new ArrayList<>();
        PatientCheckApply pca=null;
        while (rs.next()){
            pca=new PatientCheckApply();
            pca.setId(rs.getInt(1));
            pca.setName(rs.getString(2));
            pca.setItemName(rs.getString(3));
            pca.setDeptName(rs.getString(4));
            pca.setIsUrgent(rs.getByte(5));
            pca.setState(rs.getInt(6));
            pca.setPrice(rs.getDouble(7));
            pca.setResult(rs.getString(8));
            list.add(pca);
        }
        JdbcUtil.release(null, pstmt, null);
        return list;
    }

    /**
     * 更改个人的检查/检验/处置 申请状态
     * 需要设置 id，state   -1-暂存 2-已开立 3-已交费 4-已登记 5-执行完 6-已退费 0-已作废
     *
     */
    @Override
    public void updateCheckApplyState(int[] ids,int state) throws SQLException {
        String sql1="update checkapply set State=? where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql1);
        for (int i=0;i<ids.length;i++){
            pstmt.setInt(2,ids[i]);
            pstmt.setInt(1,state);
            pstmt.addBatch();
            if (i%10==0){
                pstmt.executeBatch();
            }
        }
        pstmt.executeBatch();
        JdbcUtil.release(null, pstmt, null);
    }
}
