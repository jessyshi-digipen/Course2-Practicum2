package Invoker;

import Command.Command;
import Command.UndoCommand;
import Command.ListCommand;
import CustomException.CustomException;

import java.util.Stack;

public class Invoker {

    Command[] cmdToExecute;



    //takes an array of Command.Command, and push each command to the Stack CommandStackHistory
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;

    }

    //execute all the command in the stack
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            //Undo last undoable command if exist (update, add, delete)
            
            try{
                
            }
            if (cmd.getClass() == UndoCommand.class) {
                while (!history.isEmpty() && history.peek().getClass() == ListCommand.class) {
                    history.pop();
                }
                if (!history.isEmpty()) {
                    Command lastCmd = history.pop();
                    lastCmd.undo();
                }
            }
            else{
                cmd.execute();
//
                if (cmd.getClass() != ListCommand.class) {
                    history.push(cmd);
                }
            }



        }
    }
}
