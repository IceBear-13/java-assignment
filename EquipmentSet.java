import java.util.ArrayList;

public class EquipmentSet {
    private String setCode;
    private boolean isAvailable;
    private ArrayList<Member> reservations;
    private String eqType;
    private BorrowInformation borrowInfo;

    public EquipmentSet(String setCode) {
        this.setCode = setCode;
        this.isAvailable = true;
        this.reservations = new ArrayList<>();
        Club c = Club.getInstance();
        this.eqType = c.getEqName(setCode.substring(0, 1));
    }

    public void setBorrowInfo(BorrowInformation borrowInfo){
        this.borrowInfo = borrowInfo;
    }

    public BorrowInformation getBorrowInfo(){
        return this.borrowInfo;
    }

    public boolean getAvailability(){
        return this.isAvailable;
    }

    public String getSetCode(){
        return this.setCode;
    }

    public void setAvailability(boolean availability){
        this.isAvailable = availability;
    }

    public ArrayList<Member> getReservations(){
        return this.reservations;
    }

    public void addReservation(Member m){
        this.reservations.add(m);
    }

    public void moveReservation(){
        Member m = this.reservations.get(0);
        m.borrowEquipment(this);
        if(this.reservations.size() > 0){
            this.reservations.remove(0);
        }

    }

    public String toString(){
        return this.setCode + " " + "(" + this.eqType + ")";
    }

}
