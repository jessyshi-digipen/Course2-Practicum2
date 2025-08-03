public class AddCommand implements Command{
    Receiver receiver;
    String[] params;
    String firstName, lastName, email;

    //constructor
    public AddCommand(Receiver receiver, String[] params) {
        this.receiver = receiver;
        this.params = params;
        //params contain 3 values (payload 1) <data1> <data2> <data3>
        email = params[2];

        //!check if the email value follows the correct format with regex, only execute if email is of correct
        //! format, otherwise action fails and  print error message.
        if (checkEmail(email)){
            execute();
            printAction();
        }
        else {
            System.out.println("Invalid email. Please enter valid email address");
        }

    }


    @Override
    public void execute() {
        receiver.add(params);

    }

    //print the action at the end
    @Override
    public void printAction() {
        System.out.println("Add");
        //!print the details too for easier checking
    }
}
