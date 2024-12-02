public class EquipmentNotFound extends Exception {
    public EquipmentNotFound(String e) {
        super("Missing record for Equipment " + e);
    }
}