import java.util.ArrayList;
import java.util.Iterator;

public class CmdStartNewDay extends RecordedCommand {
    
    private String newDate, oldDate;

    @Override
    public void execute(String[] cmdParts){
        SystemDate cur = SystemDate.getInstance();
        Club c = Club.getInstance();
        ArrayList<Member> members = c.getAllMembers();
        if(cmdParts.length != 2){
            System.out.println("Insufficient command arguments.");
            return;
        }
        newDate = cmdParts[1];
        if(!Day.isValidDateString(newDate)){
            System.out.println("Invalid date.");
            return;
        }
        oldDate = cur.toString();
        cur.set(cmdParts[1]);
        for(Member m : members){
            ArrayList<EquipmentSet> eq = m.getBorrowed();
            Iterator<EquipmentSet> iterator = eq.iterator();
            while (iterator.hasNext()) {
                EquipmentSet e = iterator.next();
                if(e.getBorrowInfo() != null){
                    if(e.getBorrowInfo().getReturnDate().compareTo(SystemDate.getInstance()) < 0){
                        iterator.remove(); // Remove the element using the iterator
                        m.returnEquipment(e);
                    }
                }
            }
        }
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe(){
        SystemDate cur = SystemDate.getInstance();
        cur.set(oldDate);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        SystemDate cur = SystemDate.getInstance();
        cur.set(newDate);
        addUndoCommand(this);
    }
}
