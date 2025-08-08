/**
 * This package contains concrete classes for handling various operations.
 * Includes classes to add, delete, list, undo, update contents
 */
package Command;
import CustomException.CustomException;
import Receiver.Receiver;

/**
 * A class that adds contents to dataStore.
 * Accepts 2 parameters:
 * 1. receiver : Receiver
 * 2. params: String <data1> <data2> <data3>, all 3 content fields are compulsory
 * data 3 only accepts an email address or latin characters, number and underscores
 */
public class AddCommand implements Command{
    private Receiver receiver;
    private String params;
    private String[] paramsStr;
    private String email;

    /**
     * constructor accepts 2 parameters
     * @param receiver the receiver
     * @param params String <data1> <data2> <data3>, all 3 content fields are compulsory
     */
    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params =  params;
    }

    /**
     * Executes AddCommand
     */
    @Override
    public void execute() throws CustomException {

        if (params == null){
            throw new CustomException("Please enter the data to be added! Do not leave this blank");
        }

        if (receiver == null){
            throw new CustomException("Please enter input receiver object! Do not leave this blank");
        }
        //split the params string and ensure correct number of inputs, otherwise, throw exception
        this.paramsStr = this.params.split(" ");
        for (int i = 0; i < this.paramsStr.length; i++) {
            if (this.paramsStr[i].replace(" ", "").equals("")) {
                throw new CustomException("Data" + (i + 1) + " field cannot be blank");
            }
        }
        if(paramsStr.length != 3) {
            throw new CustomException("Please enter correct number of parameters for AddCommand");
        }
        //check the email format, do not assign receiver if the email format is wrong.
        if (!checkEmail(paramsStr[2])){
            throw new CustomException("Invalid email. Please enter valid email address");
        }
        //capitalize first 2 elements for first name and lastname, and 3rd element if not a email 
        for(int j=0;j<2;j++){
            paramsStr[j]= paramsStr[j].substring(0,1).toUpperCase() + paramsStr[j].substring(1).toLowerCase();
        }
        if (!paramsStr[2].contains("@")){
            paramsStr[2] = paramsStr[2].substring(0,1).toUpperCase() +  paramsStr[2].substring(1).toLowerCase();
        }
        receiver.add(-1,paramsStr);
        System.out.println("Add");
    }

    /**
     * Executes undo AddCommand
     */
    @Override
    public void undo() throws CustomException{
        //use index-1 to indicate this is an undo addCommand
        receiver.delete(-1);
    }

    /**
     * Checks if UndoCommand was executed before this AddCommand
     */
    @Override
    public boolean checkUndo(){
        return false;
    }

    /**
     * Checks if ListCommand was executed before this AddCommand
     */
    @Override
    public boolean checkList(){
        return false;
    }
}
