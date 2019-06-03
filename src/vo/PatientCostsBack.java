/**
 * * @ClassName: PatientCostsBack
 * * @description: 患者退费
 * * @author: cro
 * * @create: 2019-06-03 11:25
 **/

package vo;

public class PatientCostsBack extends PatientCosts {
    //患者名字
    private String realName;
    //患者身份证号
    private int idNum;
    //患者家庭住址
    private String homeAdd;
    //病历号
    private String caseNum;
    //看诊状态
    private int visitState;

    public int getVisitState() {
        return visitState;
    }

    public void setVisitState(int visitState) {
        this.visitState = visitState;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    @Override
    public String toString() {
        return "PatientCostsBack{" +
                "realName='" + realName + '\'' +
                ", idNum=" + idNum +
                ", homeAdd='" + homeAdd + '\'' +
                ", caseNum='" + caseNum + '\'' +
                '}';
    }
}
