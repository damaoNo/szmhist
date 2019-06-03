/**
 * * @ClassName: SchedDoctor
 * * @description: 医生排班信息
 * * @author: cro
 * * @create: 2019-06-02 21:14
 **/

package vo;

import java.util.Date;

public class SchedDoctor {

    /**排版时间*/
    private Date schedDate;
    /**排班午别*/
    private String noon;
    /**部门编号*/
    private int deptID;
    /**挂号级别*/
    private int registLeID;

    public SchedDoctor(Date schedDate, String noon, int deptID, int registLeID) {
        this.schedDate = schedDate;
        this.noon = noon;
        this.deptID = deptID;
        this.registLeID = registLeID;
    }

    public SchedDoctor() {
    }

    @Override
    public String toString() {
        return "SchedDoctor{" +
                "schedDate=" + schedDate +
                ", noon='" + noon + '\'' +
                ", deptID=" + deptID +
                ", registLeID=" + registLeID +
                '}';
    }

    public Date getSchedDate() {
        return schedDate;
    }

    public void setSchedDate(Date schedDate) {
        this.schedDate = schedDate;
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

    public int getRegistLeID() {
        return registLeID;
    }

    public void setRegistLeID(int registLeID) {
        this.registLeID = registLeID;
    }
}
