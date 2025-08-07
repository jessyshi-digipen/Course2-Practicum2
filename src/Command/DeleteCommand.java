package Command;
import CustomException.CustomException;
import Receiver.Receiver;

/**
 * A class that deletes params to dataStore
 * accepts 2 parameters:
 * 1. receiver : Receiver
 * 2. params: String <data1> <data2> <data3>
 */
public class DeleteCommand implements Command {
    private Receiver receiver;
    private String params;
    private int index;
    private static String[] deletedElement;

    /**
     * constructor accepts accepts 2 parameters
     * @param receiver the receiver
     * @param params String - <data1> <data2> <data3>
     */
    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        //index to delete
        this.params = params;
        //parse to integer, otherwise throw custom exception
    }

    /**
     * Executes DeleteCommand
     */
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

    /**
     * Executes undo DeleteCommand
     */
    @Override
    public void undo() {
        //use add to undo delete action
        receiver.add(index,deletedElement);
    }

    /**
     * Checks if undoCommand was executed before DeleteCommand
     */
    @Override
    public boolean checkUndo(){
        return false;
    }

    /**
     * Checks if listCommand was executed before DeleteCommand
     */
    @Override
    public boolean checkList(){
        return false;
    }
}
