package carPractice;

public class CarPart implements Functional{
	private int condition;
	protected final int MAX_COMP = 100;
	protected boolean functional = true;
	protected boolean essential = false;
	protected String name;
	
	// New Generic Part (non-essential)
	public CarPart() {
		this.setCondition(MAX_COMP);
		this.name = "Generic Part";
	}
	// New Generic Part (essential)
	public CarPart(boolean ess) {
		this.setCondition(MAX_COMP);
		this.name = "Generic Part";
		this.essential = ess;
	}
	// New Named Part (non-essential)
	public CarPart(String name) {
		this.name = name;
		this.setCondition(MAX_COMP);
	}
	// New Named Part (essential)
	public CarPart(String name, boolean ess) {
		this.setCondition(MAX_COMP);
		this.name = name;
		this.essential = ess;
	}
	// Used Generic Part (non-essential)
	public CarPart(int num) {
		this.setCondition(num);
		this.name = "Generic Part";
	}
	// Used Generic Part (essential)
	public CarPart(int num, boolean ess) {
		this.setCondition(num);
		this.name = "Generic Part";
		this.essential = ess;
	}
	// Used Named Part (non-essential)
	public CarPart(int num, String name) {
		this.setCondition(num);
		this.name = name;
	}
	// Used Named Part (essential)
	public CarPart(int num, String name, boolean ess) {
		this.setCondition(num);
		this.name = name;
		this.essential = ess;
	}
	
	/**
	 * Performs the degradation of the part after use.
	 * The degradation only occurs if the part is functional.
	 * It also updates the functional var if the part is no longer functional after use
	 */
	public void function() {
		if(functional) {
			this.condition -= 10;
			if(this.condition <= 0) 
				this.setCondition(0);		
		}/**else
			System.out.println(this.name + " is broken!");**/
	}
	
	/**
	 * States the status of a functional part. States an error if the part is not functional
	 * @return msg String containing the status/error of part.
	 */
	public String status() {
		String msg = "";
		if(functional)
			msg = this.name + " has " + this.condition + "% of life left.\n";
		else
			msg += this.name + " will need to be replaced immediately!\n";
		return msg;
	}
	
	protected int calcCompsPercent(int num, int max) {
		 System.out.println("Num: " + num + " Max: " + max);
		double x = (double)num / (double)max;
		 System.out.println("Per: " + x);
		x *= 100;
		 System.out.println("Per: " + x);
		return (int)x;
	}
	/**
	 * This private method accepts an integer value that will represents the durability of a part.
	 * The condition of a part ranges from 0 to 100, this method validates the incoming number.
	 * It also updates the functional boolean accordingly.
	 * @param val
	 */
	protected void setCondition(int val) {
		this.functional = true;
		if(val > MAX_COMP) 
			this.condition = MAX_COMP;
		else if(val <= 0) {
			this.condition = 0;
			this.functional = false;
		}
		else
			this.condition = val;
	}
	
	public boolean isFunct() {
		return functional;
	}
	public boolean isEssent() {
		return essential;
	}
	public String getName() {
		return this.name;
	}
	public int getCondition() {
		return this.condition;
	}
	/**
	 * Replace with brand new part same name
	 */
	public void replace() {
		this.setCondition(MAX_COMP);
	}
	
	/**
	 * Replace with brand new part different name
	 * @param nm is the new name
	 */
	public void replace(String nm) {
		this.name = nm;
		this.setCondition(MAX_COMP);
	}
	
	/**
	 * Replace with used part with different name
	 * @param nm
	 * @param cond
	 */
	public void replace(String nm, int cond) {
		this.name = nm;
		this.setCondition(cond);
	}
}
