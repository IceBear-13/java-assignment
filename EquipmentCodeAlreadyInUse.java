public class EquipmentCodeAlreadyInUse extends Exception{
    public EquipmentCodeAlreadyInUse(Equipment e){
        super("Equipment code already in use: " + e.getEqCode() + " " + e.getName());
    }
    
}
