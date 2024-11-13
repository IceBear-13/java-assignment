public class CmdArrive extends RecordedCommand {

    private String eqCode;

    @Override
    public void execute(String[] args){
        if(args.length != 2){
            System.out.println("Insufficient command arguments");
            return;
        }
        this.eqCode = args[1];
        Club c = Club.getInstance();
        Equipment e = c.findEquipment(args[1]);
        e.addEquipmentSet();
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe(){
        Club c = Club.getInstance();
        Equipment e = c.findEquipment(eqCode);
        e.removeEquipmentSet();
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Club c = Club.getInstance();
        Equipment e = c.findEquipment(eqCode);
        e.addEquipmentSet();
        addUndoCommand(this);
    }
}
