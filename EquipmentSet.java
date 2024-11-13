import java.util.ArrayList;

public class EquipmentSet {
    private String setCode;
    private boolean isAvailable;
    private String eqType;
    private BorrowInformation borrowInfo;
    ArrayList<RequestInformation> requests;

    public EquipmentSet(String setCode) {
        this.setCode = setCode;
        this.isAvailable = true;
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

    public String toString(){
        return this.setCode + " " + "(" + this.eqType + ")";
    }

}
