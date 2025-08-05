public class UndoCommand implements Command{
    Receiver receiver;
    public UndoCommand(Receiver receiver) {
        this.receiver=receiver;
    }

    @Override
    public void execute() {
        printAction();
    }

    @Override
    public void printAction() {}

    @Override
    public void undo() {}
}
