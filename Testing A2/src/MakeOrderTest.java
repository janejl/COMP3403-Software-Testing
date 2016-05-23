import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.allOf;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MakeOrderTest {

	private ByteArrayOutputStream baoutput;
	private mockIngredientFactory mockFactory;
	private ArrayList<String> InputList;
	private ArrayList<Matcher> OutputList;
	private ArrayList<int[]> CountList;
	
	private Order order;
	private String output;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("=== Start Testing ===");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("=== Finish Testing ===");
	}

	@Before
	public void setUp() throws Exception {
		InputList = new ArrayList<String>();
		OutputList = new ArrayList<Matcher>();
		CountList = new ArrayList<int[]>();
		
		
		InputList.add("0");
		OutputList.add(allOf(
				containsString("Order started with 150ml of Coffee added. You can add 150 ml more ingredients."),
				containsString("Done!! Please pay 100.00 HKD.")
				));
		CountList.add(new int[]{1,0,0,0,0,0});
		
		InputList.add("-1 0");
		OutputList.add(allOf(
				containsString("Order started with 200ml of Coffee added. You can add 100 ml more ingredients."),
				containsString("Not a valid choice."),
				containsString("Done!! Please pay 20.00 HKD.")
				));
		CountList.add(new int[]{1,0,0,0,0,0});
		
		InputList.add("5 0");
		OutputList.add(allOf(
				containsString("Order started with 50ml of Coffee added. You can add 250 ml more ingredients."),
				containsString("Not a valid choice."),
				containsString("Done!! Please pay 30.00 HKD.")
				));
		CountList.add(new int[]{1,0,0,0,0,0});
		
		InputList.add("1 1 0");
		OutputList.add(allOf(
				containsString("Order started with 60ml of Coffee added. You can add 240 ml more ingredients."),
				containsString("60ml of Chocolate added. You can add 180 ml more ingredients."),
				containsString("Done!! Please pay 45.00 HKD.")
				));
		CountList.add(new int[]{1,1,0,0,0,0});
		
		InputList.add("2 2 0");
		OutputList.add(allOf(
				containsString("Order started with 70ml of Coffee added. You can add 230 ml more ingredients."),
				containsString("80ml of Espresso added. You can add 150 ml more ingredients."),
				containsString("Done!! Please pay 60.00 HKD.")
				));
		CountList.add(new int[]{1,0,1,0,0,0});
		
		InputList.add("3 1 0");
		OutputList.add(allOf(
				containsString("Order started with 140ml of Coffee added. You can add 160 ml more ingredients."),
				containsString("40ml of Lemon Juice added. You can add 120 ml more ingredients."),
				containsString("Done!! Please pay 27.70 HKD.")
				));
		CountList.add(new int[]{1,0,0,1,0,0});
		
		InputList.add("4 Y 1 0");
		OutputList.add(allOf(
				containsString("Order started with 210ml of Coffee added. You can add 90 ml more ingredients."),
				containsString("10ml of Steamed Milk added. You can add 80 ml more ingredients."),
				containsString("Done!! Please pay 45.50 HKD.")
				));
		CountList.add(new int[]{1,0,0,0,0,1});
		
		InputList.add("4 y 1 0");
		OutputList.add(allOf(
				containsString("Order started with 215ml of Coffee added. You can add 85 ml more ingredients."),
				containsString("80ml of Steamed Milk added. You can add 5 ml more ingredients."),
				containsString("Done!! Please pay 300.00 HKD.")
				));
		CountList.add(new int[]{1,0,0,0,0,1});
		
		InputList.add("4 n 1 0");
		OutputList.add(allOf(
				containsString("Order started with 250ml of Coffee added. You can add 50 ml more ingredients."),
				containsString("25ml of Milk added. You can add 25 ml more ingredients."),
				containsString("Done!! Please pay 59.00 HKD.")
				));
		CountList.add(new int[]{1,0,0,0,1,0});
		
		InputList.add("1 0");
		OutputList.add(allOf(
				containsString("Order started with 100ml of Coffee added. You can add 200 ml more ingredients."),
				containsString("Sorry, Chocolate is not compatible with an earlier choice."),
				containsString("Done!! Please pay 20.00 HKD.")
				));
		CountList.add(new int[]{1,1,0,0,0,0});
		
		InputList.add("1 1 0");
		OutputList.add(allOf(
				containsString("Order started with 280ml of Coffee added. You can add 20 ml more ingredients."),
				containsString("Cup too full to add the ingredient."),
				containsString("Done!! Please pay 55.50 HKD.")
				));
		CountList.add(new int[]{1,1,0,0,0,0});
	}

	@After
	public void tearDown() throws Exception {
	}

	// [coffee][chocolate][espresso][lemon][milk][steam]
	@Test
	public void test1() {
		System.out.println(">> Case 1:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(150, 100);
		
		order = new Order(new Scanner(InputList.get(0)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(0), mockFactory.getCountArray());
		assertThat(output, OutputList.get(0));		
		
		singleTestSummary();
	}
	
	@Test
	public void test2(){
		System.out.println(">> Case 2:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(200, 20);
		
		order = new Order(new Scanner(InputList.get(1)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(1), mockFactory.getCountArray());
		assertThat(output, OutputList.get(1));
		
		singleTestSummary();
	}
		
	@Test
	public void test3(){			
		System.out.println(">> Case 3:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(50, 30);
		
		order = new Order(new Scanner(InputList.get(2)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(2), mockFactory.getCountArray());
		assertThat(output, OutputList.get(2));
		
		singleTestSummary();
	}	
		
	@Test
	public void test4(){
		System.out.println(">> Case 4:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(60, 10);
		mockFactory.setMockBData(60, 35, "Chocolate", true);
		
		order = new Order(new Scanner(InputList.get(3)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(3), mockFactory.getCountArray());
		assertThat(output, OutputList.get(3));
		
		singleTestSummary();
	}	
		
	@Test
	public void test5(){	
		System.out.println(">> Case 5:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(70, 38);
		mockFactory.setMockBData(80, 22, "Espresso", true);
		
		order = new Order(new Scanner(InputList.get(4)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(4), mockFactory.getCountArray());
		assertThat(output, OutputList.get(4));
		
		singleTestSummary();
	}	
		
	@Test
	public void test6(){	
		System.out.println(">> Case 6:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(140, 17);
		mockFactory.setMockBData(40, 10.7, "Lemon Juice", true);
		
		order = new Order(new Scanner(InputList.get(5)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(5), mockFactory.getCountArray());
		assertThat(output, OutputList.get(5));
		
		singleTestSummary();
	}	
		
	@Test
	public void test8(){	
		System.out.println(">> Case 8:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(210, 25.5);
		mockFactory.setMockBData(10, 20, "Steamed Milk", true);
		
		order = new Order(new Scanner(InputList.get(6)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(6), mockFactory.getCountArray());
		assertThat(output, OutputList.get(6));
		
		singleTestSummary();
	}	
		
	@Test
	public void test9(){	
		System.out.println(">> Case 9:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(215, 200);
		mockFactory.setMockBData(80, 100, "Steamed Milk", true);
		
		order = new Order(new Scanner(InputList.get(7)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(7), mockFactory.getCountArray());
		assertThat(output, OutputList.get(7));
		
		singleTestSummary();
	}
		
	@Test
	public void test10(){
		System.out.println(">> Case 10:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(250, 40);
		mockFactory.setMockBData(25, 19, "Milk", true);
		
		order = new Order(new Scanner(InputList.get(8)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(8), mockFactory.getCountArray());
		assertThat(output, OutputList.get(8));
		
		singleTestSummary();
	}
		
	@Test
	public void test12(){
		System.out.println(">> Case 12:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(100, 20);
		mockFactory.setMockBData(75, 30, "Chocolate", false);
		
		order = new Order(new Scanner(InputList.get(9)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(9), mockFactory.getCountArray());
		assertThat(output, OutputList.get(9));
		
		singleTestSummary();
	}	
		
	@Test
	public void test13(){	
		System.out.println(">> Case 13:");
		baoutput = new ByteArrayOutputStream();
		mockFactory = new mockIngredientFactory();
		mockFactory.setMockAData(280, 55.5);
		mockFactory.setMockBData(21, 10, "Chocolate", true);
		
		order = new Order(new Scanner(InputList.get(10)), new PrintStream(baoutput));
		order.makeOrder(mockFactory);
		output = baoutput.toString();
		
		assertArrayEquals(CountList.get(10), mockFactory.getCountArray());
		assertThat(output, OutputList.get(10));
		
		singleTestSummary();
	}
	
	public void singleTestSummary(){
		int[] IngredientArray = mockFactory.getCountArray();
		String ingredientName = "";
		int numShots = 0;
		for(int i=0; i<6; ++i){
			if(IngredientArray[i] != 0){
				switch(i){
				case 0:
					System.out.println("Calls to createCoffee(): " + IngredientArray[i]);
					break;
				case 1:
					System.out.println("Calls to createChocolate(): " + IngredientArray[i]);
					numShots = mockFactory.getNumShotsB();
					ingredientName = "Chocolate";
					break;
				case 2:
					System.out.println("Calls to createEspresso(): " + IngredientArray[i]);
					numShots = mockFactory.getNumShotsB();
					ingredientName = "Espresso";
					break;
				case 3:
					System.out.println("Calls to createLemon(): " + IngredientArray[i]);
					numShots = mockFactory.getNumShotsB();
					ingredientName = "Lemon";
					break;
				case 4:
					System.out.println("Calls to createMilk(): " + IngredientArray[i]);
					numShots = mockFactory.getNumShotsB();
					ingredientName = "Milk";
					break;
				case 5:
					System.out.println("Calls to createSteamedMilk(): " + IngredientArray[i]);
					numShots = mockFactory.getNumShotsB();
					ingredientName = "SteamedMilk";
				}
			}
		}
		
		if(ingredientName != ""){
			System.out.println("Shots of " + ingredientName + ": " + numShots);
		}
		System.out.println("Ingredients contains Coffee " + ingredientName);
	}
}










