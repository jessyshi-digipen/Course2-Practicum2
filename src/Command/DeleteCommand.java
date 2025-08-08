/**
 * This package contains concrete classes for handling various operations.
 * Includes classes to add, delete, list, undo, update contents
 */
package Command;
import CustomException.CustomException;
import Receiver.Receiver;

/**
 * A class that deletes contents to dataStore based on a given index.
 * Accepts 2 parameters:
 * 1. receiver : Receiver
 * 2. params: index of the content to delete
 */
public class DeleteCommand implements Command {
    private Receiver receiver;
    private String params;
    private int index;
    private static String[] deletedElement;

    /**
     * constructor accepts 2 parameters
     * @param receiver the receiver
     * @param params String <data1>, an integer
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
        if (params == null){
            throw new CustomException("Please enter the index of data to be deleted! Do not leave this blank");
        }

        if (receiver == null){
            throw new CustomException("Please enter input receiver object! Do not leave this blank");
        }

        try {
            index = Integer.parseInt(params);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new CustomException("Please enter correct number of parameters for DeleteCommand");
        }

        if (index < 1){
            throw new CustomException("Please enter positive index of data to be deleted!");
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
     * Checks if UndoCommand was executed before this DeleteCommand
     */
    @Override
    public boolean checkUndo(){
        return false;
    }

    /**
     * Checks if ListCommand was executed before this DeleteCommand
     */
    @Override
    public boolean checkList(){
        return false;
    }
}
