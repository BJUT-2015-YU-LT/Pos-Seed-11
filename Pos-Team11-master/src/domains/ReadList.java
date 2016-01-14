package domains;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import sun.nio.cs.ext.GBK;

import java.io.*;
import java.util.*;

/**
 * Created by Sylvia on 2015/1/3.
 */
public class ReadList {
    private File index;
    private File list;
    private final ObjectMapper objectMapper;

    public ReadList(File index,File list) {
        this.index = index;
        this.list=list;
        objectMapper = new ObjectMapper(new JsonFactory());
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    public ShoppingChart parser() throws IOException {
        //String textInput = FileUtils.readFileToString(index);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(index),"GBK"));
        StringBuilder stringBuilder = new StringBuilder();
        String data = null;
        do {
            data = bufferedReader.readLine();
            if (data != null) {
                stringBuilder.append(data);
            }
        } while (data != null);
        String textInput = stringBuilder.toString();
        //ArrayList<Item> indexOfGoods=new ArrayList<Item>();
        Map<String, Map<String,Object>> maps = objectMapper.readValue(textInput, Map.class);
        Map<String,Item> indexOfGoods=new HashMap<String, Item>();
        Set<String> key = maps.keySet();
        Iterator<String> iter = key.iterator();

        try{
            while (iter.hasNext()) {
                String field = iter.next();
                String name = maps.get(field).get("name").toString();
                String unit = maps.get(field).get("unit").toString();
                double price = Double.parseDouble(maps.get(field).get("price").toString());
                double discount=1;
                double vipDiscount=1;
                boolean isPromotion=false;
                if(maps.get(field).containsKey("discount")){
                    //indexOfGoods.put(field,new Item(field,name,unit,price,Double.parseDouble(maps.get(field).get("discount").toString())));
                    discount=Double.parseDouble(maps.get(field).get("discount").toString());
                }
                if(maps.get(field).containsKey("vipDiscount")){
                    vipDiscount=Double.parseDouble(maps.get(field).get("vipDiscount").toString());
                }
                if(maps.get(field).containsKey("promotion")) {
                    //indexOfGoods.put(field,new Item(field, name, unit, price, Boolean.parseBoolean(maps.get(field).get("promotion").toString())));
                    isPromotion = Boolean.parseBoolean(maps.get(field).get("promotion").toString());
                }

                indexOfGoods.put(field,new Item(field,name,unit,price,discount,isPromotion,vipDiscount));

            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        //ShoppingChart shoppingChart = new ShoppingChart();

        return BuildShoppingChart(indexOfGoods);
    }

    private ShoppingChart BuildShoppingChart(Map<String,Item> maps) throws IOException {
        ShoppingChart shoppingChart = new ShoppingChart();
        Map<String,ArrayList<String>> list=this.getListOfGoods();

        if(list.size()==0){
            System.out.println(0);
        }

        if(list.get("user")==null){
            shoppingChart.setUserName("NULL");
        }
        else{
            shoppingChart.setUserName(list.get("user").get(0));
        }

        for(int i=0;i<list.get("items").size();i++){
            if(maps.get(list.get("items"))==null){
                //抛出空异常
            }
            shoppingChart.add(maps.get(list.get("items").get(i)));
        }

        return shoppingChart;
    }

    private Map<String,ArrayList<String>> getListOfGoods() throws IOException{
        String textInput = FileUtils.readFileToString(list);
        Map<String,ArrayList<String>> listOfGoods=new HashMap<String, ArrayList<String>>();
        //ArrayList<Item> indexOfGoods=new ArrayList<Item>();
        ArrayList<String> list=new ArrayList<String>();
        //listOfGoods = objectMapper.readValue(textInput, Map.class);
        //return listOfGoods;

        try {
            listOfGoods = objectMapper.readValue(textInput, Map.class);
            //System.out.println("hhh");
        }
        catch (Exception exception){
            list=objectMapper.readValue(textInput, ArrayList.class);
            listOfGoods.put("items",list);
            //System.out.println("hhh");
        }
        finally {
            //System.out.println("hhhyh");
            return listOfGoods;
        }
    }

}
