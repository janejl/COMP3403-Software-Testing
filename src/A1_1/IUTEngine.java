package A1_1;

public class IUTEngine {

	public int calculateIndex(double a, double b, double c, double d){
		if(a>100.0){
			return 1;
		}else if((b+4*c+2*d*d) > 3.0){
			return 2;
		}
		return 3;
	};
}
