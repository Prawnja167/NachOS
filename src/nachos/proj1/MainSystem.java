package nachos.proj1;

import nachos.machine.Machine;

public class MainSystem {

	MyConsole con = new MyConsole();
	MyNetworkLink nl = new MyNetworkLink();
	
	public MainSystem() {
		// TODO Auto-generated constructor stub
		int choice = 0;
		
		do {
			con.writeln("Computer No: " + nl.getAddress());
			con.writeln("1. Send File");
			con.writeln("2. View All File");
			con.writeln("3. Delete File");
			con.writeln("4. Write File");
			con.writeln("5. Read File");
			con.writeln("6. Exit");
			con.write(">> ");
			
			choice = con.readInt();
			switch (choice) {
			case 1 :
				String fileName = "";
				do {
					con.write("File Name: ");
					fileName = con.read();
				} while (!fileName.contains("."));
				
				String fileType = "";
				do {
					con.write("File Type: ");
					fileType = con.read();
				} while(!fileType.equals("Word") && !fileType.equals("Excel"));
				
				int fileSize = 0;
				do {
					con.write("File Size: ");
					fileSize = con.readInt();
				} while (fileSize < 1 || fileSize > 500);
				
				int dest = 0;
				do {
					con.write("Send to: ");
					dest = con.readInt();
				} while (dest < 0);
				
				nl.send(fileName, fileType, fileSize, dest);
				
				break;
			case 2 :
				nl.viewAll();
				break;
			case 3 :
				nl.viewAll();
				int del = 0;
				do {
					con.write("Delete File No: ");
					del = con.readInt();
				} while (del < 0);
				
				nl.delete(del-1);
				
				break;
			case 4 :
				nl.writeFile();
				break;
			case 5 :
				String content = nl.readFile();
				con.write(content);
				break;
			}
			
		} while (choice != 6);
		
		con.writeln("Time: "+Machine.timer().getTime());
	}

}
