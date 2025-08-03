import java.util.ArrayList;
import java.util.Stack;

public class Invoker {

    Command[] cmdToExecute;

    Stack<Command> CommandStackHistory = new Stack<Command>();

    //! double check if input parameters for the constructor can be done this way.
    public Invoker(Command[] cmdToExecute) {
        setCommandsForExecution(cmdToExecute);
        executeCommand(CommandStackHistory);

    }

    //takes an array of Command, and push each command to the Stack CommandStackHistory
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;

        for (Command command : cmdToExecute ) {
            CommandStackHistory.push(command);
        }
    }

    //execute all the command in the stack
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : history) {
            cmd.execute();
        }
    }
}
