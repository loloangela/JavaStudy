package carPractice;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Simulator {
	boolean hasCars = false;
	boolean exit = false;
	
	static Scanner kb = new Scanner(System.in);
	private String busHeader = "Welcome To Lori's Car Simulator";
	private String divider = "--------------------------------------------------";
	
	protected List<Car> carsList = new ArrayList<Car>();
	
	public void displayMenu() {
		String[] mainMenuOptions = {"1. Create a Car", "2. Drive Car", "3. Check Car", "4. Fill Tank", "5. Replace Parts", "6. Exit"};
		System.out.println(divider);
		System.out.println(String.format("\t%s", busHeader));
		System.out.println(divider);
		System.out.println();
		
		if(!hasCars) {
			System.out.println(mainMenuOptions[0]);
			System.out.println(mainMenuOptions[5]);
			System.out.println();
		}else {
			for(String option: mainMenuOptions) {
				System.out.println(option);
			}
			System.out.println();
		}
	}
	
	public boolean menuChoice(int choice) {
		Boolean validInput = false;
			if(!hasCars) {
				if(choice == 1) {
					validInput = true;
					createCars();
				}
				else if (choice == 6) {
					validInput = true;
					this.exit = true;
					displayExit();
				}
				else
					validInput = false;
			}else {
				switch(choice) {
				case 1: validInput = true;
						createCars();
						break;
				case 2: validInput = true;
						carsList(choice);
						break;
				case 3: validInput = true;
						carsList(choice);
						break;
				case 4: validInput = true;
						carsList(choice);
						break;
				case 5: validInput = true;
						carsList(choice);
						break;
				case 6: validInput = true;
						this.exit = true;
						displayExit();
						break;
			}
			if((choice < 0) || (choice > 6))
				validInput = false;
			}
			
			return validInput;
	
	}
	
	/*
	 * List all options to create cars
	 */
	public void createCars() {
		displayCreateMenu();
	}
	
	private void displayCreateMenu() {
		String carTitle = "Car Menu";
		boolean validInput = false;
		
		String[] createMenu = {"1. Create new default car", "2. Create new car", "3. Create used default car", "4. Create used car"};
		System.out.println(divider);
		System.out.println(String.format("\t%s", carTitle.toUpperCase()));
		System.out.println();
		do {
			for(String option : createMenu) {
				System.out.println(option);
			}
			validInput = createChoice(kb.nextInt());
			System.out.println(divider);
			//DEBUG System.out.println("This.Exit is " + this.exit);
		}while((!validInput) );
		
	}
	
	private boolean createChoice(int x) {
		boolean isValid = false;
		if((x < 1) || (x > 5))
			return isValid;
		else {
			isValid = true;
			if(x == 1) {
				this.carsList.add(new Car());
				this.hasCars = true;
				//DEBUG System.out.println("isValid is: " + isValid);
			}				
			if(x == 2) {
				// Get color make and model, create car and add to list
				buildCar(x);
			}
			if(x == 3) {
				this.carsList.add(new Car("used"));
				this.hasCars = true;
			}			
			if(x == 4) {
				// Get color make model and level of use, create car and add to list
				buildCar(x);
			}
			if(x == 5) {
				// Determine Car then part then replace the part
				carsList(x);
			}
		}
		return isValid;
	}
	
	private void buildCar(int x) {
		String mk, mdl, clr;
		int bound;
		String[] inputText = {"What is the color of the car? (No Spaces)", "What is the make of the car? (No Spaces)", 
				"What is the model of the vehicle? (No Spaces)", "How used is the vehicle? (0 - 100, 100 is new"};
		String[] inputAnswers= {"","","",""};
		if(x == 2)
			bound = 3;
		else
			bound = 4;
		for(int i = 0; i < bound; i++) {
			System.out.println(inputText[i]);
			System.out.println();
			inputAnswers[i] = kb.next();
		}
		System.out.println(divider);
		
		if(bound == 3) 
			this.carsList.add(new Car(inputAnswers[0], inputAnswers[1], inputAnswers[2]));
		else
			this.carsList.add(new Car(inputAnswers[0], inputAnswers[1], inputAnswers[2], Integer.parseInt(inputAnswers[3])));
		this.hasCars = true;
	}
	public void displayExit() {
		System.out.println(divider);
		System.out.println("\tThanks for using, bye!");
		System.out.println(divider);
	}
	
	/**
	 * carsLists List all cars in a menu the user will select which car to act upon
	 * int x represents what action is to be taken
	 * 2 - Run Car, 3 - Show Car Status, 4 - Fill Tank, 5 - Replace Parts
	 */
	public void carsList(int action) {
		if((action < 2) || (action > 5)) 
			System.out.println("Invalid input");
		else {
			// Display Title and List of Cars
			String title = "Car List";
			String info = "Choose from the list of cars below to perform the selected action";
			Boolean carOptValid = false;
			do {
				System.out.println(divider);
				System.out.println(String.format("\t%s", title.toUpperCase()));
				System.out.println(divider);
				System.out.println(info);
				int count = 1;
				for(Car aCar : this.carsList) {
					System.out.println(String.format("%d. %s", count++, aCar));
				}
				int carChoice = kb.nextInt();
				Object[] allCars =  this.carsList.toArray();
				if((carChoice <= allCars.length) && (carChoice > 0)) {
					Car theCar = (Car)allCars[carChoice -1];
					carOptValid = true;
					if(action == 2) {
						// Run the selected car "theCar"
						theCar.run();
					}else if(action == 3) {
						// Show Car Status
						System.out.println(theCar.carDetails());
					}else if(action == 4) {
						// Fill Tank
						Object[] allParts = theCar.parts.toArray();
						FuelTank selected = null;
						for(Object x : allParts) {
							if(x instanceof FuelTank)
								selected = (FuelTank)x;
						}
						selected.replace();
						System.out.println("The " + selected.name + " has been replaced.");
					}else if(action == 5) {
						// Replace Parts - Show Parts, Replace selected
						theCar.listParts();
						int partChoice = kb.nextInt();
						Object[] allParts = theCar.parts.toArray();
						((CarPart)allParts[partChoice -1]).replace();
						System.out.println("The " + ((CarPart)allParts[partChoice -1]).name + " has been replaced.");
					}
				}else {
					carOptValid = false;
					System.out.println(this.divider);
					System.out.println("Invalid Car Choice! Try Again.");
				}
					
			}while(!carOptValid);
			
			
		}
		
	}
	public static void main(String[] args) {
		Simulator sim = new Simulator();
		
		boolean validInput = true;
		do {
			sim.displayMenu();
			validInput = sim.menuChoice(kb.nextInt()); 
		}while((validInput) && (!sim.exit));
		
		/**
		// Create an instance of a Car object, and invoke that object's run() method.
		Car car1 = new Car();
		// DEBUG System.out.println(car1.getDriveType());
		car1.run();
		// DEBUG System.out.println(car1.carDetails());
		// DEBUG System.out.println();
		car1.run();
		
		Car car2 = new Car("Purple", "Cheverolet", "Mustang");
		// DEBUG System.out.println(car2.carDetails());
		car2.run();
		// DEBUG System.out.println(car2.carDetails());
		// DEBUG System.out.println();
		
		// DEBUG System.out.println(car1.carDetails());
		car1.run();
		// DEBUG System.out.println(car1.carDetails());
		// DEBUG System.out.println();
		
		
		// DEBUG System.out.println(car2.carDetails());
		car2.run();
		// DEBUG System.out.println(car2.carDetails());
		// DEBUG System.out.println();
		 * 
		 */
	}
}
