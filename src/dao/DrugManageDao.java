/**
 * * @ClassName: MedicineManageDao
 * * @description: 药品管理
 * * @author: cro
 * * @create: 2019-06-02 14:08
 **/

package dao;

import util.JdbcUtil;
import vo.ConstantItem;
import vo.Drugs;
import vo.RegisterRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugManageDao implements IDrugManageDao {
    Connection con=null;
    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }

    /**
     * 分页查询可用药品列表
     *
     * @param mnemonicCode 助记码
     * @param page         页码
     * @return 药品对象集合
     */
    @Override
    public List<Drugs> selectDrugList(String mnemonicCode, int page) throws SQLException {
        int start=(page-1)*10;
        String sql ="select d.ID,DrugsCode,DrugsName,DrugsFormat," +
                "DrugsUnit,Manufacturer,DrugsDosageID,DrugsTypeID," +
                "DrugsPrice,MnemonicCode,CreationDate,LastUpdateDate," +
                "d.DelMark,c1.ConstantName,c2.ConstantName " +
                "from drugs d,constantitem c1,constantitem c2 " +
                "where d.DrugsDosageID = c1.ID " +
                "and d.DrugsTypeID = c2.ID " +
                "and d.DelMark = 1";
        if (mnemonicCode!=null && mnemonicCode.length()!=0){
            sql+=" and MnemonicCode like '%"+mnemonicCode+"%'";
        }
        sql+=" limit "+start+",10";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();
        List<Drugs> drugsList=new ArrayList<>();
        Drugs drugs=null;
        while (rs.next()){
            drugs=new Drugs();
            drugs.setId(rs.getInt(1));
            drugs.setDrugCode(rs.getString(2));
            drugs.setDrugName(rs.getString(3));
            drugs.setDrugsFormat(rs.getString(4));
            drugs.setDrugsUnit(rs.getString(5));
            drugs.setMaufacturer(rs.getString(6));
            drugs.setDrugsDosageID(rs.getInt(7));
            drugs.setDrugsTpyeID(rs.getInt(8));
            drugs.setDrugsPrice(rs.getDouble(9));
            drugs.setMnemonicCode(rs.getString(10));
            drugs.setCreationDate(rs.getDate(11));
            drugs.setLastUpdateDate(rs.getDate(12));
            drugs.setDelMark(rs.getInt(13));
            drugs.setDrugJiXing(rs.getString(14));
            drugs.setDrugLeiXing(rs.getString(15));
            drugsList.add(drugs);
        }
        JdbcUtil.release(null,pstm,null);
        return drugsList;
    }

    /**
     * 药品列表总页数
     *
     * @param mnemonicCode 助记码
     * @param page         页数
     * @return 总页数
     */
    @Override
    public int drugListPages(String mnemonicCode) throws SQLException {
        String sql ="select count(d.id)" +
                "from drugs d,constantitem c1,constantitem c2 " +
                "where d.DrugsDosageID = c1.ID " +
                "and d.DrugsTypeID = c2.ID " +
                "and d.DelMark = 1";
        if (mnemonicCode!=null && mnemonicCode.length()!=0){
            sql+=" and MnemonicCode like '%"+mnemonicCode+"%'";
        }
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();
        int rows=0;
        int pagenum=0;
        while (rs.next()){
            rows=rs.getInt(1);
        }
        if (rows%10==0){
            pagenum=rows/10;
        }else{
            pagenum=rows/10+1;
        }
        return pagenum;
    }

    /**
     * 获取有效药品剂型
     *
     * @return 药品剂型集合
     */
    @Override
    public List<ConstantItem> selectDrugJixing() throws SQLException {
        String sql ="select i.ID,i.ConstantCode,i.ConstantName from constantType t,constantitem i " +
                "where i.ConstantTypeID = t.ID " +
                "and ConstantTypeCode = 'Drugs_Dosage'" +
                "and i.DelMark = 1";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();
        List<ConstantItem> items=new ArrayList<>();
        ConstantItem ci=null;
        while (rs.next()){
            ci=new ConstantItem();
            ci.setId(rs.getInt(1));
            ci.setContantCode(rs.getString(2));
            ci.setConstantName(rs.getString(3));
            items.add(ci);
        }
        JdbcUtil.release(null,pstm,null);
        return items;
    }

    /**
     * 获取有效药品类型
     *
     * @return 药品类型集合
     */
    @Override
    public List<ConstantItem> selectDrugLeixing() throws SQLException {
        String sql ="select i.ID,i.ConstantCode,i.ConstantName from constantType t,constantitem i " +
                "where i.ConstantTypeID = t.ID " +
                "and ConstantTypeCode = 'Drugs_Type'" +
                "and i.DelMark = 1";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();
        List<ConstantItem> items=new ArrayList<>();
        ConstantItem ci=null;
        while (rs.next()){
            ci=new ConstantItem();
            ci.setId(rs.getInt(1));
            ci.setContantCode(rs.getString(2));
            ci.setConstantName(rs.getString(3));
            items.add(ci);
        }
        JdbcUtil.release(null,pstm,null);
        return items;
    }

    /**
     * 查看是否有重复的药品编号
     *
     * @param drugscode 药品编号
     * @return 返回值若为1，则已经存在该药品编号，不能够继续
     */
    @Override
    public int selectDrugID(String  drugscode) throws SQLException {
        String sql ="select count(id) from drugs " +
                "where DrugsCode=? " +
                "and DelMark = 1";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,drugscode);
        ResultSet rs=pstm.executeQuery();
        int drugs=0;
        while (rs.next()){
            drugs=rs.getInt(1);
        }
        JdbcUtil.release(null,pstm,null);
        return drugs;
    }

    /**
     * 新增一条药品记录,最后更新时间为系统当前时间
     *
     * @param drugs 药品对象
     */
    @Override
    public void insertDrug(Drugs drugs) throws SQLException {
        String sql ="insert into drugs(DrugsCode,DrugsName,DrugsFormat,DrugsUnit," +
                "Manufacturer,DrugsDosageID,DrugsTypeID,DrugsPrice,MnemonicCode," +
                "CreationDate,LastUpdateDate,DelMark) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,1)";
        PreparedStatement pstm = con.prepareStatement(sql);
        String code=String.valueOf(drugs.getDrugCode());
        pstm.setString(1,code);
        pstm.setString(2,drugs.getDrugName());
        pstm.setString(3,drugs.getDrugsFormat());
        pstm.setString(4,drugs.getDrugsUnit());
        pstm.setString(5,drugs.getMaufacturer());
        pstm.setInt(6,drugs.getDrugsDosageID());
        pstm.setInt(7,drugs.getDrugsTpyeID());
        pstm.setDouble(8,drugs.getDrugsPrice());
        pstm.setString(9,drugs.getMnemonicCode());
        Date cd=new Date(drugs.getCreationDate().getTime());
        pstm.setDate(10,cd);
        Timestamp lastUpdateTime=new Timestamp(System.currentTimeMillis());
        pstm.setTimestamp(11,lastUpdateTime);
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }

    /**
     * 根据ID查询特定药品信息
     *
     * @param id 药品ID
     * @return 药品对象集合
     */
    @Override
    public Drugs selectDrugByName(String drugName) throws SQLException {
        String sql ="select * from drugs where DrugsName=?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,drugName);
        ResultSet rs=pstm.executeQuery();
        Drugs drugs=null;
        while (rs.next()){
            drugs=new Drugs();
            drugs.setId(rs.getInt(1));
            drugs.setDrugCode(rs.getString(2));
            drugs.setDrugName(rs.getString(3));
            drugs.setDrugsFormat(rs.getString(4));
            drugs.setDrugsUnit(rs.getString(5));
            drugs.setMaufacturer(rs.getString(6));
            drugs.setDrugsDosageID(rs.getInt(7));
            drugs.setDrugsTpyeID(rs.getInt(8));
            drugs.setDrugsPrice(rs.getDouble(9));
            drugs.setMnemonicCode(rs.getString(10));
            drugs.setCreationDate(rs.getDate(11));
            drugs.setLastUpdateDate(rs.getDate(12));
            drugs.setDelMark(rs.getInt(13));
        }
        JdbcUtil.release(null,pstm,null);
        return drugs;
    }

    /**
     * 修改一条药品记录
     *
     * @param drugs 药品对象
     */
    @Override
    public void updateDrug(Drugs drugs) throws SQLException {
        String sql ="update drugs set DrugsName=?,DrugsFormat=?,DrugsUnit=?," +
                "Manufacturer=?,DrugsDosageID=?,DrugsTypeID=?,DrugsPrice=?,MnemonicCode=?," +
                "LastUpdateDate=? " +
                "where DrugsCode=?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1,drugs.getDrugName());
        pstm.setString(2,drugs.getDrugsFormat());
        pstm.setString(3,drugs.getDrugsUnit());
        pstm.setString(4,drugs.getMaufacturer());
        pstm.setInt(5,drugs.getDrugsDosageID());
        pstm.setInt(6,drugs.getDrugsTpyeID());
        pstm.setDouble(7,drugs.getDrugsPrice());
        pstm.setString(8,drugs.getMnemonicCode());
        Timestamp lastUpdate=new Timestamp(System.currentTimeMillis());
        pstm.setTimestamp(9,lastUpdate);
        pstm.setString(10,drugs.getDrugCode());
        pstm.executeUpdate();
        JdbcUtil.release(null,pstm,null);
    }

    /**
     * 批量删除药品
     *
     * @param drugNames 要删除的药品名称
     */
    @Override
    public void delDrugs(String[] drugNames) throws SQLException {
        String sql="update drugs set DelMark = 0 where DrugsCode = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        for (int i=0;i<drugNames.length;i++){
            pstmt.setString(1,drugNames[i]);
            pstmt.addBatch();
            if (i%10==0){
                pstmt.executeBatch();
            }
        }
        pstmt.executeBatch();
        JdbcUtil.release(null, pstmt, null);
    }

}
