package Command;
import CustomException.CustomException;
import Receiver.Receiver;

import java.util.Stack;

/**
 * A class that undo the previous command
 * accepts 2 parameters:
 * 1. receiver : Receiver
 * 2. command history : Stack <Command>
 */
public class UndoCommand implements Command{
    Stack<Command> history;

    /**
     * constructor accepts 2 parameters
     * @param receiver the receiver
     * @param history Stack <Command> command history
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
            throw new CustomException("History is empty. Nothing to undo.");
        }
        Command lastCmd = history.pop();
        lastCmd.undo();
        System.out.println("Undo");
    }

    /**
     * cannot undo UndoCommand
     */
    @Override
    public void undo() {}

    /**
     * Checks if an UndoCommand was executed before this UndoCommand
     */
    @Override
    public boolean checkUndo(){
        return true;
    }

    /**
     * Checks if ListCommand was executed before this UndoCommand
     */
    @Override
    public boolean checkList(){
        return false;
    }
}
