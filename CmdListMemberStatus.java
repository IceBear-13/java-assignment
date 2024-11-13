import java.util.ArrayList;

public class CmdListMemberStatus implements Command{
    @Override
    public void execute(String[] cmdParts){
        Club c = Club.getInstance();
        ArrayList<Member> mems = c.getAllMembers();
        for(Member m : mems){
            System.out.println("[" + m.toString() + "]");
            ArrayList<EquipmentSet> eq = m.getEquipment();
            for(EquipmentSet e : eq){
                System.out.println("- borrows " + e.toString() + " for " + e.getBorrowInfo().getBorrowDate().toString() + " to " + e.getBorrowInfo().getReturnDate().toString());
            }
        }
    }
}
