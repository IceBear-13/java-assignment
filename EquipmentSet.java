import java.util.ArrayList;

public class EquipmentSet implements Comparable<EquipmentSet> {
    private String setCode;
    private boolean isAvailable;
    private Equipment eqType;
    private BorrowInformation borrowInfo;
    ArrayList<RequestInformation> requests;

    public EquipmentSet(String setCode, Equipment eqType) {
        this.setCode = setCode;
        this.isAvailable = true;
        this.eqType = eqType;
        requests = new ArrayList<RequestInformation>();
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

    public Equipment getEqType(){
        return this.eqType;
    }

    public String getEqCode(){
        return this.setCode.substring(0, 1);
    }

    public void setAvailability(boolean availability){
        this.isAvailable = availability;
    }

    @Override
    public String toString(){
        return this.setCode + " " + "(" + this.eqType.getName() + ")";
    }

    @Override
    public int compareTo(EquipmentSet another) {
        return this.setCode.compareTo(another.setCode);
    }

    public void addRequest(RequestInformation request){
        requests.add(request);
    }

    public ArrayList<RequestInformation> getRequests(){
        return requests;
    }

}
