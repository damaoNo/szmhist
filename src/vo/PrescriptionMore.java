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

    public double getPrice() {
        return price;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    @Override
    public String toString() {
        return "PrescriptionMore{" +
                "drugName='" + drugName + '\'' +
                ", price=" + price +
                '}'+super.toString();
    }
}
