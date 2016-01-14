package test.domains; 

import domains.*;
//import javafx.scene.effect.Light;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/** 
* Pos Tester. 
* 
* @author <Authors name> 
* @since <pre>01/04/2015</pre> 
* @version 1.0 
*/ 
public class PosTest extends TestCase {
    @org.junit.Test
    /****************************普通用户购买测试******************************************/
    public void testAddPromotedItems() throws Exception {//测试买二赠一
//TODO: Test goes here...
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,true),6);
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00,true),4);
        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);
        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：可口可乐，数量：6瓶，单价：3.00(元)，小计：15.00(元)\n"
                        + "名称：雪碧，数量：4瓶，单价：3.00(元)，小计：9.00(元)\n"
                        + "----------------------\n"
                        + "挥泪赠送商品：\n"
                        + "名称：可口可乐，数量：1瓶\n"
                        + "名称：雪碧，数量：1瓶\n"
                        + "----------------------\n"
                        + "总计：24.00(元)\n"
                        + "节省：6.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    public void testDiscountItem() throws Exception{//测试打折商品
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000006", "彩虹糖", "罐", 4.00,0.9),2);

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：彩虹糖，数量：2罐，单价：4.00(元)，小计：7.20(元)\n"
                        + "----------------------\n"
                        + "总计：7.20(元)\n"
                        + "节省：0.80(元)\n"
                        + "**********************\n";

        assertThat(actualShoppingList, is(expectedShoppingList));

    }

public void testAddNormalItems() throws Exception{//测试普通商品(即不打折也没有优惠活动)
    ShoppingChart shoppingChart = new ShoppingChart();

    Item cola = new Item("ITEM000000", "可口可乐", "瓶", 3.00);
    Item sprite = new Item("ITEM000001",	"雪碧","瓶",3,00);
    shoppingChart.add(cola,2);
    shoppingChart.add(sprite,2);

    Pos pos = new Pos();
    String actualShoppingList = pos.getShoppingList(shoppingChart);

    Date d=new Date();
    DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
    String time = d5.format(d);
    String expectedShoppingList =
            "***商店购物清单***\n"
                    + "打印时间："+ time
                    + "\n----------------------\n"
                    + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)\n"
                    + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                    + "----------------------\n"
                    + "总计：9.00(元)\n"
                    + "**********************\n";
    assertThat(actualShoppingList, is(expectedShoppingList));

    }

    public void testDelItem() throws Exception {//测试删除商品
        ShoppingChart shoppingChart = new ShoppingChart();

        Item cola = new Item("ITEM000000", "可口可乐", "瓶", 3.00,true);
        shoppingChart.add(cola,3);
        shoppingChart.remove(cola);

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

//    public void testAddItemOverStock() throws Exception{//测试商品数量超过库存
//        ShoppingChart shoppingChart = new ShoppingChart();
//        Item sprite = new Item("ITEM000001", "雪碧", "瓶", 3.00);
//        int actual = shoppingChart.add(sprite,3);
//        int expected = 1;
//        assertThat(actual, is(expected));
//    }

    public void testAddPromotionAndDiscountItems() throws Exception{//添加优惠产品和打折产品
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,true),6);
        Item candy = new Item("ITEM000006","彩虹糖","罐",4,0.9);
        shoppingChart.add(candy,2);

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);
        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：可口可乐，数量：6瓶，单价：3.00(元)，小计：15.00(元)\n"
                        + "名称：彩虹糖，数量：2罐，单价：4.00(元)，小计：7.20(元)\n"
                        + "----------------------\n"
                        + "挥泪赠送商品：\n"
                        + "名称：可口可乐，数量：1瓶\n"
                        + "----------------------\n"
                        + "总计：22.20(元)\n"
                        + "节省：3.80(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

/*********************************会员***********************************/


    public void testIMemberBuyGoods() throws  Exception{//积分第一档  会员购买商品

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.setUserName("USER000003");
        Item coke = new Item("ITEM000009","纯牛奶","箱",60.00);//普通商品
        Item candy = new Item("ITEM000006","彩虹糖","罐",4.00,0.9,false,0.95);//打折商品      //商品的折扣  VIP折扣
        Item shampoo = new Item("ITEM000004","洗发液","瓶",40.00,true);//买二赠一商品
        shoppingChart.add(coke,1);
        shoppingChart.add(candy,1);
        shoppingChart.add(shampoo,3);

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "会员编号：USER000003    会员积分：140分"
                        + "\n----------------------\n"
                        + "打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：纯牛奶，数量：1箱，单价：60.00(元)，小计：60.00(元)\n"
                        + "名称：彩虹糖，数量：1罐，单价：4.00(元)，小计：3.42(元)\n"
                        + "名称：洗发液，数量：3瓶，单价：40.00(元)，小计：80.00(元)\n"
                        + "----------------------\n"
                        + "挥泪赠送商品：\n"
                        + "名称：洗发液，数量：1瓶\n"
                        + "----------------------\n"
                        + "总计：143.42(元)\n"
                        + "节省：40.58(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    public void testIIMemberBuyGoods() throws  Exception{//积分第二档  会员购买商品

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.setUserName("USER000001");
        Item coke = new Item("ITEM000009","纯牛奶","箱",60.00);//普通商品
        Item candy = new Item("ITEM000006","彩虹糖","罐",4.00,0.9,false,0.95);//打折商品      //商品的折扣  VIP折扣
        Item shampoo = new Item("ITEM000004","洗发液","瓶",40.00,true);//买二赠一商品
        shoppingChart.add(coke,1);
        shoppingChart.add(candy,1);
        shoppingChart.add(shampoo,3);

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "会员编号：USER000001    会员积分：140分"
                        + "\n----------------------\n"
                        + "打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：纯牛奶，数量：1箱，单价：60.00(元)，小计：60.00(元)\n"
                        + "名称：彩虹糖，数量：1罐，单价：4.00(元)，小计：3.42(元)\n"
                        + "名称：洗发液，数量：3瓶，单价：40.00(元)，小计：80.00(元)\n"
                        + "----------------------\n"
                        + "挥泪赠送商品：\n"
                        + "名称：洗发液，数量：1瓶\n"
                        + "----------------------\n"
                        + "总计：143.42(元)\n"
                        + "节省：40.58(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    public void testIIIMemberBuyGoods() throws  Exception{//积分第三档  会员购买商品

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.setUserName("USER000007");
        Item coke = new Item("ITEM000009","纯牛奶","箱",60.00);//普通商品
        Item candy = new Item("ITEM000006","彩虹糖","罐",4.00,0.9,false,0.95);//打折商品      //商品的折扣  VIP折扣
        Item shampoo = new Item("ITEM000004","洗发液","瓶",40.00,true);//买二赠一商品
        shoppingChart.add(coke,1);
        shoppingChart.add(candy,1);
        shoppingChart.add(shampoo,3);

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "会员编号：USER000007    会员积分：140分"
                        + "\n----------------------\n"
                        + "打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：纯牛奶，数量：1箱，单价：60.00(元)，小计：60.00(元)\n"
                        + "名称：彩虹糖，数量：1罐，单价：4.00(元)，小计：3.42(元)\n"
                        + "名称：洗发液，数量：3瓶，单价：40.00(元)，小计：80.00(元)\n"
                        + "----------------------\n"
                        + "挥泪赠送商品：\n"
                        + "名称：洗发液，数量：1瓶\n"
                        + "----------------------\n"
                        + "总计：143.42(元)\n"
                        + "节省：40.58(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

  /*  public void testAddItemUsingJson() throws Exception{//通过商品索引及列表添加商品
        File file1 = new File("./Index.json");
        File file2 = new File("./List.json");

        ReadList readList = new ReadList(file1, file2);
        ShoppingChart shoppingChart = readList.parser();

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        Date d=new Date();
        DateFormat d5 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        String time = d5.format(d);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "会员编号：USER000003    会员积分：2分"
                        + "\n----------------------"
                        + "\n打印时间："+ time
                        + "\n----------------------\n"
                        + "名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：8.10(元)\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.28(元)\n"
                        + "----------------------\n"
                        + "总计：12.38(元)\n"
                        + "节省：1.62(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }*/
}
