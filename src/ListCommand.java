public class ListCommand implements Command {

    Receiver receiver;
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        printAction();
        receiver.list();
    }

    @Override
    public void printAction() {
        System.out.println("List");

    }

    @Override
    public void undo(){}


}
