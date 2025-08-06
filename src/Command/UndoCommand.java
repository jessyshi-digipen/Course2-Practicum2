package Command;
import Receiver.Receiver;

public class UndoCommand implements Command{
    Receiver receiver;
    public UndoCommand(Receiver receiver) {
        this.receiver=receiver;
    }

    @Override
    public void execute() {
        System.out.println("Undo");
    }


    @Override
    public void undo() {}

    @Override
    public boolean checkUndo(){
        return true;
    }
}
