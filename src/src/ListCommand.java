public class ListCommand implements Command {

    Receiver receiver;
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
        printAction();
        execute();
    }

    @Override
    public void execute() {
        receiver.list();

    }

    @Override
    public void printAction() {
        System.out.println("List");

    }
}
