package dao;

import vo.Fmeditem;

import java.sql.SQLException;
import java.util.List;

public interface IFmeditemDao {
    //查询所有的医技
    List<Fmeditem> getAllFemditem()throws SQLException;
    //通过名字获得医技对象
    Fmeditem getFemdByName(String name)throws SQLException;
    //通过编号获得医技对象
    Fmeditem getFemdByItemc(String itemc)throws SQLException;
}
