package domains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhiwei on 2015/1/2.
 */
public class Connect {
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;

    //数据库连接
    public void connect(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "silly");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //断开数据库连接
    public void  disconnect(){
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //生成错误内容报告log文件
    public void log(StringBuilder error){
        StringBuilder log=new StringBuilder();
        try {
            File file=new File("./errorLog.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(file.getName(),true);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            log
                    .append("【")
                    .append(dateFormat.format(calendar.getTime()))//记录当前系统时间
                    .append("】")
//                    .append("\r\n")
                    .append("Error：" + error)
                    .append("\r\n");
            bufferedWriter.write(String.valueOf(log));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //日期比较
    public int compare_date(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /*
    * 以下为关于item数据表的操作
    * */
    //查询库存商品,返回全部商品信息
    public List checkStock(){
        List<Item> stock = new ArrayList<Item>();
        String select_all="select * from Item";
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(select_all);
            while(rs.next()){
                String barCode = rs.getString("barcode");
                String name =rs.getString("name");
                String unit =rs.getString("unit");
                double price = rs.getDouble("price");
                int quantity=rs.getInt("quantity");
                double vipDiscount=rs.getDouble("vipdiscount");
                double discount=rs.getDouble("discount");
                boolean promotion=rs.getBoolean("promotion");
                Item item=new Item(barCode,name,unit,price,quantity,discount,promotion,vipDiscount);
                stock.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    //根据商品编号返回该商品的全部信息，返回值为Item对象
    public Item checkByBarCode(String barCode){
        String select_item="select * from item where barcode ='"+barCode+"'";
        Item item=new Item(null,null,null,0,0,true,0);
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(select_item);
            if(rs.next()){
                String name =rs.getString("name");
                String unit =rs.getString("unit");
                double price = rs.getDouble("price");
                int quantity=rs.getInt("quantity");
                double vipDiscount=rs.getDouble("vipdiscount");
                double discount=rs.getDouble("discount");
                boolean promotion=rs.getBoolean("judge");
                item=new Item(barCode,name,unit,price,quantity,discount,promotion,vipDiscount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    //根据商品编号查询商品库存数量，编号不存在能返回-1
    public int checkQuantity(String barCode){
        StringBuilder error=new StringBuilder();
        int quantity=-1;
        String select_quantity="select quantity ,barcode from item where barcode ='"+barCode+"'";
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(select_quantity);
            if(rs.next()&&rs.getString("barcode").equals(barCode))
                quantity=rs.getInt("quantity");
            else{
                error
                        .append("Input——"+barCode+",")
                        .append("Result——barcode doesn't exist;\r\n");
            }
            log(error);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    //判断商品编号是否存在
    public boolean checkCode(String barCode) {
        boolean a=false;
        StringBuilder error=new StringBuilder();
        String select_code = "select * from item where barcode='" +barCode + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(select_code);
           if( rs.next()){
               if (rs.getString("barcode").equals(barCode))
                    a = true;
           }
           else{
                error.append("Input——"+barCode+",")
                     .append("Result——barcode doesn't exist;\r\n");
                log(error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }


    //根据商品编号判断该商品的其他信息是否与输入的匹配
    public boolean matchByBarcode(Item item){
        boolean a=true;
        int i=0;
        StringBuilder error=new StringBuilder();
        String select_match="select * from item where barcode='"+item.getBarCode()+"'";
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(select_match);
            while(rs.next()) {
                String name = rs.getString("name");
                String unit = rs.getString("unit");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                boolean promotion = rs.getBoolean("judge");
                double vipDiscount=rs.getDouble("vipdiscount");
                if (!item.getName().equals(name)) {
                    a = false;
                    error
                            .append("Input——" + item.getName() + ",")
                            .append("Output——" + name+",")
                            .append("Result——name doesn't matched;\r\n");
                }
                if (!item.getUnit().equals(unit)) {
                    a = false;
                    error
                            .append("Input——" + item.getUnit() + ",")
                            .append("Output——" + unit+",")
                            .append("Result——unit doesn't matched;\r\n");
                }
                if (item.getPrice() != price) {
                    a = false;
                    error
                            .append("Input——" + item.getPrice() + ",")
                            .append("Output——" + price+",")
                            .append("Result——price doesn't matched;\r\n");
                }
                if (item.getDiscount() != discount) {
                    a = false;
                    error
                            .append("Input——" + item.getDiscount() + ",")
                            .append("Output——" + discount+",")
                            .append("Result——discount doesn't matched;\r\n");
                }
                if (item.isPromotion() != promotion) {
                    a = false;
                    error
                            .append("Input——" + item.isPromotion() + ",")
                            .append("Output——" + promotion+",")
                            .append("Result——judge doesn't matched;\r\n");

                }
                if (item.getVipDiscount() != vipDiscount) {
                    a = false;
                    error
                            .append("Input——" + item.getVipDiscount() + ",")
                            .append("Output——" + vipDiscount+",")
                            .append("Result——vipdiscount doesn't matched;\r\n");

                }
                log(error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    //判断输入的日期是否在打折期限内
    public boolean checkDiscount(Item item,Date date){
        boolean a=false;
        StringBuilder error=null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String inputTime=simpleDateFormat.format(date);
        Date start;
        Date end;
        String select_check_discount="select start,end from item where name='"+item.getName()+"'";
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(select_check_discount);
            while(rs.next()){
                start=rs.getDate("start");
                end=rs.getDate("end");
                if((compare_date(start,date)==-1||compare_date(start,date)==0)
                        &&(compare_date(end,date)==1||compare_date(start,date)==0)){
                    a=true;
                }else{
                    error
                            .append("Input——"+inputTime+",")
                            .append("Result——discount timeover;\r\n");
                    log(error);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    /*
    * 以下为member数据表的操作
    * */
    //根据会员编号返回现有积分
    public int checkPoints(String user){
        StringBuilder error=new StringBuilder();
        int points=0;
        String select_points="select points from member where user = '"+user+"'";
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(select_points);
            if(rs.next())
                points=rs.getInt("points");
            else{
                points=-1;
                error
                        .append("Input——"+user+",")
                        .append("Result—— user doesn't exist;\r\n");
            }
            log(error);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }

    //根据会员编号更改积分
    public void updatePoints(String user,int points){
        String update_points="update member set points = "+points+" where user ='"+user+"'";
        String s="UPDATE `test`.`member` SET `points`='103' WHERE `id`='00000000010';";
        try {
            stmt=con.createStatement();
            stmt.executeUpdate(update_points);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 }
