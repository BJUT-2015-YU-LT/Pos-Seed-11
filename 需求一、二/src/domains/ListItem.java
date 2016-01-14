package domains;

/**
 * Created by Administrator on 2016/1/5.
 */
public class ListItem extends Item{
    private int amount;
    private double subTotal;
    private double saveMoney;

    public ListItem(Item item) {
        this.barCode = item.barCode;
        this.name = item.name;
        this.unit = item.unit;
        this.price = item.price;
        this.discount = item.discount;
        this.amount = 1;
        this.subTotal = item.price*discount;
        this.saveMoney =item.price*(1-discount);
    }

    public void create()
    {
        this.amount+=1;
        this.subTotal+=this.price*this.discount;
        this.saveMoney+=this.price*(1-this.discount);

    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(double saveMoney) {
        this.saveMoney = saveMoney;
    }


}
