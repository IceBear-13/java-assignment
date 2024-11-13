public class CmdCreate extends RecordedCommand {
    private Equipment newEquipment;

    @Override
    public void execute(String[] args){
        Club c = Club.getInstance();
        String eqCode = args[1];
        String eqName = args[2];
        newEquipment = new Equipment(eqName, eqCode);
        c.addEquipment(newEquipment);
        System.out.println("Done.");
        addUndoCommand(this);
        clearRedoList();
    }

    @Override
    public void undoMe(){
        Club c = Club.getInstance();
        c.removeEquipment(newEquipment);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Club c = Club.getInstance();
        c.addEquipment(newEquipment);
        addUndoCommand(this);
    }
}
