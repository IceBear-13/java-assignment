import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

	public static void main(String [] args) throws FileNotFoundException {	
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Scanner inFile = new Scanner(new File(filepathname));
		
		//The first command in the file must be to set the system date 
		//(eg. "startNewDay 03-Jan-2024"); and it cannot be undone
		String cmdLine1 = inFile.nextLine();
		String[] cmdLine1Parts = cmdLine1.split(" ");
		System.out.println("\n> "+cmdLine1);
		SystemDate.createTheInstance(cmdLine1Parts[1]);
		
		while (inFile.hasNext()) {
			String cmdLine = inFile.nextLine().trim();
			
			//Blank lines exist in data file as separators.  Skip them.
			if (cmdLine.equals("")) continue;  

			System.out.println("\n> "+cmdLine);
			
			//split the words in actionLine => create an array of word strings
			String[] cmdParts = cmdLine.split(" "); 
			
			switch (cmdParts[0]) {
				case "register":
					(new CmdRegister()).execute(cmdParts);
					break;
				case "listMembers":
					(new CmdListMembers()).execute(cmdParts);
					break;
				case "startNewDay":
					(new CmdStartNewDay()).execute(cmdParts);
					break;
				case "create":
					(new CmdCreate()).execute(cmdParts);
					break;
				case "arrive":
					(new CmdArrive()).execute(cmdParts);
					break;
				case "borrow":
					(new CmdBorrow()).execute(cmdParts);
					break;
				case "listMemberStatus":
					(new CmdListMemberStatus()).execute(cmdParts);
					break;
				case "listEquipment":
					(new CmdListEquipment()).execute(cmdParts);
					break;
				case "listEquipmentStatus":
					(new CmdListEquipmentStatus()).execute(cmdParts);
					break;
				case "undo":
					RecordedCommand.undoOneCommand();
					break;
				case "redo":
					RecordedCommand.redoOneCommand();
					break;
				default:
					System.out.println("Unknown command - ignored.");
					break;
			}
			
		}

		inFile.close();	
		in.close();
	}
}