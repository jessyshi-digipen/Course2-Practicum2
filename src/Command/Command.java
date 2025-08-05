package Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    void execute();
    void printAction();
    void undo();

    //use this: inputchecker includes the email checker. Returns true for good to go else false 
    //checks for 3 args, if true, further check the <data3> by using the emailchecker method 
    default boolean inputChecker(String[] params){
    if  (params.length !=3 ){
        System.out.println("Wrong number of arguments");
        return false;
    }
    String data3 = params[2];
    //check for a single email address or Latin letters, digits 0 to 9 and underscores
    //email address takes in all numbers, - and . for now and will be subjected to further checks
    Pattern pattern = Pattern.compile("^((\\w|-|\\.)*@(\\w|-|\\.)*\\.(\\w|-|\\.)*)$|^(\\w*)$");
    Matcher matcher = pattern.matcher(data3);

    if (matcher.find()){
        if (data3.contains("@")){
            //conduct valid email check by splitting into local and domain
            return checkEmail(data3);
        }
        else {
//                System.out.println("This is just a string: " + inputStr);
        }
        return true;
    }
    else {
        System.out.println("Error in input: "+data3);
        System.out.println("Please input a valid email address or Latin letters, digits 0 to 9 and underscores");
        return false;
    }
}
    
    //email checker contains business logic for data3
    default boolean checkEmail(String email){
        //check for @., a@. a@b., @.b edge cases
        Pattern patternZero = Pattern.compile("(^@)|(@\\.)|(\\.$)");
        Matcher matcherZero = patternZero.matcher(email);
        if (matcherZero.find()){
            //conduct valid email check by splitting into local and domain
            System.out.println("Invalid email address local, domain and .com cannot be empty");
            return false;
        }
        //conduct valid email check by splitting into local and domain
        String[] checkParts = email.split("@");
        String localPart =  checkParts[0];
        String domainPart =  checkParts[1];
    
        //conduct email local part check
        Pattern patternLocal = Pattern.compile("[-.]{2}|^[-.]|[-.]$");
        Matcher matcherLocal = patternLocal.matcher(localPart);
        if (matcherLocal.find()){
            System.out.println("Error in email local part: " + localPart);
            System.out.println("email cannot start, end or have consecutive \"-\" or \".\"");
            return false;
        }
    
        //conduct email domain part check
        //further split at "." and check characters after each "." e.g. .com.edu.sg
        String[] domainParts = domainPart.split("\\.");
        //check domain 1st part before the "."
        Pattern patternBeforeDot = Pattern.compile("_|[-.]{2}|^[-.]|[-.]$");
        Matcher matcherBeforeDot = patternBeforeDot.matcher(domainParts[0]);
        if (matcherBeforeDot.find()){
            System.out.println("Error in email domain part before \".\": " + domainParts[0]);
            System.out.println("domain cannot have \"_\" and cannot start, end or have consecutive \"-\" or \".\"");
            return false;
        }
        //check domain 2nd part after each "."
        for (int i = 1; i < domainParts.length; i++){
            Pattern patternPartAfterEachDot = Pattern.compile("^[a-z]{2,3}$");
            Matcher matcherPartAfterDotEachDot = patternPartAfterEachDot.matcher(domainParts[i]);
    
            if (!matcherPartAfterDotEachDot.find()){
                System.out.println("Error in email domain after \".\": " + domainParts[i]);
                System.out.println(".xxx only allows for lowercase z-z and have 2-3 characters");
                return false;
            }
        }
    //        System.out.println("This is a valid email: " + email);
        return true;
    }
}
