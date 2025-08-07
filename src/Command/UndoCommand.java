package Command;
import CustomException.CustomException;
import Receiver.Receiver;

import java.util.Stack;

/**
 * A class that deletes params to dataStore
 * accepts 2 parameters:
 * 1. receiver : Receiver
 * 2. history : Stack <Command>
 */
public class UndoCommand implements Command{
    Stack<Command> history;

    /**
     * constructor accepts accepts 2 parameters
     * @param receiver the receiver
     * @param history command stack
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.history = history;
    }

    /**
     * Executes UndoCommand
     */
    @Override
    public void execute() throws CustomException {
        if (history.isEmpty()){
            throw new CustomException("History is empty");
        }
        Command lastCmd = history.pop();
        lastCmd.undo();
        System.out.println("Undo");
    }

    /**
     * Executes UndoCommand
     */
    @Override
    public void undo() {}

    @Override
    public boolean checkUndo(){
        return true;
    }

    @Override
    public boolean checkList(){
        return false;
    }
}
