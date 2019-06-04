package dao;

import vo.DiseCategory;
import vo.Disease;

import java.sql.SQLException;
import java.util.List;

public interface IDiseCategoryDao {
    //根据编号或者名字查询
    DiseCategory getDiseCategoryByCorN(String dicac,String dican)throws SQLException;
    //修改一个诊断目录
    void addNewDiseCategory(String dicac,String dican,int seq,int dicat,int delmark)throws SQLException;
    //根据id找到对象
    DiseCategory getDiseByID(int id)throws SQLException;
    //修改对应编码对象的信息
    void updateDiseCategory(String dicac,String dican,int seq,int dicat,int delmark)throws SQLException;
    //设置当前疾病分类以及疾病状态无效
    void invalidateDiseCByID(int id)throws SQLException;
    //通过编号或者名字读取疾病列表
    Disease getvalideDiseCategory(String disec,String disen)throws SQLException;
    //读取有效疾病分类
    List<DiseCategory> getAllvalideDiseCategory()throws SQLException;
    //新增疾病
    void addNewDisease(String dicac,String dican,String icd, int cateid, int delmark)throws SQLException;
    //根据疾病目录id找到疾病
    List<Object> findDiseaseByCategoryID(int id)throws SQLException;
    //修改疾病
    void updateDisease(String diseaseC,String diseaseI,String diseaseN,int diseCate,int delmark)throws SQLException;
    //使疾病无效
    void invalideDisease(int id)throws SQLException;
}
