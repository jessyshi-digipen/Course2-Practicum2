package Command;
import CustomException.CustomException;
import Receiver.Receiver;

public class DeleteCommand implements Command {
    private Receiver receiver;
    private String params;
    private int index;
    private static String[] deletedElement;

    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        //index to delete
        this.params = params;
        //parse to integer, otherwise throw custom exception
        try{
            index = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            CustomException ce = new CustomException("Please enter a valid index for deleteCommand");
            System.out.println(ce.getMessage());
        }

    }

    @Override
    public void execute() {
        deletedElement = receiver.delete(index);
        System.out.println("Delete");
    }


    @Override
    public void undo() {
        //use add to undo delete action
        receiver.add(index,deletedElement);
    }

    @Override
    public boolean checkUndo(){
        return false;
    }



}
