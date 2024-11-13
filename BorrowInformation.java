public class BorrowInformation {
    public Day borrowDate;
    public Day returnDate;
    public EquipmentSet equipmentSet;
    public Member member;

    public BorrowInformation(Day borrowDate, Day returnDate, EquipmentSet equipmentSet, Member member) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.equipmentSet = equipmentSet;
        this.member = member;
    }

    public Day getBorrowDate() {
        return borrowDate;
    }

    public Day getReturnDate() {
        return returnDate;
    }

    public EquipmentSet getEquipmentSet() {
        return equipmentSet;
    }

    public Member getMember() {
        return member;
    }

    public void setBorrowDate(Day borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Day returnDate) {
        this.returnDate = returnDate;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
