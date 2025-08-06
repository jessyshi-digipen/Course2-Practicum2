package Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    void execute();
    void undo();
    boolean checkUndo();


    public default boolean checkEmail(String email){

        //FIXME current regex does not accept a@aa.com or aa@a.com as email and accpets aa@a.a.com as email
        // updated solution: ^([a-zA-Z0-9_]+(([.-]?[a-zA-Z0-9_])?)+?@[a-zA-Z0-9]+(([.-]?[a-zA-Z0-9])?)+?(\.[a-z]{2,3})+|\w+)$
        // truncate: ^([\w]+(([.-]?[\w])?)+?@[a-zA-Z\d]+(([-]?[a-zA-Z\d])?)+?(\.[a-z]{2,3})+|\w+)$

        String local = "^([a-zA-Z0-9_]+([.-]?[a-zA-Z0-9_]+)+";
        String domain = "@[a-zA-Z0-9]+([.-]?[a-zA-Z0-9]+)+(\\.[a-z]{2,3})+";
        String latin = "|\\w+)$";
        String regex = local + domain +latin;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();

    }
}
