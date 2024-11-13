public class CmdBorrow extends RecordedCommand {

    private Member m;
    private EquipmentSet es;

    @Override
    public void execute(String[] cmdParts){
        Club c = Club.getInstance();
        Member m = c.findMember(cmdParts[1]);
        
        Equipment e = c.findEquipment(cmdParts[2]);
        SystemDate cur = SystemDate.getInstance();
        SystemDate cur2 = SystemDate.getInstance();
        for(EquipmentSet es : e.getEquipmentSets()){
            if(es.getAvailability()){
                this.m = m;
                this.es = es;
                es.setAvailability(false);
                m.borrowEquipment(es);
                cur.advance(7);
                BorrowInformation bi = new BorrowInformation(cur2, cur, es, m);
                es.setBorrowInfo(bi);
                e.borrowEquipmentSet(es);
            }
        }
        addUndoCommand(this);
        clearRedoList();
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
