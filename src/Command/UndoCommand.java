package Command;
import Receiver.Receiver;

public class UndoCommand implements Command{

    public UndoCommand(Receiver receiver) {    }

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
