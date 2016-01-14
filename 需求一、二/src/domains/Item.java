package domains;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Item {
    public String barCode;
    public String name;
    protected String unit;
    protected double price;
    protected double discount;
    public Item(){}

    public Item(String barCode, String name, String unit, double price) {
        this.barCode = barCode;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.discount = 1;
    }

    public Item(String barCode, String name, String unit, double price,double discount) {

        this.barCode = barCode;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
