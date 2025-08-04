public class UndoCommand implements Command{

    public void UndoCommand(Receiver receiver) {
        execute();
        printAction();
    }

    @Override
    public void execute() {}

    @Override
    public void printAction() {

    }
}
