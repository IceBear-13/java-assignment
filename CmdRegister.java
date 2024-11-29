public class CmdRegister extends RecordedCommand {

    private Member m;

    @Override
    public void execute(String[] cmdParts){
        if(cmdParts.length != 3){
            System.out.println("Insufficient command arguments..");
            return;
        }
        m = new Member(cmdParts[1], cmdParts[2]);
        Club c = Club.getInstance();
        try {
            c.addMember(m);
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
        c.removeMember(m);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Club c = Club.getInstance();
        try {
            c.addMember(m);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        addUndoCommand(this);
    }
}
