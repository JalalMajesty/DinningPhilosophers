package codePackage;
//Hemidach, Mohammed Jalal
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JLabel;

public class Fork extends ReentrantLock{
	//class attributes
	int id;// fork ID
	JLabel label;// fork label

	//Fork Constructor
	public Fork(int id, JLabel label) {
		super();
		this.id = id;
		this.label = label;
	}
	
	//method that allows the Philosopher to pick up the fork
	public synchronized void pickUp(){
		//locking the fork to ensure unique access
		this.lock();
		//updating the fork label on the GUI
		label.setIcon(DiningPhilosophers.forkUsedIcons[id]);
	}
	
	//method that allows the Philosopher to pick up the fork
	public synchronized void putDown(){
		//unlocking the fork to make it available for the neighbor Philosopher to use
		this.unlock();
		//updating the fork label on the GUI
		label.setIcon(DiningPhilosophers.forkAvailableIcons[id]);
	}
	
	
}
