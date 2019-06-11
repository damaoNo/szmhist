/**
 * * @ClassName: PrescriptionMore
 * * @description: 包含更多信息的处方
 * * @author: cro
 * * @create: 2019-06-05 17:25
 **/

package vo;

import java.util.Date;

public class PrescriptionMore extends Prescription{
    private String drugName;
    private double price;
    private double amout;

    public double getPrice() {
        return price;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmout() {
        return amout;
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }

    @Override
    public String toString() {
        return "PrescriptionMore{" +
                "drugName='" + drugName + '\'' +
                ", price=" + price +
                '}'+super.toString();
    }
}
