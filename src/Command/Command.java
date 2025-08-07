package Command;

import CustomException.CustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An interface that has 3 methods to inherit:
 * 1. execute()
 * 2. undo()
 * 3. checkUndo()
 */
public interface Command {
    /**
     * execute DeleteCommand
     */
    void execute() throws CustomException;
    /**
     * undo DeleteCommand
     */
    void undo() throws CustomException;
    /**
     * use this to check if lastest command is an undoCommand
     */
    boolean checkUndo();
    /**
     * use this to check if lastest command is a listCommand
     */
    boolean checkList();


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
