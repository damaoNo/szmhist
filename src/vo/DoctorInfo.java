package vo;

import java.util.Date;

public class DoctorInfo {
    //排班表id
    private int ID;
    //排班日期
    private Date SchedDate;
    //午别
    private String noon;
    //科室名称
    private String DeptName;
    //真实姓名
    private String RealName;
    //号别名称（挂号级别）
    private String RegistName;
    //挂号限额
    private int RegistQuota;

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "ID=" + ID +
                ", SchedDate=" + SchedDate +
                ", noon='" + noon + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", RealName='" + RealName + '\'' +
                ", RegistName='" + RegistName + '\'' +
                ", RegistQuota=" + RegistQuota +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getSchedDate() {
        return SchedDate;
    }

    public void setSchedDate(Date schedDate) {
        SchedDate = schedDate;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getRegistName() {
        return RegistName;
    }

    public void setRegistName(String registName) {
        RegistName = registName;
    }

    public int getRegistQuota() {
        return RegistQuota;
    }

    public void setRegistQuota(int registQuota) {
        RegistQuota = registQuota;
    }
}
