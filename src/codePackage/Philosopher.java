package codePackage;
//Hemidach, Mohammed Jalal
import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

public class Philosopher implements Runnable {
	//class attributes
	public int id;//Philosopher ID
	Fork leftFork, rightFrok; // Philosopher forks
	private JLabel philLabel, comments, meal; //Philosopher label that control the images on the GUI
	private Random randomPeriod = new Random(); // to generate a random number
	int maxPeriod = 5000; // maximum period of eating or thinking
	int numberOfMeals = 0;//number of meals

	//Philosopher constructor
	public Philosopher(int id, Fork leftFork, Fork rightFrok, JLabel philLabel, JLabel comments, JLabel meal) {
		super();
		this.id = id;

		this.leftFork = leftFork;
		this.rightFrok = rightFrok;

		this.philLabel = philLabel;
		this.comments = comments;
		this.meal = meal;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				this.think();
				this.hungry();
				this.eat();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Philosopher is thinking in this method
	private void think() throws InterruptedException {
		
		//Changing the Philosopher imageIcon to thinking state
		philLabel.setIcon(DiningPhilosophers.philThinkingIcons[id]);
		//updating the Philosopher comment on the GUI
		comments.setText("Philosopher# "+id+" Thinking");
		//updating the Philosopher comment's Color on the GUI
		comments.setForeground(Color.RED);
		//random period of thinking
		Thread.sleep(randomPeriod.nextInt(maxPeriod));
	}

	//Philosopher is hungry and asking for permission to pick up the forks to eat
	public void hungry() throws InterruptedException {
		//loop that keeps the Philosopher waiting for neighbors to finish eating
		while (DiningPhilosophers.leftNeighbourPhilosopher(id) || DiningPhilosophers.rightNeighbourPhilosopher(id)) {
			Thread.sleep(randomPeriod.nextInt(10));
		}
		//Semaphore Permission given to the Philosopher to eat
		DiningPhilosophers.permissions.acquire();
		//Philosopher picking up the left fork
		leftFork.pickUp();
		//Philosopher picking up the right fork
		rightFrok.pickUp();
		//Philosopher state change to eating (eating == true; thinking == false)
		DiningPhilosophers.philIsEating[id] = true;
	}

	//Philosopher starts eating
	private void eat() throws InterruptedException {
		// number of meals counter
		numberOfMeals++;
		//updating the Philosopher comment on the GUI
		comments.setText("Philosopher# "+id+" Eating");
		//updating the Philosopher comment's Color on the GUI
		comments.setForeground(Color.GREEN);
		//updating the number of meals eaten by the Philosopher on the GUI
		meal.setText("Meal # :" + numberOfMeals);
		//updating the number of meals text Color on the GUI
		meal.setForeground(Color.BLUE);
		//Changing the Philosopher picture to eating state
		philLabel.setIcon(DiningPhilosophers.philEatingIcons[id]);
		// random period of eating
		Thread.sleep(randomPeriod.nextInt(maxPeriod));
		//Philosopher putting down the left fork
		leftFork.putDown();
		//Philosopher putting down the right fork
		rightFrok.putDown();
		//Semaphore permission retrieved back from the philosopher
		DiningPhilosophers.permissions.release();
		//Philosopher state change to thinking (eating == true; thinking == false)
		DiningPhilosophers.philIsEating[id] = false;
	}
}
