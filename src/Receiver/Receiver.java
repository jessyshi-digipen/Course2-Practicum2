package Receiver;

import CustomException.CustomException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Receiver class contains the logic of the concrete commands.
 */
public class Receiver {

    /**
     * static variable to store the data: first name, last name, email
     */
    private static ArrayList<String[]> dataStore = new ArrayList<String[]>();
    /**
     * variable for index of the data to be updated by commands
     */
    private String index;
    /**
     * variable for a row of data from dataStore
     */
    private String[] Stringlist;
    /**
     * variable for path to read from and write to (dataStore.txt)
     */
    private static final Path dataStoreFilePath = Paths.get("./src/dataStore.txt");

    /**
     * Contains the logic for AddCommand and the undo action for an AddCommand.
     * This method takes the index and String array of data to be added to dataStore.
     * @param index index of data to be added. This takes value of -1 for undo of a DeleteCommand.
     * @param params data to be added in.
     */
    public void add(int index, String[] params){
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        if (index==-1){
            dataStore.add(params);
        }
        else{
            dataStore.add(index-1,params);
        }
    }

    /**
     * Contains the logic for DeleteCommand and the undo action for a DeleteCommand.
     * This method takes an integer index of data to be deleted from the dataStore, and returns the deleted array of
     * string.
     * @param index index of data to be deleted. This takes value of -1 for undo of an AddCommand.
     * @return the deleted array of strings
     * @throws CustomException when dataStore is empty/ there is nothing to delete.
     */
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

    /**
     * Contains the logic for UpdateCommand and the undo action for Updatecommand
     * This method takes an integer index of data and the Array of data to be updated, and returns a String Array of
     * the previous version of the updated data
     * @param index index of data to be updated
     * @param params data to be updated
     * @return previous version of the updated data
     * @throws CustomException for invalid index
     */
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
    
    /**
     * Contains the logic for ListCommand
     * This method does not take any input and prints the dataStore
     * @throws CustomException if there is nothing to print (i.e. dataStore is empty)
     */
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

    /**
     * Checks if the dataStore.txt file exists, and create it if it does not exist/
     * @return boolean true if the file exist or if the file is created successfully
     */
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

    /**
     * reads data from file and store it in dataStore variable.
     */
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

    /**
     * writes dataStore to the file
     */
    public static void writeUpdatedDataStoreToFile(){
        String tempString = "";
        try {
            for (int i = 0; i < dataStore.size(); i++) {
                if (dataStore.get(i).length != 3) {
                    throw new CustomException("File corrupted, check your datastore file");
                }
                String line = dataStore.get(i)[0] + " " + dataStore.get(i)[1] + " " + dataStore.get(i)[2] + "\n";
                tempString += line;
            }
        } catch (CustomException e) {
            System.out.print(e.getMessage());
        }
        finally {
            try {
                Files.writeString(dataStoreFilePath, tempString);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
