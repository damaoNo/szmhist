/**
 * * @ClassName: PatientCostsService
 * * @description:
 * * @author: cro
 * * @create: 2019-06-03 11:43
 **/

package service;

import dao.IPatientCostsDao;
import dao.IRegistDao;
import dao.PatientCostsDao;
import dao.RegistDao;
import util.JdbcUtil;
import vo.PatientCosts;
import vo.PatientCostsBack;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PatientCostsService implements IPatientCostsService {


    /**
     * 根据病历号查询病人的消费信息
     * @param caseNum 病历号
     * @return 过往消费记录list
     * @throws SQLException sql
     */
    @Override
    public List<PatientCostsBack> selectPatientCosts(String caseNum) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPatientCostsDao pcs=new PatientCostsDao();
            pcs.setConnection(con);
            List<PatientCostsBack> list=pcs.selectPatientCosts(caseNum);
            con.commit();
            return list;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return null;
    }

    /**
     * @param pc
     * @Description: 记录患者费用明细,创建时间和付钱时间需要设置
     * @Param: [pc]
     * @return: void
     * @Author: cro
     * @Date: 2019/6/1
     */
    @Override
    public boolean newPatientCosts(PatientCosts pc) throws SQLException {
        Connection con=null;
        try {
            con= JdbcUtil.getConnection();
            con.setAutoCommit(false);
            IPatientCostsDao pcs=new PatientCostsDao();
            pcs.setConnection(con);
            pcs.insertPatientCosts(pc);
            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JdbcUtil.release(con,null,null);
        }
        return false;
    }
}
