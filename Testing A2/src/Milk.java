
public class Milk extends IngredientSkeleton {
	
	public Milk() {
		super(40, 0, "Milk");
	}
	
	public boolean isCompatibleWith(Ingredient ingredient) {
		return ingredient.isValidWithMilk();
	}
	
	public boolean isValidWithLemon() {
		return false;
	}
	
}