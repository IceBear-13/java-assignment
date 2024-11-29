public class MemberIDAlreadyInUse extends Exception{
    public MemberIDAlreadyInUse(Member m){
        super("Member ID already in use: " + m.toString());
    }    
}
