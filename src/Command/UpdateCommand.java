package Command;
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
    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
        paramsStr = params.split(" ");
        index = Integer.parseInt(paramsStr[0]);
        length = paramsStr.length;
        // params may contain up to 3 values (payload 2) <index> <data1> <data2> <data3>
        // requirement states that input can only have data3 if data1 and data2 also exist, so check via the string
        // length

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
                System.out.println("Not enough input parameter");
                break;
        }



    }


    @Override
    public void execute() {
        switch(length){
            case 4:
                receiver.update(index, paramsToBeUpdated);
                break;
            case 3:
                receiver.updateFullName(index, paramsToBeUpdated);
                break;
            case 2:
                receiver.updateName(index,paramsToBeUpdated[0]);
                break;
            default:
                System.out.println("Not enough input parameter");
        }
        printAction();
    }



    @Override
    public void printAction() {
        System.out.println("Update");
    }

    @Override
    public void undo() {
        System.out.println("Undo Update");
        receiver.update(index, updatedParams);
    }
}
