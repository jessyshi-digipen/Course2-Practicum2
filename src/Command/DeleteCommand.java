package Command;
import CustomException.CustomException;
import Receiver.Receiver;

public class DeleteCommand implements Command {
    Receiver receiver;
    String params;
    int index;
    static String[] deletedElement;

    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        //index to delete
        this.params = params;
        //parse to integer, otherwise throw custom exception
        try{
            this.index = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            CustomException ce = new CustomException("Please enter a valid index for deleteCommand");
            System.out.println(ce.getMessage());
        }

    }

    @Override
    public void execute() {
        deletedElement = receiver.delete(index);
        printAction();

    }


    @Override
    public void printAction() {
        System.out.println("Delete");
    }
    @Override
    public void undo() {
        //use add to undo delete action
        receiver.add(index,deletedElement);
        System.out.println("Undo");

    }



}
