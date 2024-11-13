import java.util.ArrayList;
import java.util.Collections;


public class Club {
    private ArrayList<Member> allMembers;
    private static Club instance = new Club();
    private ArrayList<Equipment> equipments;
    private Club() { 
        allMembers = new ArrayList<Member>();
        equipments = new ArrayList<Equipment>();
    }
    public static Club getInstance() { return instance; }


    public void addMember(Member m) {// See how it is called in Member constructor (Step 3)
        allMembers.add(m);
        Collections.sort(allMembers);
    }

    public void listClubMembers() {
        Member.list(this.allMembers);
    }

    public void removeMember(Member m) {
        allMembers.remove(m);
    }

    public Member findMember(String memberId) {
        for (Member m : allMembers) {
            if (m.getId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    public void addEquipment(Equipment e) {
        this.equipments.add(e);
    }

    public void removeEquipment(Equipment e) {
        this.equipments.remove(e);
    }
    
    public Equipment findEquipment(String eqCode){
        for (Equipment e : this.equipments) {
            if (e.getEqCode().equals(eqCode)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Member> getAllMembers() {
        return allMembers;
    }

    public String getEqName(String eqCode) {
        for (Equipment e : this.equipments) {
            if (e.getEqCode().equals(eqCode)) {
                return e.getName();
            }
        }
            return null;
    }

    public void listEquipments(){
        System.out.printf("%-10s %-20s %-10s%n", "Code", "Name", "#sets");
        for (Equipment e : this.equipments) {
            System.out.printf("%-10s %-20s %-10d", e.getEqCode(), e.getName(), e.getAmount());
            if(e.getBorrowedEquipmentSets().size() > 0){
                System.out.printf(" (Borrowed set(s): ");
                for(int i = 0; i < e.getBorrowedEquipmentSets().size(); i++){
                    EquipmentSet es = e.getBorrowedEquipmentSets().get(i);
                    System.out.printf(es.getSetCode() + "("+es.getBorrowInfo().getMember().getId()+")");
                    if(i < e.getBorrowedEquipmentSets().size() - 1){
                    System.out.printf(", ");
                    }
                }
            System.out.println(")");
            } else {
            System.out.println();
            }
        }
    }

    public void listEquipmentStatus(){
        for(Equipment e : this.equipments){
            System.out.println("[" + e.getEqCode() + " " + e.getName() + "]");
            for(EquipmentSet es : e.getEquipmentSets()){
                System.out.println("\t" + es.getSetCode());
                if(es.getBorrowInfo() != null){
                    System.out.println("\t" + "\t" + "Current status: " + es.getBorrowInfo().getMember().getId() + " " + es.getBorrowInfo().getMember().getName() + " borrows for " + es.getBorrowInfo().getBorrowDate().toString() + " to " + es.getBorrowInfo().getReturnDate().toString());
                } else {
                    System.out.println("\t" + "\t" + "Current status: Available");
                }
            }
        }
    }

}
