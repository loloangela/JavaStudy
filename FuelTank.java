package carPractice;

public class FuelTank extends CarPart {

	private int fuelLevel;
	private int fuelTank;
	private boolean compsFunct = true;
	
	private final int MAX_LVL = 100;
	private final int MAX_TANK = 1000;
	private final int DEG_TEN = 10; // Used in function() to degrade components
	private final int MAX_SUM = 1100;
	private final static String GEN_TANK = "Generic Fuel Tank";
	
	// New-Generic Fuel Tank Constructor
	public FuelTank() {
		super(GEN_TANK, true);
		this.setCondition(this.MAX_COMP);
	}
	// New-Named Fuel Tank Constructor	
	public FuelTank(String name) {
		super(name, true);
		this.setCondition(this.MAX_COMP);
	}
	// Used-Generic Fuel Tank Constructor
	public FuelTank(int num) {
		super(num,GEN_TANK, true);
		this.setCondition(num);
	}
	// Used-Named Fuel Tank Constructor
	public FuelTank(int num, String name) {
		super(num, name, true);
		this.setCondition(num);
	}

	public void function() {
		double avg = 0;
		if(this.functional) {
			if(this.compCheck()) {
				this.fuelLevel -= DEG_TEN;
				this.fuelTank -= DEG_TEN;
				avg = (((double)(this.fuelLevel + this.fuelTank) / (double)this.MAX_SUM)) * 100;
				//DEBUG System.out.println("The average is " + avg + "\nThe sum is " + (fuelLevel + fuelTank));
				super.setCondition((int)avg);
				this.compCheck();
			}	
		}
	}
	
	private boolean compCheck() {
		if((this.fuelLevel <= 0) || (this.fuelTank <= 0)) {
			this.functional = false;
			return false;
		}else
			return true;		
	}
	protected void setCondition(int num) {
		if(num <= 0) {
			this.fuelLevel = 0;
			this.fuelTank = 0;
			this.functional = false;
			this.compsFunct = false;
		}else if(num >= 100) {
			this.fuelLevel = this.MAX_LVL;
			this.fuelTank = this.MAX_TANK;
			this.functional = true;
			this.compsFunct = true;
		}else {
			this.fuelLevel = num;
			this.fuelTank = (int)(((double)num/(double)this.MAX_COMP) * this.MAX_TANK);
			this.functional = true;
			this.compsFunct = true;
		}
	}
	
	public String status() {
		if(this.fuelLevel > 0)
			System.out.println("The fuel level in this car is " + this.fuelLevel + "%");
		else 
			System.out.println("You're out of gas!!!");
		if(this.fuelTank > 0)
			System.out.println("The fuel tank has " + this.calcCompsPercent(this.fuelTank, this.MAX_TANK) + "% of life left.");
		else
			System.out.println("The fuel tank has a leak and is inoperable!! Replace immediately");
		return super.status();
	}
}
