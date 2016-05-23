
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
	
	private static final int  MAX_VOLUME = 300;
	
	protected static String[] menu = {"Hot chocolate", 
			                            "Espresso", 
			                            "Lemon juice", 
			                            "Milk"};
	
	protected ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	protected int volume = 0;
	private Scanner scanner; 
	private PrintStream output; 

	
	public Order(Scanner in, PrintStream out) {
		this.scanner = in;
		this.output = out;
	}

	
	public void makeOrder(IngredientFactory factory) {
		output.print("Order started with ");
		Ingredient ingredient = factory.createCoffee();
		addIngredient(ingredient);
		showMenu();
		
		int option = getOption();

		while(option != 0) {
			if (option > 0 && option <= menu.length) {			
				switch (option) {
					case 1:   ingredient = factory.createCholocate();
					          break;
					case 2:   ingredient = factory.createEspresso();
		                   break;
					case 3:   ingredient =  factory.createLemon();
		                   break;
					case 4:   output.print("Do you want the milk steamed? (y/n): ");
					          String choice = scanner.next();
					          if (choice != null && (choice.equals("y") || choice.equals("Y"))) {
								    ingredient =  factory.createSteamedMilk();
					          } else {
					         	 ingredient = factory.createMilk();
					          }
		                   break;
				}
				
				if (!(isValidCombination(ingredient))) {
					output.println("Sorry, " 
				                  + ingredient.toString() 
				                  + " is not compatible with an earlier choice.");	
				} else {
					ingredient.setShots(getNumberOfShots());
					if ((volume + ingredient.getVolume()) > MAX_VOLUME) {
						output.println("Cup too full to add the ingredient.");
					} else {
						addIngredient(ingredient);
					}
				}
			} else {
				output.print("Not a valid choice.");
			}
			
			option = getOption();
		}
		output.println("\nDone!! Please pay " + getTotalPrice() + " HKD.");
	}

	protected String getTotalPrice() {
		double total = 0;
		for (Ingredient i : ingredients) {
			total += i.getPrice();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(total);
	}

	protected void showMenu() {
		output.println("\nWhat would you like to add to your coffee?");
		for (int i = 0; i < menu.length; i++) {
		   output.println((i + 1) + ". " + menu[i]);
		}
	}
	
	protected int getOption() {
		output.print("\nEnter your choice (1 - "
					+ menu.length
					+ ") to add an ingredient, or 0 to finish the order: ");
		return getInt();
	}
	
	protected int getNumberOfShots() {
		output.print("How many shots?: ");
		return getInt();
	}
	
	protected int getInt() {
		int input = 0;
		boolean invalidInput = true;
		while (invalidInput) {
			if (scanner.hasNextInt()) {
				input = scanner.nextInt();
				invalidInput = false;
			} else {	
				scanner.next();
				output.print("Not a valid input. Please try again: ");
			}
		}
		return input;
	}

	protected void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
		volume += ingredient.getVolume();
		int freespace = MAX_VOLUME - volume;
		output.print(ingredient.getVolume() 
				       + "ml of "
				       + ingredient.toString() 
				       + " added.");
		output.println(" You can add "
		               + freespace
		               + " ml more ingredients.");
	}
	
	protected boolean isValidCombination(Ingredient ingredient) {
		for (Ingredient i : ingredients) { 
			if ( !(ingredient.isCompatibleWith(i)) ) {
				return false;	
			}
		}
		return true;
	}

}