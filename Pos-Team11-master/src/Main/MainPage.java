package Main;

import domains.Pos;
import domains.ReadList;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sylvia on 2015/1/7.
 */
public class MainPage {
    private static File fIndex=new File("index.txt");
    private static File fList=new File("list.txt");

    public static void main(String[] args){
        ReadList read=new ReadList(fIndex,fList);
        Pos pos=new Pos();
        try{

            String s=pos.getShoppingList(read.parser());

            System.out.print(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
