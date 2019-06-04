package vo;

public class PatientExamination {
    private String caseNumber;
    private String name;
    private int age;
    private String settileId;
    private String departmentName;
    private String prescriptionState;
    private String visitDate;//收费日期
    private String userName;
    private String invoiceNum;

    @Override
    public String toString() {
        return "PatientExamination{" +
                "caseNumber='" + caseNumber + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", settileId='" + settileId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", prescriptionState='" + prescriptionState + '\'' +
                ", visitDate='" + visitDate + '\'' +
                ", userName='" + userName + '\'' +
                ", invoiceNum='" + invoiceNum + '\'' +
                '}';
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSettileId() {
        return settileId;
    }

    public void setSettileId(String settileId) {
        this.settileId = settileId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPrescriptionState() {
        return prescriptionState;
    }

    public void setPrescriptionState(String prescriptionState) {
        this.prescriptionState = prescriptionState;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }
}
