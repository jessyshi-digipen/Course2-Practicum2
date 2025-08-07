package Receiver;

import CustomException.CustomException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Receiver {

    private static ArrayList<String[]> dataStore = new ArrayList<String[]>();
    private String index;
    private String[] Stringlist;
    private static final Path dataStoreFilePath = Paths.get("./src/dataStore.txt");

    public void add(int index, String[] params){
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        if (index==-1){
            dataStore.add(params);
        }
        else{
            dataStore.add(index-1,params);
        }
    }

    public String[] delete(int index) throws CustomException{
        if (dataStore.isEmpty()){
            throw new CustomException("There is nothing to delete!");
        }
        else if (index==-1){
            //undo add action
            return dataStore.removeLast();
        }
        else{
            //delete at index-1(as index input starts from 1)
            return dataStore.remove(index-1);
        }
    }

    public String[] update(int index, String[] params) throws CustomException {
        if (index<0 | index>dataStore.size()){
            throw new CustomException("index to update not found");
        }
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
        for(int j=0;j<2;j++){
            toBeUpdated[j]= toBeUpdated[j].substring(0,1).toUpperCase() + toBeUpdated[j].substring(1).toLowerCase();
        }
        dataStore.set(index-1, toBeUpdated);
        return updatedParams;
    }
    
    public void list () throws CustomException {
        if (dataStore.isEmpty()){
            throw new CustomException("DataStore is Empty!");
        }
        for (int i = 0; i<dataStore.size(); i++) {
            index = String.format("%02d. ", i+1);
            System.out.print(index);

            Stringlist = dataStore.get(i);

            for (String params : Stringlist )
                System.out.print(params+" ");
            System.out.println();
        }
    }

    public static boolean checkIfFileExistElseCreate(){
        if (Files.notExists(dataStoreFilePath)) {
//            System.out.println("File does not exist, new file being created...");
            File newFile = new File(dataStoreFilePath.toString());

            try {
                newFile.createNewFile();
//             System.out.println("file successfully created? " + newFile.exists() );
            } catch (IOException e){
                e.getMessage();
                return false;
            }
            return true;
        }
        else {
            return true;
        }
    }

    public static void readFileToDataStore(){
        if(checkIfFileExistElseCreate()){
            try {
                List<String> lines = Files.readAllLines(dataStoreFilePath);
                for  (String line: lines) {
                    dataStore.add(line.split(" "));
                }
            }catch (IOException e){
                System.out.print(e.getMessage());
            }
        }
    }

    public static void writeUpdatedDataStoreToFile(){
        String tempString = "";
        for (int i = 0; i < dataStore.size(); i++) {
            String line = dataStore.get(i)[0] + " " + dataStore.get(i)[1] + " "  + dataStore.get(i)[2] + "\n";
            tempString += line;
        }
        try {
            Files.writeString(dataStoreFilePath, tempString);
        } catch (IOException e){
            e.getMessage();
        }
    }
}
