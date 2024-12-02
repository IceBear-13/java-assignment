public class RequestInformation {
    private Member mem;
    private EquipmentSet eqSet;
    private Day start, end;

    public RequestInformation(Member mem, EquipmentSet eqSet, Day start, Day end) {
        this.mem = mem;
        this.eqSet = eqSet;
        this.start = start;
        this.end = end;
    }

    public Member getMember() {
        return this.mem;
    }

    public EquipmentSet getEquipmentSet() {
        return this.eqSet;
    }

    public Day getStart() {
        return this.start;
    }

    public Day getEnd() {
        return this.end;
    }

    public String toString() {
        return this.mem.getName() + " " + this.eqSet.toString() + " " + this.start.toString() + " " + this.end.toString();
    }
    
    public boolean checkRequested(RequestInformation other){
        if (this.eqSet == other.eqSet && (this.end.compareTo(other.start) <= 0 || this.start.compareTo(other.end) >= 0)) {
            return true; 
        }
        return false; 
        
    }
}
