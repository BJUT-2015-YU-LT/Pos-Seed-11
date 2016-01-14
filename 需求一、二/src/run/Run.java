package run;

import domains.*;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/5.
 */


public class Run {
    private ArrayList<Item> items = new ArrayList<Item>();

    public static void main(String[] args) throws Exception {
        int i =0;
        Run run = new Run();
        run.getinput("ITEM000000","可口可乐","瓶",3.00,1);

        run.getinput("ITEM000000","可口可乐","瓶",3.00,1);
        run.getinput("ITEM000000","可口可乐","瓶",3.00,1);
        run.getinput("ITEM000000","可口可乐","瓶",3.00,1);
        run.getinput("ITEM000000","可口可乐","瓶",3.00,1);
        run.getinput("ITEM000000","可口可乐","瓶",3.00,1);
        run.getinput("ITEM000001","雪碧","瓶",3.00,1);
        run.getinput("ITEM000001","雪碧","瓶",3.00,1);
        run.getinput("ITEM000004","电池","个",2.00,0.8);
        ShoppingChart shoppingChart = run.chart();
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);
        System.out.println(result);

    }
    public Item getinput(String barCode,String name,String unit,double price,double discount)throws Exception{
        Item item1 = new Item();
        item1.setBarCode(barCode);
        item1.setName(name);
        item1.setUnit(unit);
        item1.setPrice(price);
        item1.setDiscount(discount);
        items.add(item1);
        return item1;
    }
    public ShoppingChart chart() throws  Exception
    {
        Run run = new Run();
        int i=0;
        ShoppingChart shoppingChart=new ShoppingChart();

        while (i<items.size()) {
            shoppingChart.add(items.get(i));
            i++;
        }
        return  shoppingChart;
    }
}
