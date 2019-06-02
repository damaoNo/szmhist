/**
 * * @ClassName: RegistRecord
 * * @description: 挂号记录
 * * @author: cro
 * * @create: 2019-06-02 10:04
 **/

package vo;

import java.util.Date;

public class RegisterRecord{
    //科室名字
    private  String deptName;
    //唯一标识
    private int id;
    //病例号
    private String caseNumber;
    //真实姓名
    private String realName;
    //性别
    private int gender;
    //身份证号
    private String idNumber;
    //出生日期
    private Date birthDate;
    //年龄
    private int age;
    //年龄类型（年-月-日）
    private char ageTpye;
    //家庭住址
    private String homeAddress;
    //本次看诊日期
    private Date visitDate;
    //午别
    private String noon;
    //本次挂号科室id
    private int deptID;
    //本次挂号医生id
    private int userID;
    //挂号级别id
    private int registLeID;
    //挂号类别id
    private int settLeID;
    //是否需要病历本
    private char isBook;
    //挂号时间
    private Date registTime;
    //挂号员id
    private int registerID;
    //看诊状态（1-已经挂号；2-医生接诊；3-看诊结束；4-已退号）
    private int visitState;

    public RegisterRecord( int id, String caseNumber, String realName, int gender, String idNumber, Date birthDate, int age, char ageTpye, String homeAddress, Date visitDate, String noon, int deptID, int userID, int registLeID, int settLeID, char isBook, Date registTime, int registerID, int visitState,String deptName) {
        this.deptName = deptName;
        this.id = id;
        this.caseNumber = caseNumber;
        this.realName = realName;
        this.gender = gender;
        this.idNumber = idNumber;
        this.birthDate = birthDate;
        this.age = age;
        this.ageTpye = ageTpye;
        this.homeAddress = homeAddress;
        this.visitDate = visitDate;
        this.noon = noon;
        this.deptID = deptID;
        this.userID = userID;
        this.registLeID = registLeID;
        this.settLeID = settLeID;
        this.isBook = isBook;
        this.registTime = registTime;
        this.registerID = registerID;
        this.visitState = visitState;
    }

    public RegisterRecord() {
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getAgeTpye() {
        return ageTpye;
    }

    public void setAgeTpye(char ageTpye) {
        this.ageTpye = ageTpye;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRegistLeID() {
        return registLeID;
    }

    public void setRegistLeID(int registLeID) {
        this.registLeID = registLeID;
    }

    public int getSettLeID() {
        return settLeID;
    }

    public void setSettLeID(int settLeID) {
        this.settLeID = settLeID;
    }

    public char getIsBook() {
        return isBook;
    }

    public void setIsBook(char isBook) {
        this.isBook = isBook;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public int getRegisterID() {
        return registerID;
    }

    public void setRegisterID(int registerID) {
        this.registerID = registerID;
    }

    public int getVisitState() {
        return visitState;
    }

    public void setVisitState(int visitState) {
        this.visitState = visitState;
    }

    @Override
    public String toString() {
        return "RegistRecord{" +
                "deptName='" + deptName + '\'' +
                ", id=" + id +
                ", caseNumber='" + caseNumber + '\'' +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", idNumber='" + idNumber + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", ageTpye=" + ageTpye +
                ", homeAddress='" + homeAddress + '\'' +
                ", visitDate=" + visitDate +
                ", noon='" + noon + '\'' +
                ", deptID=" + deptID +
                ", userID=" + userID +
                ", registLeID=" + registLeID +
                ", settLeID=" + settLeID +
                ", isBook=" + isBook +
                ", registTime=" + registTime +
                ", registerID=" + registerID +
                ", visitState=" + visitState +
                '}';
    }
}
