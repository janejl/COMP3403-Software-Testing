
public class Chocolate extends IngredientSkeleton

{
	public Chocolate() {
		super(30, 5, "Chocolate");
	}

   public boolean isCompatibleWith(Ingredient ingredient) {
	   return ingredient.isValidWithChocolate();
   }
   
	public boolean isValidWithLemon() {
		return false;
	}	
}