package Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    void execute();
    void undo();
    boolean checkUndo();


    //FIXME add in email format checks logic
    public default boolean checkEmail(String email){

        String local = "^([a-zA-Z0-9_]+([.-]?[a-zA-Z0-9_]+)+";
        String domain = "@[a-zA-Z0-9]+([.-]?[a-zA-Z0-9]+)+(\\.[a-z]{2,3})+";
        String latin = "|\\w+)$";
        String regex = local + domain +latin;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();

    }


}
