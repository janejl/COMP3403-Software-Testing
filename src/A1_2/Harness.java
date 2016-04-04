package A1_2;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import C3403.A1.IUTEngine;
import C3403.A1.OracleEngine;

public class Harness{
	private ArrayList<Double> RSPList, SO2List, NO2List, O3List;
	private ArrayList<String> VariantList;
	
	private HashMap<String, Integer> randomTestBase;
	
	private final static Logger logger = Logger.getLogger("Harness");
	PrintWriter writer;
	
	public static void main(String[] args){
		// start log
		LogManager.getLogManager().reset();
		FileHandler fh;
        try {
            fh = new FileHandler("log.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        // start program
		System.out.println("Please select testing mode: \n1: Streamlined Domain Testing \n2: Random Testing");
		Scanner s = new Scanner(System.in);
		int mode = s.nextInt();
		
		Harness test = new Harness();
		
		switch(mode){
			case 1:
				System.out.println("Please enter the name of test file (.txt format): ");
				String fileName = (new Scanner(System.in)).nextLine();
				logger.info("test mode: " + mode + ", test file: " + fileName);
				
				test.LoadDomainTest(fileName);
				test.RunDomainTest();
				break;
			case 2:
				System.out.println("How many test cases you prefer in a random testing?");
				int loop = s.nextInt();
				logger.info("test mode: " + mode + ", test loops: " + loop);

				test.RunRandomTest(loop);
				break;
		}
		logger.info("test is done!\n");
		System.out.println("End of test!");
	}
	
	public void LoadDomainTest(String test_file){
		RSPList = new ArrayList<Double>();
		SO2List = new ArrayList<Double>();
		NO2List = new ArrayList<Double>();
		O3List = new ArrayList<Double>();
		VariantList = new ArrayList<String>();
		System.out.println("Loading test cases...");
		try{
			Scanner scan = new Scanner(new FileReader(test_file));
			StringTokenizer st;
					
			while(scan.hasNext()){
				st = new StringTokenizer(scan.nextLine());
				if(st.countTokens() == 5){
					RSPList.add(Double.valueOf(st.nextToken()));
					SO2List.add(Double.valueOf(st.nextToken()));
					NO2List.add(Double.valueOf(st.nextToken()));
					O3List.add(Double.valueOf(st.nextToken()));
					VariantList.add(st.nextToken());
				}else{
					System.out.println("DEBUG: please make sure the syntax of test file is correct!");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Test cases ready.");
	}
	
	public void RunDomainTest(){
		System.out.println("Running test cases...");
		IUTEngine iut = new IUTEngine();
		OracleEngine oracle = new OracleEngine();
		int iut_result, oracle_result;
		
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			logger.warning("[RunDomainTest] lack of output.txt");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.warning("[RunDomainTest] unsupported encoding exception");
			e.printStackTrace();
		}
	
		writer.println("========== Streamlined Domain Test Result ==========");
		for(int i=0; i<RSPList.size(); ++i){
			iut_result = iut.calculateIndex(RSPList.get(i), SO2List.get(i), NO2List.get(i), O3List.get(i));
			oracle_result = oracle.calculateIndex(RSPList.get(i), SO2List.get(i), NO2List.get(i), O3List.get(i));
			
			if(iut_result != oracle_result){
				writer.println("Test "+(i+1)+" Fail! RSP="+RSPList.get(i)+" SO2="+SO2List.get(i)+" NO2="+NO2List.get(i)+" O3="+O3List.get(i)+" - IUT = "+iut_result+", Oracle = "+oracle_result+", "+VariantList.get(i));
				logger.warning("Test "+(i+1)+" Fail! RSP="+RSPList.get(i)+" SO2="+SO2List.get(i)+" NO2="+NO2List.get(i)+" O3="+O3List.get(i)+" - IUT = "+iut_result+", Oracle = "+oracle_result+", "+VariantList.get(i));
			}else{
				writer.println("Test "+(i+1)+" Pass! RSP="+RSPList.get(i)+" SO2="+SO2List.get(i)+" NO2="+NO2List.get(i)+" O3="+O3List.get(i)+" - IUT = "+iut_result+", Oracle = "+oracle_result+", "+VariantList.get(i));
			}
		}
		
		writer.println("========== End of test ==========");
		writer.close();
	}

	public void RunRandomTest(int loop){
		randomTestBase = new HashMap<String, Integer>();
		String R, S, N, O;
		NumberFormat RSPFormat = new DecimalFormat("#0.0");
		NumberFormat OtherFormat = new DecimalFormat("#0.000");

		IUTEngine iut = new IUTEngine();
		OracleEngine oracle = new OracleEngine();
		
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			logger.warning("[RunRandomTest] lack of output.txt");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.warning("[RunRandomTest] unsupported encoding exception");
			e.printStackTrace();
		}
		
		writer.println("========== Random Test Result ==========");
		String key;
		int iut_result, oracle_result;
		Random r = new Random();
		for(int i=0; i<loop; ++i){
			R = RSPFormat.format(300 * r.nextDouble());
			S = OtherFormat.format(3 * r.nextDouble());
			N = OtherFormat.format(1.5 * r.nextDouble());
			O = OtherFormat.format(1.5 * r.nextDouble());
			
			key = R+" "+S+" "+N+" "+O;
			if(randomTestBase.containsKey(key)){
				--i;
				continue;
			}else{
				iut_result = iut.calculateIndex(Double.valueOf(R), Double.valueOf(S), Double.valueOf(N), Double.valueOf(O));
				oracle_result = oracle.calculateIndex(Double.valueOf(R), Double.valueOf(S), Double.valueOf(N), Double.valueOf(O));
				
				if(iut_result != oracle_result){
					writer.println("Test "+(i+1)+" Fail! RSP="+R+" SO2="+S+" NO2="+N+" O3="+O+" - IUT = "+iut_result+", Oracle = "+oracle_result);
					logger.warning("Test "+(i+1)+" Fail! RSP="+R+" SO2="+S+" NO2="+N+" O3="+O+" - IUT = "+iut_result+", Oracle = "+oracle_result);
				}else{
					writer.print("Test Case "+(i+1)+": Pass! RSP="+R+" SO2="+S+" NO2="+N+" O3="+O+" - IUT = "+iut_result+", Oracle = "+oracle_result);
				}
				randomTestBase.put(key, iut_result);
			}
			
		}
		
		writer.println("========== End of test ==========");
		writer.close();
	}

}
