package Command;
import CustomException.CustomException;
import Receiver.Receiver;

import java.util.Stack;

public class UndoCommand implements Command{

    Stack<Command> history;
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.history = history;
    }

    @Override
    public void execute() throws CustomException {
        if (!history.isEmpty()) {
            Command lastCmd = history.pop();
            lastCmd.undo();
        }
        System.out.println("Undo");
    }

    @Override
    public void undo()  {}

    @Override
    public boolean checkUndo(){
        return true;
    }

    @Override
    public boolean checkList(){
        return false;
    }
}
