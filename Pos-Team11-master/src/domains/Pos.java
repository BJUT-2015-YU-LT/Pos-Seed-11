package domains;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sylvia on 2014/12/30.
 */
public class Pos {
    ArrayList<Item> giftProduct=new ArrayList<Item>();
    Connect con=new Connect();

    public String getShoppingList(ShoppingChart shoppingChart) throws IOException {
        ArrayList<Item> items = shoppingChart.getItems();
        double total=0.0;
        double save=0.0;

        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("***商店购物清单***\n");
        stringBuilder.append("打印时间：")
                .append(this.timeOfPrint())
                .append("\n")
                .append("----------------------\n");

        for(int i=0;i<items.size();i++) {
            Item item = items.get(i);
            int amountOfItem = item.getQuantity();
            double priceOfItem = item.getPrice();
            double discountOfItem=item.getDiscount();
            String nameOfItem = item.getName();
            String unitOfItem = item.getUnit();
            double subTotal = this.calSubTotal(item,shoppingChart.getUserName());
            save+=priceOfItem * amountOfItem-subTotal;
            total += subTotal;
            stringBuilder
                    .append("名称：").append(nameOfItem).append("，")
                    .append("数量：").append(amountOfItem).append(unitOfItem).append("，")
                    .append("单价：").append(String.format("%.2f", priceOfItem)).append("(元)").append("，")
                    .append("小计：").append(String.format("%.2f", subTotal)).append("(元)").append("\n");
        }
        if(giftProduct.size()>0){
            stringBuilder
                    .append("----------------------\n")
                    .append("挥泪赠送商品：\n");
            for(int i=0;i<giftProduct.size();i++){
                stringBuilder
                        .append("名称：").append(giftProduct.get(i).getName()).append("，")
                        .append("数量：").append("1").append(giftProduct.get(i).getUnit()).append("\n");
            }
        }

        stringBuilder
                .append("----------------------\n")
                .append("总计：").append(String.format("%.2f", total)).append("(元)").append("\n");
        if(save!=0.0){
            stringBuilder.append("节省：").append(String.format("%.2f", save)).append("(元)").append("\n");
        }
        stringBuilder.append("**********************\n");

        if(shoppingChart.getUserName()!="NULL"){
            stringBuilder.insert(0,this.getUserInformation(shoppingChart.getUserName(),total));
        }
        stringBuilder.insert(0,"***商店购物清单***\n");
        return stringBuilder.toString();
    }

    private double calSubTotal(Item item,String name){
        if(name=="NULL"){
            if(item.getDiscount()!=1){
                //检查是否在活动期限之内
                if(false){
                    return item.getQuantity()*item.getPrice();
                }
                return item.getQuantity()*item.getPrice()*item.getDiscount();
            }
            else if((item.isPromotion()==true)&&item.getQuantity()>=3){
                //检查是否在活动期限之内
                if(false){
                    return item.getQuantity()*item.getPrice();
                }
                giftProduct.add(item);
                return (item.getQuantity()-1)*item.getPrice();
            } else {
                return item.getQuantity() * item.getPrice();
            }
        }
        else {
            if (item.getDiscount() != 1) {
                //检查是否在活动期限之内
                if (false) {
                    return item.getQuantity() * item.getPrice();
                }
                return item.getQuantity() * item.getPrice() * item.getDiscount() * item.getVipDiscount();
            } else if (item.getVipDiscount() != 1) {
                return item.getQuantity() * item.getPrice() * item.getVipDiscount();
            } else if ((item.isPromotion() == true) && item.getQuantity() >= 3) {
                //检查是否在活动期限之内
                if (false) {
                    return item.getQuantity() * item.getPrice();
                }
                giftProduct.add(item);
                return (item.getQuantity() - 1) * item.getPrice();
            } else {
                return item.getQuantity() * item.getPrice();
            }
        }
        //return 0;
    }

    //获取当前时间
    private String timeOfPrint(){
        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL); //显示日期，周，时间（精确到秒）
        return d5.format(d);
    }

    //会员名和积分
    public String getUserInformation(String name,double total){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("会员编号：").append(name).append("    会员积分：");
        int point=0;
//        this.con.connect();

//        int points=con.checkPoints(name);

//        if(points==-1){
 //           return null;
 //       }
 //       if(points<=200){
 //           double a=Math.floor(total/5);
 //           point=(int)a;
 //           stringBuilder.append((points+point));
 //        }
 //       else if(points<=500){
 //           double a=Math.floor(total/5)*3;
 //           point=(int)a;
 //           stringBuilder.append((points+point));
 //       }
//        else{
            double a=Math.floor(total/5)*5;
            point=(int)a;
            stringBuilder.append(point);
 //           stringBuilder.append((points+point));
 //       }

//        con.updatePoints(name,points+point);
        stringBuilder.append("分\n----------------------\n");
//        con.disconnect();
        return stringBuilder.toString();
    }

}
