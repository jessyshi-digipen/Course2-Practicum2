package Command;
import Receiver.Receiver;

public class AddCommand implements Command{
    Receiver receiver;
    String[] paramsStr;
    String firstName, lastName, email;

    //constructor
    public AddCommand(Receiver receiver, String params) {
        paramsStr = params.split(" ");
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        email = paramsStr[2];

        //check the email format, do not assign receiver if the email format is wrong.
        try {
            if (!checkEmail(email)){
                throw new IllegalArgumentException("Invalid email. Please enter valid email address");
            }
            this.receiver = receiver;
        }
        catch (IllegalArgumentException iae) {
            System.out.println("Invalid email");
        }
    }

    @Override
    public void execute() {
        receiver.add(paramsStr);
        printAction();
    }

    //print the action at the end
    @Override
    public void printAction() {
        System.out.println("Add");
    }
    
    @Override
    public void undo() {
        int deleteIndex = receiver.addUndo();
        receiver.delete(deleteIndex);
        System.out.println("Undo");

    }
}
