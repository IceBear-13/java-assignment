import java.util.ArrayList;

public class CmdRequest extends RecordedCommand{
    private Member m;

    private Day rStart, rEnd;
    @Override
    public void execute(String[] cmdParts){
        if(cmdParts.length < 5){
            System.out.println("Insufficient command arguments.");
            return;
        }
        Club c = Club.getInstance();
        try{
            m = c.findMember(cmdParts[1]);
            Equipment e = c.findEquipment(cmdParts[2]);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        Day start = new Day(cmdParts[3]);
        rStart = start;
        start.advance(Integer.parseInt(cmdParts[4]));
        rEnd = start;
        ArrayList<EquipmentSet> eq = m.getBorrowed();
        for(EquipmentSet es : eq){
            es.setBorrowInfo(new BorrowInformation(rStart, rEnd, es, m));
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
