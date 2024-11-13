import java.util.ArrayList;

public class CmdRequest extends RecordedCommand{
    private Day rStart, rEnd;
    @Override
    public void execute(String[] cmdParts){
        if(cmdParts.length < 5){
            System.out.println("Insufficient command arguments");
            return;
        }
        Club c = Club.getInstance();
        Member m = null;
        m = c.findMember(cmdParts[1]);
        Equipment e = c.findEquipment(cmdParts[2]);
        Day start = new Day(cmdParts[3]);
        rStart = start;
        start.advance(Integer.parseInt(cmdParts[4]));
        rEnd = start;
        ArrayList<EquipmentSet> eq = m.getEquipment();
        for(EquipmentSet es : eq){
            if(!es.getAvailability()){
                es.setBorrowInfo(new BorrowInformation(rStart, rEnd, es, m));
            }
        }
        addUndoCommand(this);
        clearRedoList();
    }

    @Override
    public void undoMe(){
        
    }

    @Override
    public void redoMe(){
        
    }
}
