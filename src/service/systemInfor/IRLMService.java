package service.systemInfor;

import util.JdbcUtil;
import vo.RegistLevel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 挂号级别管理服务层
 * @version 0.1
 * @author Vector_Wu
 */

public interface IRLMService {
    //根据条件查询有效挂号级别列表
    List<RegistLevel> RLMselectRegistLevel(String code) throws SQLException;
    //新增挂号级别
    void RLMaddRegistLevel(String Rcode,String Rname,int Rno,double Rfee,int Rquota) throws SQLException;

    //编辑挂号级别
    RegistLevel RLMSelectupdateRegistLevel(int id) throws SQLException;

    //修改挂号级别
    void RLMUpdatesaveRegistLevel(String Rcode,String Rname,int Rno,Double Rfee,int Rquota) throws SQLException;

    //删除挂号
    void RLMdeleteRegistLevel(int id) throws SQLException;
}
