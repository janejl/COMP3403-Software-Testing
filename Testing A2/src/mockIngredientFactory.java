public class mockIngredientFactory implements IngredientFactory{

	private static int[] countIngredientCalls = new int[6];	
	private mockIngredient mockA, mockB;
	
	public mockIngredientFactory(){
		countIngredientCalls = new int[]{0,0,0,0,0,0};
	}
	
	public void setMockAData(int volume, double price){
		mockA = new mockIngredient(volume, price, "Coffee", true);
	}
	
	public void setMockBData(int volume, double price, String name, boolean validity){
		mockB = new mockIngredient(volume, price, name, validity);
	}

	public int getNumShotsB(){
		return mockB.getShots();
	}
	
		
	@Override
	public Ingredient createCoffee() {
		countIngredientCalls[0]++;
		return mockA;
	}
	
	@Override
	public Ingredient createCholocate() {
		countIngredientCalls[1]++;
		return mockB;
	}

	@Override
	public Ingredient createEspresso() {
		countIngredientCalls[2]++;
		return mockB;
	}

	@Override
	public Ingredient createLemon() {
		countIngredientCalls[3]++;
		return mockB;
	}

	@Override
	public Ingredient createMilk() {
		countIngredientCalls[4]++;
		return mockB;
	}

	@Override
	public Ingredient createSteamedMilk() {
		countIngredientCalls[5]++;
		return mockB;
	}
	
	public int[] getCountArray(){
		return countIngredientCalls;
	}
}
