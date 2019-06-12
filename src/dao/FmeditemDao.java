package dao;

import util.JdbcUtil;
import vo.Fmeditem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FmeditemDao implements IFmeditemDao{
    Connection con=null;


    @Override
    public List<Fmeditem> getAllFemditem() throws SQLException {
        String sql="select itemcode,itemname,format,price,mnemoniccode from fmeditem";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        List<Fmeditem> list=new ArrayList<Fmeditem>();
        while(rs.next()){
            Fmeditem fme=new Fmeditem();
            fme.setItemCode(rs.getString(1));
            fme.setItemName(rs.getString(2));
            fme.setFormat(rs.getString(3));
            fme.setPrice(rs.getDouble(4));
            fme.setMnemonicCode(rs.getString(5));
            list.add(fme);
        }
        JdbcUtil.release(con,null,null);
        return list;
    }

    @Override
    public Fmeditem getFemdByName(String itemn) throws SQLException {
        String sql="select itemcode,itemname,format,price,mnemoniccode from fmeditem where itemname=?";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,itemn);
        ResultSet rs=ps.executeQuery();
        Fmeditem fme=new Fmeditem();
        while(rs.next()){
            fme.setItemCode(rs.getString(1));
            fme.setItemName(rs.getString(2));
            fme.setFormat(rs.getString(3));
            fme.setPrice(rs.getDouble(4));
            fme.setMnemonicCode(rs.getString(5));
        }
        JdbcUtil.release(con,null,null);
        return fme;
    }

    @Override
    public Fmeditem getFemdByItemc(String itemc) throws SQLException {
        String sql="select itemcode,itemname,format,price,mnemoniccode from fmeditem where itemcode=?";
        con= JdbcUtil.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,itemc);
        ResultSet rs=ps.executeQuery();
        Fmeditem fme=new Fmeditem();
        while(rs.next()){
            fme.setItemCode(rs.getString(1));
            fme.setItemName(rs.getString(2));
            fme.setFormat(rs.getString(3));
            fme.setPrice(rs.getDouble(4));
            fme.setMnemonicCode(rs.getString(5));
        }
        JdbcUtil.release(con,null,null);
        return fme;



    }
}
