import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class main {
    public static void main(String[] args) {



        Stack<Command> CommandStackHistory = new Stack<Command>();

//        Stack<Command> commandStack = new Stack<Command>();
        Receiver stafflist1 = new Receiver();
        String params1 = "First_name Last_name Email";
        String params2 = "John Doe simple@example.com";
        String params3 = "Hanna Moon tots@potatoes.com";

        AddCommand cmd1 = new AddCommand(stafflist1, params1);
        AddCommand cmd2 = new AddCommand(stafflist1, params2);
        AddCommand cmd3 = new AddCommand(stafflist1, params3);
        ListCommand cmd4 = new ListCommand(stafflist1);
        DeleteCommand cmd5 = new DeleteCommand(stafflist1,"1");

//        commandStack.push(cmd1);
//        commandStack.push(cmd2);
//        commandStack.push(cmd3);
        Command[] cmdlist = {cmd1,cmd2,cmd3,cmd5,cmd4};
        Command[] cmdlist2 = {cmd1,cmd2,cmd3,cmd4};
//        Invoker invoker = new Invoker(cmdlist);
        Invoker invoker2 = new Invoker(cmdlist);


        //email regex check
//        String str = ".,.zjessyshg..i109.97@gmail.com";
//        String local = "^[a-zA-Z0-9]+([a-zA-Z0-9_.-]?[a-zA-Z0-9]+)+";
//        String domain = "@[a-zA-Z0-9]+([a-zA-Z0-9_.-]?[a-zA-Z0-9]+)+\\.[a-z]{2,3}$";
//        String regex = local + domain;
//        //([A-Za-z0-9._-]*[A-Za-z0-9]+)*@[A-Za-z0-9]+([A-Za-z0-9.-]*[A-Za-z0-9]+)*\\. " +"[a-z]{2,4}";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(str);
//
//        if(matcher.find()){
//             System.out.println(matcher.group()+ "true");
//        }
//        else{
//            System.out.println(matcher.find());
//        }

    }
}