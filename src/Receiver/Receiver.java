package Receiver;

import java.util.ArrayList;

public class Receiver {

    static ArrayList<String[]> dataStore = new ArrayList<String[]>();
    String index;
    String[] Stringlist;


    //FIXME addCommand and undodelete combined
    public void add(int index, String[] params){
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        if (index==-1){
            dataStore.add(params);
        }
        else{
            dataStore.add(index-1,params);
        }
    }


    //FIXME deleteCommand logic
    public String[] delete(int index){
        if (index==-1){
            //undo add action
            return dataStore.removeLast();
        }
        else{
            //delete at index-1(as index input starts from 1)
            return dataStore.remove(index-1);
        }
    }


    //FIXME updateCommand logic combined
    public String[] update(int index, String[] params){
        String[] updatedParams = dataStore.get(index-1);
        String[] toBeUpdated = new String[0];
        switch (params.length){
            case 3:
                toBeUpdated=params;
                break;
            case 2:
                String temp = dataStore.get(index-1)[2];
                toBeUpdated=new String[] {params[0],params[1], temp};
                break;
            case 1:
                String[] tempArr = dataStore.get(index-1);
                toBeUpdated = new String[] {params[0], tempArr[1],tempArr[2]};
                break;
        }
        dataStore.set(index-1, toBeUpdated);
        return updatedParams;
    }

    //FIXME listCommand logic
    public void list (){
        for (int i = 0; i<dataStore.size(); i++) {
            index = String.format("%02d. ", i+1);
            System.out.print(index);

            Stringlist = dataStore.get(i);

            for (String params : Stringlist )
                System.out.print(params+" ");
            System.out.println();
        }

    }






}
