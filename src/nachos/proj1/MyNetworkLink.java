package nachos.proj1;

import java.util.Vector;

import nachos.machine.FileSystem;
import nachos.machine.Machine;
import nachos.machine.MalformedPacketException;
import nachos.machine.NetworkLink;
import nachos.machine.OpenFile;
import nachos.machine.Packet;
import nachos.threads.KThread;
import nachos.threads.Semaphore;

public class MyNetworkLink {
	
	NetworkLink nl = Machine.networkLink();
	Semaphore sem = new Semaphore(0);
	Vector<FileManager> vfm = new Vector<>();
	FileSystem fs = Machine.stubFileSystem();

	public MyNetworkLink() {
		// TODO Auto-generated constructor stub
		Runnable receiveInterruptHandler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Packet packet = nl.receive();
				String content = new String(packet.contents);
				String message[] = content.split("#");
				String fileName = message[0];
				String fileType = message[1];
				int fileSize = Integer.parseInt(message[2]);
				
				System.out.println("You have received a file!");
				System.out.println("From: "+packet.srcLink);
				System.out.println("Name: "+fileName);
				System.out.println("Type: "+fileType);
				System.out.println("Size: "+fileSize);
				
				FileManager fm = new FileManager(fileName, fileType, fileSize, packet.srcLink);
				vfm.add(fm);
				
				sem.V();
			}
		};
		
		Runnable sendInterruptHandler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sem.V();
			}
		};
		
		nl.setInterruptHandlers(receiveInterruptHandler, sendInterruptHandler);
	}
	
	public void send(String fileName, String fileType, int fileSize, int dest) {
		String content = fileName+"#"+fileType+"#"+fileSize;
		byte[] contentByte = content.getBytes();
		
		try {
			Packet packet = new Packet(dest, getAddress(), contentByte);
			nl.send(packet);
			sem.P();
		} catch (MalformedPacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewAll() {
		if (vfm.isEmpty()) {
			System.out.println("No File Found!");
		} else {
			for (FileManager fileManager : vfm) {
				new KThread(fileManager).fork();
			}
		}
	}
	
	public void delete(int index) {
		vfm.remove(index);
	}
	
	public void writeFile() {
		OpenFile w = fs.open("file.txt", true);
		String content = "";
		for (FileManager fileManager : vfm) {
			content += fileManager.getFileName()+"#"+fileManager.getFileType()+"#"+fileManager.getFileSize()+"#"+fileManager.getFileSender()+"\n";
		}
		byte[] contentByte = content.getBytes();
		
		w.write(contentByte, 0, contentByte.length);
	}
	
	public String readFile() {
		String content = "";
		try {
			OpenFile r = fs.open("file.txt", false);
			byte[] temp = new byte[999];
			r.read(temp, 0, r.length());
			content = new String(temp);
		} catch (Exception e) {
			// TODO: handle exception
			content = "No File Found!";
		}
		return content;
	}
	
	public int getAddress() {
		return nl.getLinkAddress();
	}

}
