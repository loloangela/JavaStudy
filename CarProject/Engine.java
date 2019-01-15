package carPractice;

public class Engine extends CarPart {
	//Components
	private int pistons;
	private int crankShaft;
	private int cylindars;
	private int sparkPlugs;
	
	boolean compsFunct = true;
	boolean running = false;
	boolean compSet;
	private final int NUM_COMPS = 4;
	private final int MAX_PIST = 500;
	private final int MAX_SHAFT =  500;
	private final int MAX_CYL = 400;
	private final int MAX_SPARK = 1000;
	private final int MAX_SUM = 2400;
	private final int DEG_FIVE = 5; // Used in function() to degrade components
	private final int DEG_TEN = 10; // Used in function() to degrade components
	
	public Engine() {
		super("Stock Engine", true);
		setCondition(100);
	}

	public Engine(int num, String name) {
		super(num, name, true);
		setCondition(num);
	}
	
	public Engine(String name) {
		super(name, true);
		setCondition(MAX_COMP);
	}
	
	/**
	 * This method takes an integer that represents the percentage for the condition of the engine
	 * It then applies this percent to the individual components
	 * @param num
	 */
	protected void setCondition(int num) {
			if(num <= 0) {
				this.pistons = 0;
				this.crankShaft = 0;
				this.cylindars = 0;
				this.sparkPlugs = 0;	
				this.functional = false;
				this.compsFunct = false;
			}else if(num >= MAX_COMP) {
				this.pistons = MAX_PIST;
				this.crankShaft = MAX_SHAFT;
				this.cylindars = MAX_CYL;
				this.sparkPlugs = MAX_SPARK;	
				this.functional = true;
				this.compsFunct = true;
			}else {
				double percentage = num / 100.0;
				//DEBUG System.out.println("The percentage is: " + percentage);
				this.pistons = (int)(MAX_PIST * percentage);
				this.crankShaft = (int)(MAX_SHAFT * percentage);
				this.cylindars = (int)(MAX_CYL * percentage);
				this.sparkPlugs = (int)(MAX_SPARK * percentage);
				this.functional = true;
				this.compsFunct = true;
			}
			//DEBUG System.out.println(String.format("Pistons: %1$d, CrankShaft: %2$d, Cylindars: %3$d, SparkPlugs: %4$d", pistons, crankShaft, cylindars, sparkPlugs));
			super.setCondition(num);
	}
	
	
	
	/**
	 * This method simulates the degradation of the components within this part.
	 * Pistons, CrankShaft, and Cylindars degrade by 5, SparkPlug degrades by 10
	 * It will then calculate the new avg and upload the CarPart's condition
	 */
	@Override
	public void function() {
		double avg = 0;
		if(functional) {
			this.compCheck();
			if(this.compsFunct) {
				this.pistons -= DEG_FIVE;
				this.crankShaft -= DEG_FIVE;
				this.cylindars -= DEG_FIVE;
				this.sparkPlugs -= DEG_TEN;
				avg = (((double)(this.pistons + this.crankShaft + this.cylindars + this.sparkPlugs)/ (double)MAX_SUM) * 100);
				// DEBUG System.out.println("The average is: " + avg);
				this.compSet = true;
				super.setCondition((int)avg);
				this.compCheck();
				if(!this.running) 
					this.running = true;
				else
					this.running = false;
			}
		}
	}
	
	private void compCheck() {
		if(this.pistons <= 0)
			this.compsFunct = false;
		if(this.crankShaft <= 0)
			this.compsFunct = false;
		if(this.cylindars <= 0)
			this.compsFunct = false;
		if(this.sparkPlugs <= 0)
			this.compsFunct = false;
		if(!compsFunct)
			this.functional = false;
	}

   /**
    * This will look at all of the components and print their status. 
    * It will call the super's status method and generate the results for the product as a whole
    */
	@Override
	public String status() {
		String msg = "";
		if(this.pistons > 0) 
			System.out.println("The pistons have %" + this.calcCompsPercent(this.pistons, this.MAX_PIST) + " of life left.");
		else
			System.out.println("The pistons have failed!");
		if(this.crankShaft > 0) 
			System.out.println("The crankShaft has %" + this.calcCompsPercent(this.crankShaft, this.MAX_SHAFT) + " of life left.");
		else
			System.out.println("The crankShaft has failed!");
		if(this.cylindars > 0) 
			System.out.println("The cylindars have %" + this.calcCompsPercent(this.cylindars, this.MAX_CYL) + " of life left.");
		else
			System.out.println("The cylindars have failed!");
		if(this.sparkPlugs > 0) 
			System.out.println("The sparkPlugs have %" + this.calcCompsPercent(this.sparkPlugs, this.MAX_SPARK) + " of life left.");
		else
			System.out.println("The sparkPlugs have failed!");
		if(this.running)
			System.out.println(this.getName() + " is running...");
		else
			System.out.println(this.getName() + " is off.");
		
		return super.status();
	}
	
	
}
