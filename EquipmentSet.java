import java.util.ArrayList;

public class EquipmentSet {
    private String setCode;
    private boolean isAvailable;
    private Equipment eqType;
    private BorrowInformation borrowInfo;
    ArrayList<RequestInformation> requests;

    public EquipmentSet(String setCode, Equipment eqType) {
        this.setCode = setCode;
        this.isAvailable = true;
        this.eqType = eqType;
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
        return this.setCode + " " + "(" + this.eqType + ")";
    }

}
