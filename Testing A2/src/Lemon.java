
public class Lemon extends IngredientSkeleton {
	
	public Lemon() {
		super(10, 5.5, "Lemon Juice");
	}
	
	public boolean isCompatibleWith(Ingredient ingredient) {
		return ingredient.isValidWithLemon();
	}
	
	public boolean isValidWithMilk() {
		return false;
	}
	
	public boolean isValidWithChocolate() {
		return false;
	}
	
}
