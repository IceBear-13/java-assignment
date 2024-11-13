import java.util.ArrayList;

public class Equipment {
    private String name;
    private String eqCode;
    private ArrayList<EquipmentSet> equipmentSets;
    private ArrayList<EquipmentSet> borrowedEquipmentSets;

    public Equipment(String name, String eqCode) {
        this.name = name;
        this.eqCode = eqCode;
        this.equipmentSets = new ArrayList<EquipmentSet>();
        this.borrowedEquipmentSets = new ArrayList<EquipmentSet>();
    }

    public ArrayList<EquipmentSet> getEquipmentSets() {
        return this.equipmentSets;
    }

    public String getEqCode() {
        return this.eqCode;
    }

    public void addEquipmentSet() {
        this.equipmentSets.add(new EquipmentSet(eqCode+"_"+(equipmentSets.size()+1)));
    }

    public void removeEquipmentSet() {
        this.equipmentSets.remove(equipmentSets.size()-1);
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.equipmentSets.size();
    }

    public void borrowEquipmentSet(EquipmentSet es) {
        this.borrowedEquipmentSets.add(es);
    }

    public ArrayList<EquipmentSet> getBorrowedEquipmentSets() {
        return this.borrowedEquipmentSets;
    }
}
