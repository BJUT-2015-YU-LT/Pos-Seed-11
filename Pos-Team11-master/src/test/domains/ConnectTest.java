package test.domains;

import domains.Connect;
import domains.Item;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Connect Tester.
 *
 * @author <Authors name>
 * @since <pre>01/05/2015</pre>
 * @version 1.0
 */
public class ConnectTest extends TestCase {
    public void testCompareDate() throws Exception {
        Connect con = new Connect();
        long date = 20140105;
        Date d1 = new Date(date);
        date = 20140106;
        Date d2 = new Date(date);
        int actual = con.compare_date(d1,d2);
        int expected = -1;
        assertThat(actual,is(expected));
    }

//    public void testCheckStock() throws  Exception{
//        Connect con = new Connect();
//        con.connect();
//        List<Item> Expectedstock = new ArrayList<Item>();
//        Item coke = new Item("ITEM000000","可口可乐","瓶",3.00,30);
//        Expectedstock.add(coke);
//        List<Item> Actualstock = con.checkStock();
//        assertThat(Actualstock,is(Expectedstock));
//    }
    /*public void testCheckDiscount() throws Exception{//查看是否在打折期限
        Connect con = new Connect();
        con.connect();
        long d = 20140506;
        Date date = new Date(d);

        Item item = new Item("ITEM000003", "牙膏", "支", 10.00);
        boolean actual = con.checkDiscount(item,date);

        assertThat(actual,is(false));
    }*/

    public void testCheckQuantity() throws Exception{
        String barCode = "ITEM000000";
        String barCode2 = "ITEM00015";
        Connect con = new Connect();
        con.connect();
        int actual = con.checkQuantity(barCode);
        int actual2 = con.checkQuantity(barCode2);
        int expected = 30;
        assertThat(actual,is(expected));
        assertThat(actual2,is(-1));
    }

    public void testCheckCode() throws Exception{//测试编号是否存在
        String barCode1 = "ITEM000015";
        String barCode2 = "ITEM000010";
        Connect con = new Connect();
        con.connect();
        boolean actual = con.checkCode(barCode1);
        boolean actual2 = con.checkCode(barCode2);
        boolean expected = false;
        boolean expected2 = true;
        assertThat(actual,is(expected));
        assertThat(actual2,is(expected2));
    }

    public void testCheckByBarCode() throws Exception{
        String barCode = "ITEM000009";
        Connect con = new Connect();
        con.connect();
        Item item = con.checkByBarCode(barCode);
        assertThat(item.getName(),is("纯牛奶"));
        assertThat(item.getUnit(),is("箱"));
        assertThat(item.getPrice(),is(60.00));
        assertThat(item.getQuantity(),is(10));
        assertThat(item.getVipDiscount(),is(1.00));
        assertThat(item.getDiscount(),is(1.00));
        assertThat(item.isPromotion(),is(false));
    }


    public void testMatchByBarCode() throws  Exception{
        Item CD = new Item("ITEM000005","CD","张",10,0.4,false,0.9);
        Connect con = new Connect();
        con.connect();
        Boolean actual = con.matchByBarcode(CD);
        assertThat(actual,is(false));
    }

    public void testCheckPoints() throws  Exception{
        String User = "USER000002";
        Connect con = new Connect();
        con.connect();
        int actual = con.checkPoints(User);
        assertThat(actual,is(180));
    }

    public void testUpdatePoints() throws Exception{
        String User = "USER000003";
        int point =0;

        Connect con = new Connect();
        con.connect();
        con.updatePoints(User,point);
        int actual = con.checkPoints(User);
        assertThat(actual,is(point));
    }
} 
