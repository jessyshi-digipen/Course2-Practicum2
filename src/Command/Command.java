package Command;

import CustomException.CustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    void execute() throws CustomException;
    void undo() throws CustomException;
    boolean checkUndo();
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
