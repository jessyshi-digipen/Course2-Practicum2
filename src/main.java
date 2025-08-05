import Command.Command;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.UndoCommand;
import Command.UpdateCommand;
import Command.ListCommand;
import Invoker.Invoker;
import Receiver.Receiver;

import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class main {
    public static void main(String[] args) {


        //initialize CommandStackHistory
        Stack<Command> CommandStackHistory = new Stack<Command>();

        //Initialize receiver object
        Receiver stafflist1 = new Receiver();

        //Initialize command objects and command array cmdlist
        AddCommand cmd1 = new AddCommand(stafflist1,"First_name Last_name Email");
        AddCommand cmd2 = new AddCommand(stafflist1, "John Doe simple@example.com");
        AddCommand cmd3 = new AddCommand(stafflist1, "Hanna Moon tetter.tots@potatoesarelife.com");
        AddCommand cmd4 = new AddCommand(stafflist1, "Ah Boon green-tea@teaforlife.com");
        ListCommand cmd5 = new ListCommand(stafflist1);
        UpdateCommand cmd6 = new UpdateCommand(stafflist1,"3 Adam");
        UpdateCommand cmd7 = new UpdateCommand(stafflist1, "1 blue bell ice-cream@alaskaFields.org");
        DeleteCommand cmd8 = new DeleteCommand(stafflist1,"1");
        UndoCommand cmd9 = new UndoCommand(stafflist1);

//        Command.Command[] cmdlist = {cmd1,cmd2,cmd3,cmd4,cmd5,cmd6, cmd5, cmd7, cmd5, cmd8, cmd5, cmd9, cmd5};
//        Command.Command[] cmdlist = {cmd1,cmd2,cmd3,cmd4, cmd5, cmd6, cmd5, cmd7, cmd5, cmd8, cmd5, cmd5, cmd5, cmd9, cmd5};
        Command[] cmdlist = {cmd1,cmd2, cmd5, cmd7, cmd5, cmd9,cmd5};

        //Initialize invoker object
        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(cmdlist);

        invoker.executeCommand(CommandStackHistory);



    }
}
