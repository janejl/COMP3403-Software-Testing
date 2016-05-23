public class mockIngredient implements Ingredient{
	protected final int volume;
	protected final double price;
	protected String name;
	protected int numberOfShots;
	protected boolean validity;
	
	public mockIngredient(int volume, double price, String name, boolean validity){
		this.volume = volume;
		this.price = price;
		this.name = name;
		this.numberOfShots = 1;
		this.validity = validity;
	}
	
	@Override
	public void setShots(int numberOfShots) {
		this.numberOfShots = numberOfShots;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public int getVolume() {
		return volume;
	}
	
	public int getShots(){
		return numberOfShots;
	}

	@Override
	public boolean isCompatibleWith(Ingredient ingredient) {
		return validity;
	}

	@Override
	public boolean isValidWithChocolate() {
		return false;
	}

	@Override
	public boolean isValidWithEspresso() {
		return false;
	}

	@Override
	public boolean isValidWithLemon() {
		return false;
	}

	@Override
	public boolean isValidWithMilk() {
		return false;
	}
	
	public String toString() {
		return name;
	}
}
