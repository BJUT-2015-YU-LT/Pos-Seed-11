package test;

import domains.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/1/5.
 */
public class PosTest {

    Calendar calendar= Calendar.getInstance();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
    String currentTime=simpleDateFormat.format(calendar.getTime());

    @Test
    public void testGetCorrectShoppingListForDoubleItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }


    @Test
    public void testGetCorrectShoppingListForTrebleItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "----------------------\n"
                        + "挥泪赠送商品:\n"
                        + "名称：可口可乐，数量：1瓶\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "节省：3.00(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }


    @Test
    public void testGetCorrectShoppingListForTwoDiffer1ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：4.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }


    @Test
    public void testGetCorrectShoppingListForTwoDiffer7ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：电池，数量：2个，单价：2.00(元)，小计：3.20(元)\n"
                        + "----------------------\n"
                        + "总计：3.20(元)\n"
                        + "节省：0.80(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForThreeDiffer1ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：7.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForThreeDiffer7ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：雪碧，数量：2瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "名称：电池，数量：2个，单价：2.00(元)，小计：3.20(元)\n"
                        + "----------------------\n"
                        + "总计：12.20(元)\n"
                        + "节省：0.80(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForThreeDiffer9ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 1, true));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00, 0.8, false));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "名称：雪碧，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "名称：电池，数量：3个，单价：2.00(元)，小计：4.80(元)\n"
                        + "----------------------\n"
                        + "挥泪赠送商品:\n"
                        + "名称：可口可乐，数量：1瓶\n"
                        + "名称：雪碧，数量：1瓶\n"
                        + "----------------------\n"
                        + "总计：16.80(元)\n"
                        + "节省：7.20(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }


    @Test
    public void testGetCorrectShoppingListForWrongVersion1ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 0.8, true));


        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = "Discount and promotion can't exist both, barCode: ITEM000000\n"
                + pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "Discount and promotion can't exist both, barCode: ITEM000000\n"
                        + "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：2.40(元)\n"
                        + "----------------------\n"
                        + "总计：2.40(元)\n"
                        + "节省：0.60(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }


    @Test
    public void testGetCorrectShoppingListForWrongVersion2ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 0.8, true));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00, 0.8, true));


        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = "Discount and promotion can't exist both, barCode: ITEM000000\n"
                + "Discount and promotion can't exist both, barCode: ITEM000001\n"
                + pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "Discount and promotion can't exist both, barCode: ITEM000000\n"
                        + "Discount and promotion can't exist both, barCode: ITEM000001\n"
                        + "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：2.40(元)\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：2.40(元)\n"
                        + "----------------------\n"
                        + "总计：4.80(元)\n"
                        + "节省：1.20(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }


    @Test
    public void testGetCorrectShoppingListForWrongVersion3ItemForReq4() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 0.8, true));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00, 0.8, true));


        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = "Discount and promotion can't exist both, barCode: ITEM000000\n"
                + "Discount and promotion can't exist both, barCode: ITEM000000\n"
                + pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "Discount and promotion can't exist both, barCode: ITEM000000\n"
                        + "Discount and promotion can't exist both, barCode: ITEM000000\n"
                        + "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：4.80(元)\n"
                        + "----------------------\n"
                        + "总计：4.80(元)\n"
                        + "节省：1.20(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForTwoSameItems() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000005", "吹风机", "件", 83.00));
        shoppingChart.add(new Item("ITEM000005", "吹风机", "件", 83.00));

        // when
        ShoppingListChart shoppingListChart = new ShoppingListChart(shoppingChart);
        Pos pos = new Pos();
        String result = pos.getShoppingList(shoppingListChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间：" + currentTime + "\n"
                        + "----------------------\n"
                        + "名称：吹风机，数量：2件，单价：83.00(元)，小计：166.00(元)\n"
                        + "----------------------\n"
                        + "总计：166.00(元)\n"
                        + "**********************\n";
        assertThat(result, is(expectedShoppingList));
    }

}