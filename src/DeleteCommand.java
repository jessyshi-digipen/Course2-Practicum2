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
    }

    @Override
    public void execute() {
        deletedElement = receiver.delete(index);
        printAction();

    }


    @Override
    public void printAction() {
        System.out.println("Delete");
    }
    @Override
    public void undo() {
        receiver.deleteUndo(index,deletedElement);
        System.out.println("Undo");

    }
}
