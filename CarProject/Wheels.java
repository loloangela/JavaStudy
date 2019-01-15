package carPractice;



public class Wheels extends CarPart { 
	//[0] - LeftFront , [1] - RightFront, [2] - LeftRear, [3] RightRear
	protected int[] wheelState = {0, 0, 0, 0};
	private final String[] wheelName = {"Left Front", "Right Front", "Left Rear", "Right Rear"};
	private final int MAX_WHEEL = 1000;
	private final int MAX_SUM = 4000;
	private DriveType dt;
	private final static String GEN_NAME = "Generic Wheels";
	
	// New-Generic Wheels
	public Wheels() {
		super(GEN_NAME, true);
		this.setCondition(MAX_COMP);
		this.setDriveType(1);
		
	}
	// New-Named Wheels
	public Wheels(String name) {
		super(name, true);
		this.setCondition(MAX_COMP);
		this.setDriveType(3);
	}
	// Used-Generic Wheels
	public Wheels(int num) {
		super(num, GEN_NAME, true);
		this.setCondition(num);
		this.setDriveType(2);
	}
	// Used-Named Wheels
	public Wheels(int num, String name) {
		super(num, name, true);
		this.setCondition(num);
		this.setDriveType(3);
	}
	
	public void function() {
		if(this.functional) {
			if(this.compCheck()) {
				// Wear down differently depending on the DriveType
				if(this.dt.name() == "REAR") {
					// Rear wheels will degrade faster
					this.wheelState[0] -= 10;
					this.wheelState[1] -= 10;
					this.wheelState[2] -= 20;
					this.wheelState[3] -= 20;
				}else if(this.dt.name() == "FRONT") {
					// Front wheels will degrade faster
					this.wheelState[0] -= 20;
					this.wheelState[1] -= 20;
					this.wheelState[2] -= 10;
					this.wheelState[3] -= 10;
				}else {
					// All wheels will degrade at the same pace.
					for(int i = 0; i < this.wheelState.length; i++) {
						this.wheelState[i] -= 10;
					}
				}
				this.compCheck();
				double avg = 0;
				System.out.println("The length of the wheel array: " + this.wheelState.length);
				for(int i = 0; i < 4; i++) {
					avg += this.wheelState[i];
				}
				avg /= (double)this.MAX_SUM * 100.0;
				super.setCondition((int)avg);
			}		
		}	
	}
	
	protected void setCondition(int num) {
		// System.out.println("The length: " + this.wheelState.length);
		if(num <= 0) {
			System.out.println("The length: " + this.wheelState.length);
			this.functional = false;
			for(int i = 0; i < 4; i++) {
				this.wheelState[i] = 0;
			}
		}else if(num >= 100) {
			System.out.println("The length: " + this.wheelState.length);
			this.functional = true;
			for(int i = 0; i < 4; i++) {
				System.out.println("i = " + i);
				this.wheelState[i] = 100;
			}
		}else {
			this.functional = true;
			for(int i = 0; i < this.wheelState.length; i++) {
				this.wheelState[i] = (int) ((num/100.0) * MAX_WHEEL);
				System.out.println("The value of tire life is: " + this.wheelState[i]);
			}	
		}
	}
	
	private boolean compCheck() {
		for(int i = 0; i < this.wheelState.length; i++) {
			if(this.wheelState[i] <= 0) {
				this.functional = false;
				return false;
			}		
		}
		return true;
	}
	
	public String status() {
		for(int i = 0; i < this.wheelState.length; i++) {
			if(this.wheelState[i] > 0)
				System.out.println("The " + this.wheelName[i] + " Wheel has " + this.calcCompsPercent(this.wheelState[i], MAX_WHEEL) + "% of life left");
			else
				System.out.println("The " + this.wheelName[i] + " Wheel needs to be replaced.");
		}
		return super.status();
	}
	
	protected String getDriveType() {
		return dt.getType();
	}
	
	protected void setDriveType(int x) {
		// Front = 1, Rear - 2, Four 3
		if((x < 1) || (x > 3))
			x = 1; // Set to default of Front Wheel Drive
		if(x == 1)
			this.dt = DriveType.FRONT;
		else if (x == 2)
			this.dt = DriveType.REAR;
		else if (x == 3)
			this.dt = DriveType.FOUR;
	}
	enum DriveType{
		FRONT("Front Wheel Drive"), REAR("Rear Wheel Drive"), FOUR("Four Wheel Drive");
		
		private String type;
		
		public String getType() {
			return this.type;
		}
		
		private DriveType(String type) {
			this.type = type;
		}
	}
}
