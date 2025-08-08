import Command.Command;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.UndoCommand;
import Command.UpdateCommand;
import Command.ListCommand;
import Invoker.Invoker;
import Receiver.Receiver;

import java.util.Stack;


public class main {
    public static void main(String[] args) {

        Stack<Command> CommandStackHistory = new Stack<Command>();
        Receiver receiver = new Receiver();

        //Initialize command objects and command array cmdlist
        AddCommand cmd1 = new AddCommand(receiver,"First_name Last_name Email");
        AddCommand cmd2 = new AddCommand(receiver, "John Doe simple@example.com");
        AddCommand cmd3 = new AddCommand(receiver, "Hanna Moon tetter.tots@potatoesarelife.com");
        AddCommand cmd4 = new AddCommand(receiver, "Ah Boon green-tea@teaforlife.com");
        ListCommand cmd5 = new ListCommand(receiver);
        UpdateCommand cmd6 = new UpdateCommand(receiver,"3 Adam");
        UpdateCommand cmd7 = new UpdateCommand(receiver, "1 blue bell ice-cream@alaskaFields.org");
        DeleteCommand cmd8 = new DeleteCommand(receiver,"1");
        UndoCommand cmd9 = new UndoCommand(receiver, CommandStackHistory);

        Command[] cmdlist = {cmd1,cmd2,cmd3,cmd4,cmd5, cmd6, cmd5, cmd7, cmd5, cmd8, cmd5, cmd9, cmd5};

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(cmdlist);
        invoker.executeCommand(CommandStackHistory);

    }

}
