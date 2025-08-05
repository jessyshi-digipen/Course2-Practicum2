package Command;
import Receiver.Receiver;

public class AddCommand implements Command{
    Receiver receiver;
    String[] paramsStr;

    //constructor
    public AddCommand(Receiver receiver, String params) {
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        while (inputChecker(params)){
            this.paramsStr = params.split(" ");
            this.receiver = receiver;
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
