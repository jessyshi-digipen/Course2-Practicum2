public class DeleteCommand implements Command {
    Receiver receiver;
    String params;
    int index;
    static String[] deletedElement;

    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        //index to delete
        this.params = params;
        this.index = Integer.parseInt(params);

        //! check index is indeed numerical input: try catch


    }

    @Override
    public void execute() {
        deletedElement = receiver.delete(index);
        printAction();

    }


    @Override
    public void printAction() {
        System.out.println("Delete");
        //!print the details too for easier checking
    }
    @Override
    public void undo() {
        receiver.deleteUndo(index,deletedElement);
        System.out.println("Undo");

    }
}
