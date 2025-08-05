import java.util.ArrayList;
import java.util.Arrays;

public class Receiver {

    static ArrayList<String[]> dataStore = new ArrayList<String[]>();
    String index;
    String[] Stringlist;


    //FIXME addCommand logic
    public void add(String[] params){
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        dataStore.add(params);
    }


    //FIXME deleteCommand logic
    public String[] delete(int index){
        return dataStore.remove(index-1);
    }

    //FIXME updateCommand logic
    public void update(int index, String[] params){
        dataStore.set(index-1, params);

    }

    public void updateFullName(int index, String[] params){
        String temp = dataStore.get(index-1)[2];
        String[] toBeUpdated = new String[] {params[0],params[1], temp};
        dataStore.set(index-1, toBeUpdated);
    }

    public void updateName(int index, String params){
        String[] temp = dataStore.get(index-1);
        String[] toBeUpdated = new String[] {params, temp[1],temp[2]};
        dataStore.set(index-1, toBeUpdated);
    }

    //FIXME listCommand logic
    public void list (){
        for (int i = 0; i<dataStore.size(); i++) {
            index = String.format("0%d. ", i+1);
            System.out.print(index);
            Stringlist = dataStore.get(i);
            for (String params : Stringlist )
                System.out.print(params+" ");
            System.out.println();
        }

    }

    public int addUndo(){
        return dataStore.size();
    }

    public void deleteUndo(int index, String[] params){
        dataStore.add(index-1,params);
    }


}
