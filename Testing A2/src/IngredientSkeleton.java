
public abstract class IngredientSkeleton implements Ingredient {
	
	protected int shotVolume;
	protected double shotPrice;
	protected String name;
	protected int numberOfShots;
	
	public IngredientSkeleton(int volume, double price, String name) {
		this.shotVolume = volume;
		this.shotPrice = price;
		this.name = name;
		this.numberOfShots = 1;
		
	}
	
	public void setShots(int shots) {
		this.numberOfShots = shots;
	}

	public double getPrice() {
		return shotPrice * numberOfShots;
	}

	public int getVolume() {
	   return shotVolume * numberOfShots;
	}
	
	public boolean isCompatibleWith(Ingredient ingredient) {
		return true;
	}
	
	public boolean isValidWithChocolate() {
		return true;
	}
	
	public boolean isValidWithEspresso() {
		return true;
	}
	
	public boolean isValidWithLemon() {
		return true;
	}
	
	public boolean isValidWithMilk() {
		return true;
	}
		
	public String toString() {
		return name;
	}
	
}
