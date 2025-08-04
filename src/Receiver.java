import java.util.ArrayList;
import java.util.Arrays;

public class Receiver {

    static ArrayList<String[]> dataStore = new ArrayList<String[]>();
    String index;

    //FIXME addCommand logic
    public void add(String[] params){
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        dataStore.add(params);
    }

    //FIXME deleteCommand logic
    public void delete(int index){
        dataStore.remove(index-1);
    }

    //FIXME updateCommand logic
    public void update(){

    }

    //FIXME listCommand logic
    public void list (){
        for (String[] strings : dataStore) {
            index = "0"+Integer.toString(dataStore.indexOf(strings)+1)+". ";
            System.out.print(index);
            for (String params : strings)
                System.out.print(params+" ");
            System.out.println();
        }

    }

    //FIXME undoCommand logic need to rethink how to do
    public static void undo(){
        String[] commandSplit = commandStackHistory.peek().split("\\s");
        switch (commandSplit[0]){
            case "add":
                arrayList.remove(arrayList.size()-1);
                break;
            case "delete":
                arrayList.add((Integer.parseInt(commandSplit[1])-1), commandSplit[2] + " " + commandSplit[3] + " " + commandSplit[4]);
                break;
            case "update":
                arrayList.remove(Integer.parseInt(commandSplit[1])-1);
                arrayList.add((Integer.parseInt(commandSplit[1])-1), commandSplit[2] + " " + commandSplit[3] + " " + commandSplit[4]);
                break;
        }
        commandStackHistory.pop();
        save();
    }


    //file handling: Check if file exist else create and read the file to store to dataStore
    public static boolean checkIfFileExistElseCreate(){
        if (Files.notExists(dataStoreFilePath)) {
//            System.out.println("File does not exist, new file being created...");
            File newFile = new File(dataStoreFilePath.toString());

            try {
                newFile.createNewFile();
//             System.out.println("file successfully created? " + newFile.exists() );
            } catch (IOException e){
                e.getMessage();
                return false;
            }
            return true;
        }
        else {
            return true;
        }
    }
    public static void readFileStoreToArrList(){
        if(checkIfFileExistElseCreate()){
            try (BufferedReader br = Files.newBufferedReader(dataStoreFilePath)) {
                while (br.ready()) {
                    String[] linetoArr =  br.readLine().split("");
                    dataStore.add(linetoArr);
                }
            }catch (IOException e){
                System.out.print(e.getMessage());
            }
        }
    };

    //method to check if add input != 3 else further check data3 using email checker
    public static boolean addInputChecker(String[] args){
        if  (args.length !=3 ){
            System.out.println("Wrong number of arguments");
            return false;
        }
        String inputStr = args[2];
        //check for a single email address or Latin letters, digits 0 to 9 and underscores
        //email address takes in all numbers, - and . for now and will be subjected to further checks
        Pattern pattern = Pattern.compile("^((\\w|-|\\.)*@(\\w|-|\\.)*\\.(\\w|-|\\.)*)$|^(\\w*)$");
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.find()){
            if (inputStr.contains("@")){
                //conduct valid email check by splitting into local and domain
                return emailChecker(inputStr);
            }
            else {
//                System.out.println("This is just a string: " + inputStr);
            }
            return true;
        }
        else {
            System.out.println("Error in input: "+inputStr);
            System.out.println("Please input a valid email address or Latin letters, digits 0 to 9 and underscores");
            return false;
        }
    }

    
    public static boolean emailChecker(String email){
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
