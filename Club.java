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


    public void addMember(Member m) throws MemberIDAlreadyInUse {
        for (Member mem : allMembers) {
            if (mem.getId().equals(m.getId())) {
                throw new MemberIDAlreadyInUse(mem);
            }
        }
        allMembers.add(m);
        Collections.sort(allMembers);
    }

    public void listClubMembers() {
        Member.list(this.allMembers);
    }

    public void removeMember(Member m) {
        allMembers.remove(m);
    }

    public Member findMember(String memberId) throws MemberNotFound {
        for (Member m : allMembers) {
            if (m.getId().equals(memberId)) {
                return m;
            }
        }

        throw new MemberNotFound();
    }

    public void addEquipment(Equipment e) throws EquipmentCodeAlreadyInUse {
        for (Equipment eq : this.equipments) {
            if (eq.getEqCode().equals(e.getEqCode())) {
                throw new EquipmentCodeAlreadyInUse(eq);
            }
        }
        this.equipments.add(e);
    }

    public void removeEquipment(Equipment e) {
        this.equipments.remove(e);
    }
    
    public Equipment findEquipment(String eqCode) throws EquipmentNotFound {
        for (Equipment e : this.equipments) {
            if (e.getEqCode().equals(eqCode)) {
                return e;
            }
        }
        throw new EquipmentNotFound(eqCode);
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

    public void listEquipments() {
        System.out.printf("%-10s %-20s %-10s%n", "Code", "Name", "#sets");
        for (Equipment e : this.equipments) {
            System.out.printf("%-10s %-20s %-10d", e.getEqCode(), e.getName(), e.getAmount());
            boolean hasBorrowedSets = false;
            for (EquipmentSet es : e.getBorrowedEquipmentSets()) {
                if (es.getBorrowInfo() != null) {
                    hasBorrowedSets = true;
                    break;
                }
            }
            if (hasBorrowedSets) {
                System.out.printf(" (Borrowed set(s): ");
                boolean first = true;
                for (EquipmentSet es : e.getBorrowedEquipmentSets()) {
                    if (es.getBorrowInfo() != null) {
                        if (!first) {
                            System.out.printf(", ");
                        }
                        System.out.printf(es.getSetCode() + "(" + es.getBorrowInfo().getMember().getId() + ")");
                        first = false;
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
            if(e.getEquipmentSets().isEmpty() && e.getBorrowedEquipmentSets().isEmpty()){
                System.out.println("We do not have any sets for this equipment.");
            }
            for(EquipmentSet es : e.getEquipmentSets()){
                System.out.println("\t" + es.getSetCode());
                if(es.getBorrowInfo() != null){
                    System.out.println("\t" + "\t" + "Current status: " + es.getBorrowInfo().getMember().getId() + " " + es.getBorrowInfo().getMember().getName() + " borrows for " + es.getBorrowInfo().getBorrowDate().toString() + " to " + es.getBorrowInfo().getReturnDate().toString());
                } else {
                    System.out.println("\t" + "\t" + "Current status: Available");
                }
                if(!es.getRequests().isEmpty()){
                    System.out.print("\t" + "\t" + "Request period(s): ");
                    boolean first = true;
                    for(RequestInformation ri : es.getRequests()){
                        if (!first) {
                            System.out.print(", ");
                        }
                        System.out.print(ri.getStart().toString() + " to " + ri.getEnd().toString());
                        first = false;
                    }
                }
                System.out.println();
            }
        }
    }

    public Equipment findEquipmentByName(String name) throws EquipmentNotFound {
        for (Equipment e : this.equipments) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        throw new EquipmentNotFound("Equipment with name " + name + " not found.");
    }

}
