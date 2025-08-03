import java.util.ArrayList;
import java.util.Arrays;

public class Receiver {

    static ArrayList<String[]> dataStore = new ArrayList<String[]>();
    String index;


    public void add(String[] params){
        //params contain 3 values (payload 1) <data1> <data2> <data3>

        dataStore.add(params);
    }

    public void delete(String[] params){

    }

    public void update(){

    }

    public void list (){
        for (String[] strings : dataStore) {
            index = "0"+Integer.toString(dataStore.indexOf(strings)+1)+". ";
            System.out.print(index);
            for (String params : strings)
                System.out.print(params+" ");
            System.out.println();
        }

    }

    public void undo (){

    }



}
