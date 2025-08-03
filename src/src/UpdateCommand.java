public class UpdateCommand implements Command {

    Receiver receiver;
    String[] params;
    int index;
    String firstName, lastName, email;
    int length;

    //constructor
    public UpdateCommand(Receiver receiver,int index, String[] params) {
        this.receiver = receiver;
        this.params = params;
        length = params.length;
        // params may contain up to 3 values (payload 2) <index> <data1> <data2> <data3>
        // requirement states that input can only have data3 if data1 and data2 also exist, so check via the string
        // length

        switch(length){
            case 3:
                this.email = params[2];
                //!check if the email value follows the correct format with regex, only execute if email is of correct
                //! format, otherwise action fails and  print error message.
                if (checkEmail(email)){
                    execute();
                    printAction();
                }
                else {
                    System.out.println("Invalid email. Please enter valid email address");
                }
                break;
            case 2:
                //only updating name
                break;
            case 1:
                //only updating first name
                break;
            default:
                System.out.println("Not enough input parameter");
                break;
        }



    }


    @Override
    public void execute() {}

    @Override
    public void printAction() {
        System.out.print("Updated record "+index);
    }
}
