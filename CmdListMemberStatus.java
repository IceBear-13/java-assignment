import java.util.ArrayList;
import java.util.Collections;

public class CmdListMemberStatus implements Command{
    @Override
    public void execute(String[] cmdParts){
        Club c = Club.getInstance();
        ArrayList<Member> mems = c.getAllMembers();
        for(Member m : mems){
            System.out.println("[" + m.toString() + "]");
            ArrayList<EquipmentSet> eq = m.getBorrowed();
            if(eq.isEmpty()){
                System.out.println("No record.");
            } else {
                Collections.sort(eq, (e1, e2) -> e1.getEqType().getEqCode().compareTo(e2.getEqType().getEqCode()));
                for(EquipmentSet e : eq){
                    if(e.getBorrowInfo() != null){
                        System.out.println("- borrows " + e.toString() + " for " + e.getBorrowInfo().getBorrowDate().toString() + " to " + e.getBorrowInfo().getReturnDate().toString());
                    }
                }
            }
        }
    }
}
