package vo;

import java.util.Date;

public class NonDrugsPay {
    //非药品收费项目表ID
    private int ID;
    //项目编码
    private String ItemCode;
    //项目名称
    private String ItemName;
    //规格
    private String Format;
    //单价
    private Double Price;
    //所述费用科目ID
    private int ExpClassID;
    //执行科室ID
    private int DeptID;
    //拼音助记码
    private String MnemonicCode;
    //创建时间
    private Date CreationDate;
    //最后修改时间
    private Date LastUpdateDate;
    //项目类型
    private int RecordType;
    //删除标记
    private int DelMark;
    //费用科目名称
    private String ExpName;
    //科室名称
    private String DeptName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getExpClassID() {
        return ExpClassID;
    }

    public void setExpClassID(int expClassID) {
        ExpClassID = expClassID;
    }

    public int getDeptID() {
        return DeptID;
    }

    public void setDeptID(int deptID) {
        DeptID = deptID;
    }

    public String getMnemonicCode() {
        return MnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        MnemonicCode = mnemonicCode;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return LastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        LastUpdateDate = lastUpdateDate;
    }

    public int getRecordType() {
        return RecordType;
    }

    public void setRecordType(int recordType) {
        RecordType = recordType;
    }

    public int getDelMark() {
        return DelMark;
    }

    public void setDelMark(int delMark) {
        DelMark = delMark;
    }

    public String getExpName() {
        return ExpName;
    }

    public void setExpName(String expName) {
        ExpName = expName;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    @Override
    public String toString() {
        return "NonDrugsPayDao{" +
                "ID=" + ID +
                ", ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", Format='" + Format + '\'' +
                ", Price=" + Price +
                ", ExpClassID=" + ExpClassID +
                ", DeptID=" + DeptID +
                ", MnemonicCode='" + MnemonicCode + '\'' +
                ", CreationDate=" + CreationDate +
                ", LastUpdateDate=" + LastUpdateDate +
                ", RecordType=" + RecordType +
                ", DelMark=" + DelMark +
                ", ExpName='" + ExpName + '\'' +
                ", DeptName='" + DeptName + '\'' +
                '}';
    }
}
