public class CmdArrive extends RecordedCommand {

    private Equipment e;

    @Override
    public void execute(String[] args){
        if(args.length != 2){
            System.out.println("Insufficient command arguments.");
            return;
        }
        String eqCode = args[1];
        Club c = Club.getInstance();
        try {
            e = c.findEquipment(args[1]);
            e.addEquipmentSet();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe(){
        Club c = Club.getInstance();
        try {
            e.removeEquipmentSet();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Club c = Club.getInstance();
        try {
            e.addEquipmentSet();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        addUndoCommand(this);
    }
}
