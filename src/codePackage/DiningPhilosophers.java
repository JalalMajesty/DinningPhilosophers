package codePackage;
// Hemidach, Mohammed Jalal
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DiningPhilosophers {
	//class attributes
	private JFrame frame;// frame for the GUI
	
	//Number of philosophers
	static final int NUM_PHILOSOPHERS = 5;
	
	//Creating an array of philosophers
	Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
	
	//creating semaphore permission to control which philosopher can pickup forks and eat 
	static Semaphore permissions = new Semaphore(2);
	
	//creating a boolean array to check the status of philosophers
	static boolean [] philIsEating = {false, false, false, false, false};
	
	//creating labels and arrays of labels for the GUI
	JLabel[] PhilsLables = new JLabel[5];
	JLabel[] forksLables = new JLabel[5];
	JLabel[] comments = new JLabel[5];
	JLabel[] meals = new JLabel[5];
	JLabel philLable_0;
	JLabel philLable_1;
	JLabel philLable_2;
	JLabel philLable_3;
	JLabel philLable_4;
	JLabel forkLable_0;
	JLabel forkLable_1;
	JLabel forkLable_2;
	JLabel forkLable_3;
	JLabel forkLable_4;
	private JLabel lblNewLabel_0;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel meal_0;
	private JLabel meal_1;
	private JLabel meal_2;
	private JLabel meal_3;
	private JLabel meal_4;
	//creating arrays of imageIcons for forks and philosophers
	ImageIcon plateIcon = new ImageIcon(getClass().getResource("plate.png"));
	static ImageIcon []philThinkingIcons = new ImageIcon[5];
	static ImageIcon []philEatingIcons = new ImageIcon[5];
	static ImageIcon []forkAvailableIcons = new ImageIcon[5];
	static ImageIcon []forkUsedIcons = new ImageIcon[5];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiningPhilosophers window = new DiningPhilosophers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DiningPhilosophers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//GUI Components
		frame = new JFrame();
		frame.setBounds(0, 0,900, 900);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creating a start button that calls the start method to start the application
		Button start = new Button("Start");
		start.setBounds(356,639 , 77, 22);
		start.setBackground(Color.GREEN);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
				}

		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(start);
		
		//filling the ImageIcon arrays with the images of the philosophers and forks
		for(int i = 0; i < philThinkingIcons.length; i++){
			philThinkingIcons[i]= new ImageIcon(getClass().getResource("phil_"+i+"_thinking.png"));
			philEatingIcons[i]= new ImageIcon(getClass().getResource("phil_"+i+"_eating.png"));
			forkAvailableIcons[i]= new ImageIcon(getClass().getResource("fork_"+i+"_available.png"));
			forkUsedIcons[i]= new ImageIcon(getClass().getResource("fork_"+i+"_used.png"));
		}
		
		//setting up dimensions names and coordinates for each label and adding it to the frame of the GUI 
		JLabel plateLable = new JLabel("Midle Plate");
		plateLable.setBounds(220, 170, 336, 333);
		plateLable.setIcon(plateIcon);
		frame.getContentPane().add(plateLable);
		
		philLable_0 = new JLabel("Philosopher 0");
		philLable_0.setBounds(344, 50, 89, 110);
		philLable_0.setIcon(philThinkingIcons[0]);
		frame.getContentPane().add(philLable_0);

		philLable_1 = new JLabel("Philosopher 1");
		philLable_1.setBounds(548, 165, 100, 110);
		philLable_1.setIcon(philThinkingIcons[1]);
		frame.getContentPane().add(philLable_1);

		philLable_2 = new JLabel("Philosopher 2");
		philLable_2.setBounds(523, 420, 100, 110);
		philLable_2.setIcon(philThinkingIcons[2]);
		frame.getContentPane().add(philLable_2);
		
		philLable_3 = new JLabel("Philosopher 3");
		philLable_3.setBounds(162, 434, 100, 110);
		philLable_3.setIcon(philThinkingIcons[3]);
		frame.getContentPane().add(philLable_3);
		
		philLable_4 = new JLabel("Philosopher 4");
		philLable_4.setBounds(134, 165, 100, 110);
		philLable_4.setIcon(philThinkingIcons[4]);
		frame.getContentPane().add(philLable_4);


		forkLable_0 = new JLabel("Fork 0");
		forkLable_0.setBounds(244, 88, 55, 110);
		forkLable_0.setIcon(forkAvailableIcons[0]);
		frame.getContentPane().add(forkLable_0);

		forkLable_1 = new JLabel("Fork 1");
		forkLable_1.setBounds(479, 88, 63, 110);
		forkLable_1.setIcon(forkAvailableIcons[1]);
		frame.getContentPane().add(forkLable_1);

		forkLable_2 = new JLabel("Fork 2");
		forkLable_2.setBounds(558, 286, 120, 100);
		forkLable_2.setIcon(forkAvailableIcons[2]);
		frame.getContentPane().add(forkLable_2);

		forkLable_3 = new JLabel("Fork 3");
		forkLable_3.setBounds(382, 514, 27, 96);
		forkLable_3.setIcon(forkAvailableIcons[3]);
		frame.getContentPane().add(forkLable_3);

		forkLable_4 = new JLabel("Fork 4");
		forkLable_4.setBounds(99, 298, 111, 96);
		forkLable_4.setIcon(forkAvailableIcons[4]);
		frame.getContentPane().add(forkLable_4);
		
		lblNewLabel_0 = new JLabel("Philosopher# 0");
		lblNewLabel_0.setBounds(344, 8, 169, 14);
		frame.getContentPane().add(lblNewLabel_0);
		
		lblNewLabel_1 = new JLabel("Philosopher# 1");
		lblNewLabel_1.setBounds(658, 213, 169, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Philosopher# 2");
		lblNewLabel_2.setBounds(632, 489, 169, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Philosopher# 3");
		lblNewLabel_3.setBounds(0, 482, 169, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Philosopher# 4");
		lblNewLabel_4.setBounds(0, 213, 169, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		meal_0 = new JLabel("Meal #");
		meal_0.setBounds(344, 25, 80, 14);
		frame.getContentPane().add(meal_0);
		
		meal_1 = new JLabel("Meal #");
		meal_1.setBounds(658, 238, 80, 14);
		frame.getContentPane().add(meal_1);
		
		meal_2 = new JLabel("Meal #");
		meal_2.setBounds(633, 514, 80, 14);
		frame.getContentPane().add(meal_2);
		
		meal_3 = new JLabel("Meal #");
		meal_3.setBounds(0, 501, 80, 14);
		frame.getContentPane().add(meal_3);
		
		meal_4 = new JLabel("Meal #");
		meal_4.setBounds(0, 238, 80, 14);
		frame.getContentPane().add(meal_4);
		
		// adding all the labels to Arrays of labels for easy access
		PhilsLables[0] = philLable_0;
		PhilsLables[1] = philLable_1;
		PhilsLables[2] = philLable_2;
		PhilsLables[3] = philLable_3;
		PhilsLables[4] = philLable_4;
		forksLables[0] = forkLable_0;
		forksLables[1] = forkLable_1;
		forksLables[2] = forkLable_2;
		forksLables[3] = forkLable_3;
		forksLables[4] = forkLable_4;
		comments[0] = lblNewLabel_0;
		comments[1] = lblNewLabel_1;
		comments[2] = lblNewLabel_2;
		comments[3] = lblNewLabel_3;
		comments[4] = lblNewLabel_4;
		meals[0] = meal_0;
		meals[1] = meal_1;
		meals[2] = meal_2;
		meals[3] = meal_3;
		meals[4] = meal_4;
	}
	
	//start method that gets called when the start button is pressed
	public void start() {		
		
		//Creating an array of forks
		Fork[] forks = new Fork[NUM_PHILOSOPHERS];

		//filling the array of forks with forks
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			//creating forks
			forks[i] = new Fork(i, forksLables[i]);
		}

		// Create the philosophers and start each running in its own thread.
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i, forks[(i + 1) % NUM_PHILOSOPHERS], forks[i], PhilsLables[i], comments[i], meals[i]);
			//creating threads and starting them
			new Thread(philosophers[i]).start();
		}
	}
	
	//The Test method: it checks the status of the left neighbor Philosopher
	public synchronized static boolean leftNeighbourPhilosopher(int id) {
		return DiningPhilosophers.philIsEating[(id + 1)
				% DiningPhilosophers.NUM_PHILOSOPHERS]; // return
	}

	//The Test method: it checks the status of the right neighbor Philosopher
	public synchronized static boolean rightNeighbourPhilosopher(int id) {

		if (id == 0)
			return DiningPhilosophers.philIsEating[4]; // return
		else
			return DiningPhilosophers.philIsEating[(id - 1)
					% DiningPhilosophers.NUM_PHILOSOPHERS]; // return
	}
}
