package carPractice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Car Class will simulate the checking of all parts in order to determine if the car will run.
 * If a part is essential and not functional, the car will not run. In line with this the car will 
 * still run if the part is not essential even if it is broken.
 * @author Lori Oliver
 *
 */
public class Car {
	private int wheels = 4;
	private String color, make, model;
	protected List<CarPart> parts = new ArrayList<>();
	private String[] partTypes = {"CarPart", "CarPart"};
	
	
	// New Default Car
	public Car() {
		this.color = "White";
		this.make = "Cheverolet";
		this.model = "Cruze";
		
		parts.add(new Engine(100, "Fly Engine"));
		parts.add(new CarPart());
		parts.add(new CarPart(100, "Headlights"));
		parts.add(new FuelTank());
		parts.add(new Wheels());
	}
	// New Named Car
	public Car(String clr, String mk, String mdl) {
		this.color = clr;
		this.make = mk;
		this.model = mdl;
		
		parts.add(new CarPart());
		parts.add(new Engine(100, "Star Engine"));
		parts.add(new CarPart(100, "Headlights"));
		parts.add(new FuelTank("Big-Cup Fuel Tank"));
		parts.add(new Wheels("Sparky Wheels"));
	}
	// Used Default Car
	public Car(String used) {
		this.color = "Silver";
		this.make = "Dodge";
		this.model = "Caravan";
		
		parts.add(new CarPart(50));
		parts.add(new Engine(50, "China Engine"));
		parts.add(new CarPart(50, "Headlights"));
		parts.add(new FuelTank(50));
		parts.add(new Wheels(50));
	}
	// Used Named Car
	public Car(String clr, String mk, String mdl, int cond) {
		this.color = clr;
		this.make = mk;
		this.model = mdl;
		
		parts.add(new CarPart(cond));
		parts.add(new Engine(cond, "Star Engine"));
		parts.add(new CarPart(cond, "Headlights"));
		parts.add(new FuelTank((cond), "Steel Holder Fuel Tank"));
		parts.add(new Wheels(cond, "Firestone Tires"));
	}
	
	/**
	 * The run method determines if a car is able to run. If it is it will call the function() on all
	 * parts and the print Vrrroooom.
	 * A car will run if all essential parts are functional.
	 */
	protected void run() {
		System.out.println("Will it run: " + this.willRun());
		if(this.willRun()) {
			Iterator<CarPart> it = parts.iterator();
			CarPart temp; 
			while(it.hasNext()) {
				temp = (CarPart)it.next();
				// Here we need to account for the type difference
				if(temp instanceof Engine) {
					Engine epp = (Engine) temp;
					epp.function();
				}if(temp instanceof FuelTank) {
					FuelTank ft = (FuelTank)temp;
					ft.function();
				}else
					temp.function();					
			}
			System.out.println("Vrrroooom, Vrrroooom\n");
		}else
			System.out.println( "This car will not run due to the errors above!\n" + this.toString());
	}
	
	/**
	 * The willRun private inner method (used by run) checks all parts and determines if the car will run.
	 * It also prints out any warnings or errors from the car.
	 * @return runs (boolean)
	 */
	private boolean willRun() {
		boolean runs = true;
		Iterator<CarPart> it = parts.iterator();
		CarPart temp;
		while(it.hasNext()) {
			temp = (CarPart)it.next();
			System.out.println(temp.getName() + " is functional: " + temp.isFunct());
			if(!temp.isFunct()) {
				// DEBUG System.out.println(temp.getName() + " is essential: " + temp.isEssent());
				if(temp.isEssent()) {
					runs = false;
					if(temp instanceof Engine)
						System.out.println("Error: " + ((Engine)temp).status());
					else if(temp instanceof FuelTank)
						System.out.println("Error: " + ((FuelTank)temp).status());
					else
						System.out.println("Error: " + temp.status());
				}else 
					System.out.println("Warning: " + temp.status());
			}
		}	
		return runs;
	}
	/**
	 * The carDetails method will print out the make, model and color of the car as well as all the parts 
	 * associated with the car.
	 * @return msg String containing all this info and formating.
	 */
	protected String carDetails() {
		String msg = "\nCar Details:\nMake: ";
		msg += this.make + "\nModel: " + this.model;
		msg += "\nColor: " + this.color + "\nWheels: " + this.wheels + "\n\n";
		
		Iterator<CarPart> it = parts.iterator();
		while(it.hasNext()) {
			CarPart thePart = it.next();
			if(thePart instanceof Engine) {
				msg += ((Engine)thePart).status();
			}else if(thePart instanceof FuelTank) {
				msg += ((FuelTank)thePart).status();
			}else
				msg += thePart.status();
		}
		return msg;		
	}
	
	public String toString() {
		String msg = "\nCar Details:\nMake: ";
		msg += this.make + "\nModel: " + this.model;
		msg += "\nColor: " + this.color + "\n";
		return msg;
	}
	
	protected void listParts() {
		int count = 1;
		for(CarPart part: this.parts) {
			//System.out.println(String.format("%1$d. %2$s has %3$d'%'", count++, part.getName(), part.getCondition()));
			System.out.println(count++ + ". " + part.getName() + " has " + part.getCondition() + "%");
		}
	}
	
}
