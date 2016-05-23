
public interface Ingredient {
	
	void setShots(int numberOfShots);
	double getPrice();
	int getVolume();
	boolean isCompatibleWith(Ingredient ingredient);
	
	boolean isValidWithChocolate();
	boolean isValidWithEspresso();
	boolean isValidWithLemon();
	boolean isValidWithMilk();
	
}
