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
                if (!cmd.checkList()){
                    history.push(cmd);
                }
            }
            catch (CustomException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
