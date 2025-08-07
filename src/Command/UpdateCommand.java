package Command;
import CustomException.CustomException;
import Receiver.Receiver;

public class UpdateCommand implements Command {

    private Receiver receiver;
    private String params;
    private String[] paramsStr;
    private String[] paramsToBeUpdated;
    private int index;
    private String[] updatedParams;

    //constructor
    public UpdateCommand(Receiver receiver, String params)  {
        this.receiver = receiver;
        this.params = params;

    }

    @Override
    public void execute() throws CustomException {
        paramsStr = params.split(" ");
        //catch if parseInt does not work
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
                paramsStr[3].substring(0,1).toUpperCase();
            }
        }
        else {
            for(int j=1;j<paramsStr.length;j++){
                paramsStr[j].substring(0,1).toUpperCase();
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

    @Override
    public void undo() throws CustomException {
        receiver.update(index, updatedParams);
    }

    @Override
    public boolean checkUndo(){
        return false;
    }
}
