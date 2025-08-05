package Invoker;

import Command.Command;
import Command.UndoCommand;
import Command.ListCommand;
import CustomException.CustomException;

import java.util.Stack;

public class Invoker {
    Command[] cmdToExecute;

    //stores the commands to be executed
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;

    }
    //execute all the command in the stored array
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            //Undo last undoable command if exist (update, add, delete)
            try{
                //throw a CustomException if cmd is null
                if (cmd == null) {
                    throw new CustomException("Command cannot be null");
                }

                //Check for UndoCommand
                if (cmd.getClass() == UndoCommand.class) {
                    //FIXME may be ok to remove the ListCommand check as ListCommands would not be pushed to history
                    // (row 46) ==> to check again
                    while (!history.isEmpty() && history.peek().getClass() == ListCommand.class) {
                        history.pop();
                    }

                    //undo the last command
                    if (!history.isEmpty()) {
                        Command lastCmd = history.pop();
                        lastCmd.undo();
                    }
                }
                else{
                    //execute commands (other than undo)
                    cmd.execute();
                    if (cmd.getClass() != ListCommand.class) {
                        history.push(cmd);
                    }
                }
            }
            catch (CustomException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
