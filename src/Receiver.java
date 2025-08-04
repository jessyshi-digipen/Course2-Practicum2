import java.util.ArrayList;
import java.util.Arrays;

public class Receiver {

    static ArrayList<String[]> dataStore = new ArrayList<String[]>();
    String index;


    //FIXME addCommand logic
    public void add(String[] params){
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        dataStore.add(params);
    }

    //FIXME deleteCommand logic
    public void delete(int index){
        dataStore.remove(index-1);
    }

    //FIXME updateCommand logic
    public void update(){

    }

    //FIXME listCommand logic
    public void list (){
        for (String[] strings : dataStore) {
            index = "0"+Integer.toString(dataStore.indexOf(strings)+1)+". ";
            System.out.print(index);
            for (String params : strings)
                System.out.print(params+" ");
            System.out.println();
        }

    }

    //FIXME undoCommand logic need to rethink how to do
    public static void undo(){
        String[] commandSplit = commandStackHistory.peek().split("\\s");
        switch (commandSplit[0]){
            case "add":
                arrayList.remove(arrayList.size()-1);
                break;
            case "delete":
                arrayList.add((Integer.parseInt(commandSplit[1])-1), commandSplit[2] + " " + commandSplit[3] + " " + commandSplit[4]);
                break;
            case "update":
                arrayList.remove(Integer.parseInt(commandSplit[1])-1);
                arrayList.add((Integer.parseInt(commandSplit[1])-1), commandSplit[2] + " " + commandSplit[3] + " " + commandSplit[4]);
                break;
        }
        commandStackHistory.pop();
        save();
    }



}
