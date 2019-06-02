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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugManageDao implements IDrugManageDao {
    Connection con=null;
    @Override
    public void setConnection(Connection con) {
        this.con=con;
    }

    /**
     * 分页查询药品列表
     *
     * @param mnemonicCode 助记码
     * @param page         页码
     * @return 药品对象集合
     */
    @Override
    public List selectDrugList(String mnemonicCode, int page) throws SQLException {
        String sql ="select ID,DrugsCode,DrugsName,DrugsFormat," +
                "DrugsUnit,Manufacturer,DrugsDosageID,DrugsTypeID," +
                "DrugsPrice,MnemonicCode,CreationDate,LastUpdateDate," +
                "DelMark,c1.ConstantName,c2.ConstantName" +
                "from drugs d,constantitem c1,constantitem c2 " +
                "where d.DrugsDosageID = c1.ID " +
                "and d.DrugsTypeID = c2.ID " +
                "and DelMark = 1";
        if (mnemonicCode!=null && mnemonicCode.length()!=0){
            sql+=" and MnemonicCode like '%"+mnemonicCode+"%'";
        }
        sql+=" limit "+page+",10";
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
    public int drugListPages(String mnemonicCode, int page) throws SQLException {
        String sql ="select count(*)" +
                "from drugs d,constantitem c1,constantitem c2 " +
                "where d.DrugsDosageID = c1.ID " +
                "and d.DrugsTypeID = c2.ID " +
                "and DelMark = 1";
        if (mnemonicCode!=null && mnemonicCode.length()!=0){
            sql+=" and MnemonicCode like '%"+mnemonicCode+"%'";
        }
        sql+=" limit "+page+",10";
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
}
