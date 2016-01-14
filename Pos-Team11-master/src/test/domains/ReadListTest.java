package test.domains; 

import domains.Item;
import domains.ReadList;
import junit.framework.Test;
import junit.framework.TestSuite; 
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.System.out;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * ReadList Tester.
 *
 * @author <Enssie>
 * @version 1.0
 * @since <pre>01/04/2015</pre>
 */
public class ReadListTest extends TestCase {
    private File file1;
    private File file2;
    private File file3;

    @Before
    public void setUp() throws Exception {
        file1 = new File("./Index.json");
        file2 = new File("./List.json");
        file3 = new File("./Member.json");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        if (!file3.exists()) {
            file3.createNewFile();
        }
    }

    @After
//   public void tearDown() throws Exception {
//      if(file1.exists()){
//         file1.delete();
//      }
//      if(file2.exists()){
//         file2.delete();
//      }
//   }

    @org.junit.Test
    public void testParser() throws Exception {

        ReadList readList = new ReadList(file1, file2);
        ArrayList<Item> items = readList.parser().getItems();
        assertThat(items.size(), is(3));
        for (int i = 0; i <items.size(); i++) {
            Item item = items.get(i);
            System.out.println("名称："+item.getName());
            System.out.println("条码："+item.getBarCode());
            System.out.println("单位："+item.getUnit());
            System.out.println("数量："+item.getQuantity());
            System.out.println("单价："+item.getPrice());
            System.out.println("折扣："+item.getDiscount());
            System.out.println();
        }
    }

    public void testNewList() throws Exception{//测试加入会员信息后读取的信息是否正确
        ReadList readList = new ReadList(file1, file2);
        String user = readList.parser().getUserName();
        ArrayList<Item> items = readList.parser().getItems();
        assertThat(items.size(), is(3));
        for (int i = 0; i <items.size(); i++) {
            Item item = items.get(i);
            System.out.println("会员："+user);
            System.out.println("名称："+item.getName());
            System.out.println("条码："+item.getBarCode());
            System.out.println("单位："+item.getUnit());
            System.out.println("数量："+item.getQuantity());
            System.out.println("单价："+item.getPrice());
            System.out.println("VIP折扣："+item.getVipDiscount());
            System.out.println("折扣："+item.getDiscount());
            System.out.println("买二赠一："+item.isPromotion());
            System.out.println();
        }

    }
}