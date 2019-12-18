package nachos.proj1;

import nachos.threads.Scheduler;
import nachos.threads.ThreadQueue;

public class MyScheduler extends Scheduler{ //dont forget to extend this manually

	public MyScheduler() {
		// TODO Auto-generated constructor stub
	}
	
	//add unimplemented methods
	@Override
	public ThreadQueue newThreadQueue(boolean transferPriority) {
		// TODO Auto-generated method stub
		return new MyQueue(); //jangan lupa ganti ini
	}

}
