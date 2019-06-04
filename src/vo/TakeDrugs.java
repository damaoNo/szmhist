package vo;

import java.util.Date;

public class TakeDrugs {
    //药品名称
    private String drugsName;
    //单价
    private Double drugsPrice;
    //药品状态
    private int DelMark;
    //数量
    private double Amount;
    //医生名字
    private String RealName;
    //药方名称
    private String PrescriptionName;
    //开立时间
    private Date PrescriptionTime;
    //药方状态
    private int State;

    public int getPrescriptionID() {
        return PrescriptionID;
    }

    public void setPrescriptionID(int prescriptionID) {
        PrescriptionID = prescriptionID;
    }

    //成药处方ID
    private int PrescriptionID;

    @Override
    public String toString() {
        return "TakeDrugs{" +
                "drugsName='" + drugsName + '\'' +
                ", drugsPrice=" + drugsPrice +
                ", DelMark=" + DelMark +
                ", Amount=" + Amount +
                ", RealName='" + RealName + '\'' +
                ", PrescriptionName='" + PrescriptionName + '\'' +
                ", PrescriptionTime=" + PrescriptionTime +
                ", State=" + State +
                ", PrescriptionID=" + PrescriptionID +
                '}';
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName;
    }

    public Double getDrugsPrice() {
        return drugsPrice;
    }

    public void setDrugsPrice(Double drugsPrice) {
        this.drugsPrice = drugsPrice;
    }

    public int getDelMark() {
        return DelMark;
    }

    public void setDelMark(int delMark) {
        DelMark = delMark;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getPrescriptionName() {
        return PrescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        PrescriptionName = prescriptionName;
    }

    public Date getPrescriptionTime() {
        return PrescriptionTime;
    }

    public void setPrescriptionTime(Date prescriptionTime) {
        PrescriptionTime = prescriptionTime;
    }

}
