package A1_2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

// a rough helper program for retrieving column data from fail cases in random testing.
public class Converter {

	public static void main(String[] args){
        PrintWriter writer = null;
        try {
			writer = new PrintWriter("convert_result.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        try{
			Scanner s = new Scanner(new FileReader("convert.txt"));
	        String line;
	        String R, S, N, O, IUT, Oracle;
	        String[] trim;
	        while(s.hasNext()){
	        	line = s.nextLine();
	        	System.out.println(line);
	        	trim = line.split(" ");
	        	for(int i=0; i<trim.length; ++i){
	        		if(trim[i].contains("RSP")){
	        			R = trim[++i];
//	        			writer.println(R);
	        		}else if(trim[i].contains("SO2")){
	        			S = trim[++i]; 
//	        			writer.println(S);
	        		}else if(trim[i].contains("NO2")){
	        			N = trim[++i]; 
//	        			writer.println(N);
	        		}else if(trim[i].contains("O3")){
	        			O = trim[++i]; 
//	        			writer.println(O);
	        		}else if(trim[i].contains("IUT")){
	        			IUT = trim[++i];
//	        			writer.println(IUT);
	        		}else if(trim[i].contains("Oracle")){
	        			Oracle = trim[++i];
	        			writer.println(Oracle);
	        		}
	        	}
	        }
	        writer.flush();
        }catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Convert done.");
	}
}
