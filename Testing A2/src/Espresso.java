
public class Espresso extends IngredientSkeleton {
	
	public Espresso() {
		super(25, 4, "Espresso");
	}
	
	public boolean isCompatibleWith(Ingredient ingredient) {
		return ingredient.isValidWithEspresso();
	}
	
}
