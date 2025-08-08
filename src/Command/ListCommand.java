package Command;
import CustomException.CustomException;
import Receiver.Receiver;

/**
 * A class that prints the contents in the dataStore
 * accepts 1 parameter:
 * 1. receiver : Receiver
 */
public class ListCommand implements Command {

    private Receiver receiver;

    /**
     * constructor accepts 1 parameter1
     * @param receiver the receiver
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;


    }

    /**
     * Executes ListCommand
     */
    @Override
    public void execute() throws CustomException {
        if (receiver == null){
            throw new CustomException("Please enter receiver object! Do not leave this blank");
        }

        System.out.println("List");
        receiver.list();
    }

    /**
     * cannot undo ListCommand
     */
    @Override
    public void undo(){}

    /**
     * Checks if UndoCommand was executed before this ListCommand
     */
    @Override
    public boolean checkUndo(){
        return false;
    }
    @Override
    public boolean checkList(){
        return true;
    }

}
