package Command;
import CustomException.CustomException;
import Receiver.Receiver;

/**
 * A class that allows user to update the data store content based on a given index
 * accepts 2 parameters:
 * 1. receiver : Receiver
 * 2. params: string <index of the content to update in datastore> <data1> <data2> <data3>
 * for params, index and data1 are compulsory fields
 */
public class UpdateCommand implements Command {

    private Receiver receiver;
    private String params;
    private String[] paramsStr;
    private String[] paramsToBeUpdated;
    private int index;
    private String[] updatedParams;

    /**
     * constructor accepts 2 parameter:
     * @param receiver the receiver
     * @param params: string <index of the content to update in datastore> <data1> <data2> <data3>, index and data1 are compulsory fields
     */
    public UpdateCommand(Receiver receiver, String params){
        this.receiver = receiver;
        this.params = params;


    }

    /**
     * Executes UpdateCommand
     */
    @Override
    public void execute() throws CustomException {
        paramsStr = params.split(" ");
        //catch if parseInt does not work

        if (params == null){
            throw new CustomException("Please enter parameters to be updated! Do not leave this blank");
        }

        if (receiver == null){
            throw new CustomException("Please enter input receiver object! Do not leave this blank");
        }

        for (int i = 0; i < this.paramsStr.length; i++) {
            if (this.paramsStr[i].replace(" ", "").equals("")) {
                throw new CustomException("Data" + (i + 1) + " field cannot be blank");
            }
        }

        try {
            index = Integer.parseInt(paramsStr[0]);
        } catch (NumberFormatException e) {
            throw new CustomException("Please enter correct number of parameters for UpdateCommand");
        }
        //catch if less than 2 or more than 4 input params
        if (paramsStr.length < 2 | paramsStr.length > 4){
            throw new CustomException("Please enter correct number of parameters for UpdateCommand");
        }

        // params may contain up to 3 values (payload 2) <index> <data1> <data2> <data3>
        // requirement states that input can only have data3 if data1 and data2 also exist, so check via the string
        // length

        //capitalize first 2 elements for first name and lastname
        //capitalize 3rd element if not a email
        if (paramsStr.length == 4){
            if (!paramsStr[3].contains("@")){
                paramsStr[3] = paramsStr[3].substring(0,1).toUpperCase() +  paramsStr[3].substring(1).toLowerCase();
            }
        }
        else {
            for(int j=1;j<paramsStr.length;j++){
                paramsStr[j] =  paramsStr[j].substring(0,1).toUpperCase() +  paramsStr[j].substring(1).toLowerCase();
            }
        }

        //check email and assign parameters to be updated based on number of inputs
        switch(paramsStr.length){
            case 4:
                if (checkEmail(paramsStr[3])){
                    paramsToBeUpdated = new String[]{paramsStr[1], paramsStr[2], paramsStr[3]};
                }
                else {
                    throw new CustomException("Invalid email. Please enter valid email address");
                }
                break;
            case 3:
                paramsToBeUpdated = new String[]{paramsStr[1], paramsStr[2]};
                break;
            case 2:
                paramsToBeUpdated = new String[]{paramsStr[1]};
                break;
            default:
                throw new CustomException("Please enter data to be updated");
        }

        updatedParams = receiver.update(index,paramsToBeUpdated);
        System.out.println("Update");
    }

    /**
     * Executes undo UpdateCommand
     */
    @Override
    public void undo() throws CustomException {
        receiver.update(index, updatedParams);
    }

    /**
     * Checks if UndoCommand was executed before this UpdateCommand
     */
    @Override
    public boolean checkUndo(){
        return false;
    }

    /**
     * Checks if ListCommand was executed before this UpdateCommand
     */
    @Override
    public boolean checkList(){
        return false;
    }
}
