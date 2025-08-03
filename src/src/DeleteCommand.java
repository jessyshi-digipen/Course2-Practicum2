public class DeleteCommand implements Command {
    Receiver receiver;
    int index;

    public DeleteCommand(Receiver receiver, int index) {
        this.receiver = receiver;
        //index to delete
        this.index = index;

        //! check index is indeed numerical input: try catch
        execute();
        printAction();

    }
    //!call delete method in Receiver class
    @Override
    public void execute() {

    }


    @Override
    public void printAction() {
        System.out.println("Deleted " + index);
        //!print the details too for easier checking
    }
}
