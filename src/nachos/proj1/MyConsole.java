package nachos.proj1;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class MyConsole {

	SerialConsole sercon = Machine.console();
	Semaphore sem = new Semaphore(0);
	char temp;
	
	public MyConsole() {
		// TODO Auto-generated constructor stub
		Runnable receiveInterruptHandler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				temp = (char)sercon.readByte();
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
		sercon.setInterruptHandlers(receiveInterruptHandler, sendInterruptHandler);
	}
	
	public String read() {
		String str = "";
		do {
			sem.P();
			if (temp != '\n') {
				str += temp;
			}
		} while (temp != '\n');
		
		return str;
	}
	
	public int readInt() {
		int num = -1;
		try {
			num = Integer.parseInt(read());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return num;
	}
	
	public void write(String str) {
		for (int i = 0; i < str.length(); i++) {
			sercon.writeByte(str.charAt(i));
			sem.P();
		}
	}
	
	public void writeln(String str) {
		write(str+"\n");
	}
	
	public void writeln(int num) {
		writeln(""+num);
	}

}
