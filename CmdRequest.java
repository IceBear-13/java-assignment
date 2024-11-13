
import java.util.ArrayList;

public class CmdRequest extends RecordedCommand{
    
    @Override
    public void execute(String[] cmdParts){
        Club c = Club.getInstance();
        Member m = null;
        m = c.findMember(cmdParts[1]);
        Equipment e = c.findEquipment(cmdParts[2]);
        Day start = new Day(cmdParts[3]);
        Day end = new Day(cmdParts[4]);
        ArrayList<EquipmentSet> eq = m.getEquipment();

        
    }

    @Override
    public void undoMe(){
        
    }

    @Override
    public void redoMe(){
        
    }
}
