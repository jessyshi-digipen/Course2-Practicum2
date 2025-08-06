package Command;
import CustomException.CustomException;
import Receiver.Receiver;

public class UpdateCommand implements Command {

    Receiver receiver;
    String params;
    String[] paramsStr;
    String[] paramsToBeUpdated;
    int index;
    String firstName, lastName, email;
    int length;
    String[] updatedParams;

    //constructor
    public UpdateCommand(Receiver receiver, String params)  {
        this.receiver = receiver;
        this.params = params;

        //check if inputs meet requirement, otherwise throw exception.
        try{
            paramsStr = params.split(" ");
            index = Integer.parseInt(paramsStr[0]);
            length = paramsStr.length;

            if (length > 4){
                throw new CustomException("Please enter correct number of parameters for UpdateCommand");
            }
        }
        //catch if parseint does not work
        catch (NumberFormatException e) {
            CustomException ce = new CustomException("Please enter a valid index for UpdateCommand");
            System.out.println(ce.getMessage());
            return;
        }
        //catch if number of parameters >4
        catch(CustomException ce){
            System.out.println(ce.getMessage());
            return;
        }

        // params may contain up to 3 values (payload 2) <index> <data1> <data2> <data3>
        // requirement states that input can only have data3 if data1 and data2 also exist, so check via the string
        // length

        //check email and assign parameters to be updated based on number of inputs
        switch(length){
            case 4:
                this.email = paramsStr[3];
                if (checkEmail(email)){
                    paramsToBeUpdated = new String[]{paramsStr[1], paramsStr[2], paramsStr[3]};
                }
                else {
                    System.out.println("Invalid email. Please enter valid email address");
                }
                break;
            case 3:
                paramsToBeUpdated = new String[]{paramsStr[1], paramsStr[2]};
                break;
            case 2:
                paramsToBeUpdated = new String[]{paramsStr[1]};
                break;
            default:
                System.out.println("Please enter data to be updated");
                break;
        }



    }


    @Override
    public void execute() {
        //capitalize first 2 elements for first name and lastname

        updatedParams = receiver.update(index,paramsToBeUpdated);
        System.out.println("Update");
    }



    @Override
    public void undo() {
        receiver.update(index, updatedParams);
    }

    @Override
    public boolean checkUndo(){
        return false;
    }
}
