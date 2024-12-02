
public class CmdRequest extends RecordedCommand{
    private Member m;
    private Day start;
    private Day end;
    private Equipment e;
    private EquipmentSet es;
    private Day rStart, rEnd;
    @Override
    public void execute(String[] cmdParts) {
        if (cmdParts.length < 5) {
            System.out.println("Insufficient command arguments.");
            return;
        }
        Club c = Club.getInstance();
        try {
            m = c.findMember(cmdParts[1]);
            e = c.findEquipment(cmdParts[2]);
            start = new Day(cmdParts[3]);
            end = new Day(cmdParts[3]).advance(Integer.parseInt(cmdParts[4]));
            RequestInformation r = new RequestInformation(m, es, start, end);
    
            boolean isRequested = false; // Initialize the flag outside the loop
    
            for (EquipmentSet es : e.getEquipmentSets()) {
                if(isRequested){
                    break;
                }
                if(es.getRequests().isEmpty()){
                    m.requestEquipment(es, start, end);
                    isRequested = true;
                    break;
                } else{
                    for (RequestInformation ri : es.getRequests()){
                        if(!r.checkRequested(ri)){
                            m.requestEquipment(es, start, end);
                            isRequested = true;
                            System.out.println(m.getName() + " requests " + es.toString() + " for " + start.toString() + " to " + end.toString());
                            break;
                        }
                    }
                }
            }
            if(!isRequested){
                System.out.println("Request failed");
                return;
            }
    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
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
