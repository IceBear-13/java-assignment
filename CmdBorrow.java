public class CmdBorrow extends RecordedCommand {

    private Member m;
    private Equipment e;
    private EquipmentSet es;
    private Day cur;
    private Day returnDate;


    @Override
    public void execute(String[] cmdParts){
        if(cmdParts.length != 3){
            System.out.println("Insufficient command arguments.");
            return;
        }
        Club c = Club.getInstance();
        try {
            m = c.findMember(cmdParts[1]);
            e = c.findEquipment(cmdParts[2]);

            cur = SystemDate.getInstance().clone();
            returnDate = cur.clone().advance(7);

            boolean isBorrowed = false;

            for(EquipmentSet eq : m.getBorrowed()){
                if(eq.getEqType().equals(e)){
                    System.out.println("The member is currently borrowing a set of this equipment. He/she cannot borrow one more at the same time.");
                    return;
                }
            }
    
            for(EquipmentSet eSet : e.getEquipmentSets()){
                this.es = eSet;
                if(es.getAvailability()){
                    isBorrowed = true;
                    es.setAvailability(false);
                    m.borrowEquipment(es);
                    BorrowInformation bi = new BorrowInformation(cur, returnDate, es, m);
                    es.setBorrowInfo(bi);
                    e.borrowEquipmentSet(es);
                    System.out.println(m.getId() + " " + m.getName() + " borrows " + es.getSetCode() + " (" + e.getName() + ") from " + cur.toString() + " to " + returnDate.toString());
                    System.out.println("Done.");
                    break;
                }
            }

            if(!isBorrowed){
                System.out.println("There is no available set of this equipment for the command.");
                return;
            }

            addUndoCommand(this);
            clearRedoList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void undoMe(){
        Club c = Club.getInstance();
        m.returnEquipment(es);
        es.setAvailability(true);
        addRedoCommand(this);
    }

    @Override
    public void redoMe(){
        Club c = Club.getInstance();
        m.borrowEquipment(es);
        es.setAvailability(false);
        addUndoCommand(this);
    }


}
