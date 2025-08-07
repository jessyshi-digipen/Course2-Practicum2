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
    }

    @Override
    public void execute() throws CustomException {
        try {
            index = Integer.parseInt(params);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CustomException("Please enter correct number of parameters for DeleteCommand");
        }
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

    @Override
    public boolean checkList(){
        return false;
    }

}
