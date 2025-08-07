package Invoker;

import Command.Command;
import CustomException.CustomException;
import java.util.Stack;

/**
 * Invoker class is responsible for initiating the commands.
 * It contains a field to store all the commands to be executed and trigger the commands to be executed.
 */
public class Invoker {
    /**
     * variable to store the all the commands to be executed
     */
    private Command[] cmdToExecute;


    /**
     * stores the arrays of Commands into a local variable
     * @param cmdToExecute arrays of Commands
     */
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;

    }

    /**
     * executes all the commands in the stored array
     * @param history stack of Command history
     */
    //execute all the command in the stored array
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            //Undo last undoable command if exist (update, add, delete)
            try{
                cmd.execute();
                if (!cmd.checkList() & !cmd.checkUndo()) {
                    history.push(cmd);
                }
            }
            catch (CustomException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
