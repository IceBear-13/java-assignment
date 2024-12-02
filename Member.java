import java.util.ArrayList;

public class Member implements Comparable<Member> {
    private final String id;
    private final String name;
    private final Day joinDate;
    private ArrayList<EquipmentSet> borrowed;
    private ArrayList<EquipmentSet> requested;

    public Member(String id, String name){
        this.id = id;
        this.name = name;
        this.joinDate = SystemDate.getInstance().clone();
        this.borrowed = new ArrayList<EquipmentSet>();
        this.requested = new ArrayList<EquipmentSet>();
    }

    @Override
    public int compareTo(Member another) {
        return this.id.compareTo(another.id);
    }

    public static void list(ArrayList<Member> allMembers) {
        System.out.printf("%-5s%-9s%11s%11s%13s\n", "ID", "Name",
        "Join Date ", "#Borrowed ", "#Requested ");
        for (Member m : allMembers) {
            System.out.printf("%-5s%-9s%11s%7d%13d\n", m.id, m.name, m.joinDate, m.borrowed.size(), m.requested.size());
        }
    }

    public void borrowEquipment(EquipmentSet borrowed) {
        this.borrowed.add(borrowed);
        borrowed.setAvailability(false);
        BorrowInformation bi = new BorrowInformation(SystemDate.getInstance().clone(), SystemDate.getInstance().clone().advance(7), borrowed, this);
        borrowed.setBorrowInfo(bi);
    }

    public void returnEquipment(EquipmentSet returned) {
        borrowed.remove(returned);
        returned.setAvailability(true);
        returned.setBorrowInfo(null);
        try {
            Equipment e = Club.getInstance().findEquipmentByName(returned.getEqType().getName());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<EquipmentSet> getBorrowed() {
        return borrowed;
    }

    public String toString(){
        return id + " " + name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public ArrayList<EquipmentSet> getRequested(){
        return requested;
    }

    public void requestEquipment(EquipmentSet requested, Day start, Day end) {
        this.requested.add(requested);
        requested.addRequest(new RequestInformation(this, requested, start, end));
    }
}