/**
 * * @ClassName: CheckApplyMore
 * * @description: 更多信息的处置申请
 * * @author: cro
 * * @create: 2019-06-05 17:47
 **/

package vo;

public class CheckApplyMore extends CheckApply{
    private String itemName;
    private double price;


    public String getItemName() {
        return itemName;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CheckApplyMore{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                '}'+super.toString();
    }
}
