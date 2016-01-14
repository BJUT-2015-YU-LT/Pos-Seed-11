package domains;

import java.util.ArrayList;

/**
 * Created by Sylvia on 2014/12/30.
 */
public class ShoppingChart {
    private ArrayList<Item> items = new ArrayList<Item>();
//    private Connect con=new Connect();
    private String userName="NULL";

    public ShoppingChart(){
//        con.connect();
    }
    //单个添加
    public int add(Item item) {

        if(false){          //判断数据是否正确
            return 1;       //返回值为1表示数据不匹配
        }

        boolean isExist=false;
        for(int i=0;i<items.size();i++){
            if(item.getName()==items.get(i).getName()) {
                items.get(i).addQuantity();
                isExist=true;
            }
        }
        if(isExist==false){
            this.items.add(item);
        }

        return 0;       //返回值为0时表示正确添加
    }

    //多个添加
    public int add(Item item,int count) {

        if(false){          //判断数据是否正确
            return 1;       //返回值为1表示数据不匹配
        }
        boolean isExist=false;
        for(int i=0;i<items.size();i++){
            if(item.getName()==items.get(i).getName()) {
                for(int j=0;j<count;j++){
                    items.get(i).addQuantity();
                }
                isExist=true;
                break;
            }
        }
        if(isExist==false){
            this.items.add(item);
            for(int j=1;j<count;j++){
                item.addQuantity();
            }
        }

        return 0;       //返回值为0时表示正确添加
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    //单件删除，返回true表示删除成功
    public boolean remove(Item item){
        for(int i=0;i<items.size();i++){
            if(item.getName()==items.get(i).getName()) {
                    items.get(i).subQuantity();
                    if(items.get(i).getQuantity()==1) {
                        items.remove(i);
                    }
                    else {
                }
                return true;
            }
        }
        return false;
    }

    //单件删除，返回true表示删除成功
    public boolean remove(Item item,int count){
        for(int i=0;i<items.size();i++){
            if(item.getName()==items.get(i).getName()) {
                if(items.get(i).getQuantity()==count) {
                    items.remove(i);
                }
                else if (items.get(i).getQuantity()>count){
                    for(int j=0;j<count;j++){
                        items.get(i).subQuantity();
                    }
                }
                else
                {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    //清空购物车
    public void clean(){
        items.clear();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
