import java.util.ArrayList;

public class CmdStartNewDay extends RecordedCommand {
    
    private String newDate, oldDate;

    @Override
    public void execute(String[] cmdParts){
        SystemDate cur = SystemDate.getInstance();
        Club c = Club.getInstance();
        ArrayList<Member> members = c.getAllMembers();
        newDate = cmdParts[1];
        oldDate = cur.toString();
        for(Member m : members){
            ArrayList<EquipmentSet> eq = m.getEquipment();
            for(EquipmentSet e : eq){
                if(e.getBorrowInfo() != null){
                    if(e.getBorrowInfo().getReturnDate().compareTo(new Day(newDate)) < 0){
                        m.returnEquipment(e);
                        e.setAvailability(true);
                        e.setBorrowInfo(null);
                    }
                }
            }
        }
        cur.set(cmdParts[1]);
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
