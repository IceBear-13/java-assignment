public class CmdListEquipment implements Command {
    @Override
    public void execute(String[] args) {
        Club c = Club.getInstance();
        c.listEquipments();
    }
    
}
