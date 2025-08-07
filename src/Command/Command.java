package Command;

import CustomException.CustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An interface that has 4 methods to inherit:
 * 1. execute()
 * 2. undo()
 * 3. checkUndo()
 * 4. checkList()
 *
 * Interface includes 1 other call methods:
 * 1. checkEmail()
 */
public interface Command {
    /**
     * execute a command
     */
    void execute() throws CustomException;
    /**
     * undo a command
     */
    void undo() throws CustomException;
    /**
     * use this to check if lastest command is an UndoCommand
     */
    boolean checkUndo();
    /**
     * use this to check if lastest command is a ListCommand
     */
    boolean checkList();

    /**
     * method to check for a valid email
     */
    public default boolean checkEmail(String email){

//        String local = "^([a-zA-Z0-9_]+([.-]?[a-zA-Z0-9_]+)+";
//        String domain = "@[a-zA-Z0-9]+([.-]?[a-zA-Z0-9]+)+(\\.[a-z]{2,3})+";
//        String latin = "|\\w+)$";
//        String regex = local + domain +latin;

        String regex = "^([\\w]+(([.-]?[\\w])?)+?@[a-zA-Z\\d]+(([.-]?[a-zA-Z\\d])?)+?(\\.[a-z]{2,3})+|\\w+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
}
