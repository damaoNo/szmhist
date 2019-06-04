/**
 * * @ClassName: PatientCheckApply
 * * @description: 病人的处置申请表
 * * @author: cro
 * * @create: 2019-06-04 09:16
 **/

package vo;

public class PatientCheckApply extends CheckApply{
    //项目名称
    private String itemName;
    //执行科室名称
    private String deptName;
    //规格
    private String format;
    //单价
    private double price;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PatientCheckApply{" +
                "itemName='" + itemName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", format='" + format + '\'' +
                ", price=" + price +
                '}';
    }
}
