package Command;
import CustomException.CustomException;
import Receiver.Receiver;

public class AddCommand implements Command{
    Receiver receiver;
    String[] paramsStr;
    String firstName, lastName, email;

    //constructor
    public AddCommand(Receiver receiver, String params) {
        //split the params string and ensure correct number of inputs, otherwise, throw exception
        try {
            paramsStr = params.split(" ");
            if(paramsStr.length != 2) {
                throw new CustomException("Please enter correct number of parameters for AddCommand");
            }
        }
        catch(CustomException ce) {
            System.out.println(ce.getMessage());
        }

        //params contain 3 values (payload 1) <data1> <data2> <data3>
        email = paramsStr[2];

        //check the email format, do not assign receiver if the email format is wrong.
        try {
            if (!checkEmail(email)){
                throw new CustomException("Invalid email. Please enter valid email address");
            }
            this.receiver = receiver;
        }
        catch (CustomException ce) {
            System.out.println(ce.getMessage());
        }
    }

    @Override
    public void execute() {

        //capitalize first 2 elements for first name and lastname
        for(int j=0;j<2;j++){
            paramsStr[j]= paramsStr[j].substring(0,1).toUpperCase() + paramsStr[j].substring(1).toLowerCase();
        }

        receiver.add(-1,paramsStr);
        printAction();
    }

    //print the action at the end
    @Override
    public void printAction() {
        System.out.println("Add");
    }

    @Override
    public void undo() {
        //use index-1 to indicate this is an undo addCommand
        receiver.delete(-1);
        System.out.println("Undo");

    }
}
