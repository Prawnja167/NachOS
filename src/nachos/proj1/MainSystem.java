package nachos.proj1;

import java.util.Vector;

import nachos.machine.FileSystem;
import nachos.machine.Machine;
import nachos.machine.OpenFile;
import nachos.threads.KThread;

public class MainSystem {

	MyConsole console = new MyConsole();
	int time = 0;
	Vector<FoodOrder> orderList = new Vector<>();
	FileSystem fs = Machine.stubFileSystem();
	
	public MainSystem() {
		//pas UAP, kalo nachos.confnya kosong copy dr proj 2 ato yg lain 
		//1. TIMER
		Runnable handler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//biar timernya jd santuy, cuma iseng doang 
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//kalau Runnable insance, jan pake console
				System.out.println("Time: " + time);
				time++;
			}
		};
		Machine.timer().setInterruptHandler(handler);
		
		console.read(); //getchar
		Machine.timer().setInterruptHandler(null); //biar writeln dibawah ga nabrak sama runnablenya di console
		console.writeln("Total ticks: " + time); //will show up when you press enter on console
		
		//2. MENU
//		int menu = 0;
//		
//		//baca file
//		//String array[] = "aa#2#5".split("#");
//		//array[0] = "aa#2#5
//		//masukin ke vector
//		
//		do {
//			console.writeln("1. Add Food Order");
//			console.writeln("2. Serve Food Order");
//			console.writeln("3. Exit");
//			console.write("Choice: ");
//			
//			menu = console.readInt();
//			switch(menu) {
//				case 1:
//					String customerName = "";
//					do {
//						console.write("Customer Name[5-20]: ");
//						customerName = console.read();
//					} while (customerName.length() < 5 || customerName.length() > 20);
//					
//					String foodName = "";
//					do {
//						console.write("Food Name[Pizza|Burger]: ");
//						foodName = console.read();
//					} while (!foodName.equalsIgnoreCase("Pizza") && !foodName.equalsIgnoreCase("Burger"));
//					
//					int quantity = 0;
//					do {
//						console.write("Food Quantity[>0]: ");
//						quantity = console.readInt();
//					} while (quantity <= 0);
//					
//					FoodOrder order = new FoodOrder(customerName, foodName, quantity);
//					orderList.add(order);
//					
//					console.writeln("Order Success");
//					console.read();
//					
//					break;
//				case 2:
//					while(!orderList.isEmpty()) {
//						//FIFO
//						new KThread(orderList.remove(0)).fork(); //fork buat trigger KThread
//						//supaya jadi LIFO instead of FIFO
//						//orderList.remove(orderList.size()-1).fork();
//					}
//					
//					console.writeln("Finish serving");
//					console.read();
//					
//					break;
//			}
//			
//		} while(menu != 3);
//		
//		writeFile();
//		Machine.timer().setInterruptHandler(null);
//		console.writeln("Total ticks: " + time);
//		
	}
//	
//	private void writeFile() {
//		OpenFile out = fs.open("data.txt", true); //file is on nachos.test
//		
//		//AAA#100#29
//		String data = "";
//		for(FoodOrder foodOrder : orderList) {
//			data += foodOrder.getCustomerName() + "#" + foodOrder.getFoodName() + "#" + foodOrder.getFoodQuantity() + "\n";
//		}
//		
//		byte[] dataBuffer = data.getBytes();
//		
//		out.write(dataBuffer, 0, dataBuffer.length);
//	}

}