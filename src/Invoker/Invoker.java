package Invoker;

import Command.Command;
import Command.UndoCommand;
import Command.ListCommand;
import CustomException.CustomException;

import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;

    //stores the commands to be executed
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;

    }
    //execute all the command in the stored array
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            //Undo last undoable command if exist (update, add, delete)
            try{
                cmd.execute();
                //Check for UndoCommand
                if (cmd.checkUndo()) {

                    //undo the last command
                    if (!history.isEmpty()) {
                        Command lastCmd = history.pop();
                        lastCmd.undo();
                    }
                }
                else{
                    //execute commands (other than undo)
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
