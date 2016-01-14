package test;

import domains.Item;
import domains.Pos;
import domains.ShoppingChart;

import static org.hamcrest.CoreMatchers.is;
import domains.ShoppingListChart;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/1/6.
 */
public class PosTest {
    @Test
    public void testGetShoppingListForSingleItem() throws Exception {
        Item cokeCola = new Item("ITEM000000", "可口可乐", "瓶", 3.00);
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(cokeCola);
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，单价：3.00(元)，小计：3.00(元)\n"
                        + "----------------------\n"
                        + "总计：3.00(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetShoppingListForDoubleItem() throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1));
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，单价：3.00(元)，小计：6.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetShoppingListForDifferItem() throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1));
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：雪碧，单价：3.00(元)，小计：3.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetShoppingListForDiscountItem() throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8));
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：1.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetShoppingListForDifferDisItem() throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8));
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：4.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }
}