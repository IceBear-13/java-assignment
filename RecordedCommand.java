import java.util.ArrayList;

public abstract class RecordedCommand implements Command {
    public abstract void undoMe();
    public abstract void redoMe();
    public abstract void execute(String[] args);

    private static final ArrayList<RecordedCommand> undoList = new ArrayList<>();
    private static final ArrayList<RecordedCommand> redoList = new ArrayList<>();  /* adding command to the list */
    protected static void addUndoCommand(RecordedCommand cmd) {undoList.add(cmd);}  
    protected static void addRedoCommand(RecordedCommand cmd) {redoList.add(cmd);}  /* clear redo list*/
    protected static void clearRedoList() {redoList.clear();}
    /* carry out undo/redo */
    public static void undoOneCommand() {
        if (undoList.isEmpty()){
            System.out.println("Nothing to undo.");
            return;
        }
        undoList.remove(undoList.size()-1).undoMe();
    }  
    public static void redoOneCommand() {
        if (redoList.isEmpty()){
            System.out.println("Nothing to redo.");
            return;
        }
        redoList.remove(redoList.size()-1).redoMe();
    }
}
