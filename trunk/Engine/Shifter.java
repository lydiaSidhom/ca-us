package Engine;

public class Shifter {
	String input;
	String shiftingValue;
	String output;
	
	static int LEFT = 0;
	static int RIGHT = 1;
	
	public Shifter(){
		
	}
	public void setInput(String input) throws Exception {
		if (input.length() == 32) {
			input = input;
		}
		throw new Exception("Wrong Input");
	}
	public void setShiftingValue(String input) throws Exception {
		if (input.length() == 32) {
			shiftingValue = input;
		}
		throw new Exception("Wrong Input");
	}
	public String getOutput(){
		return output;
	}
	public void shift(int direction){
		if(direction ==0){
			output = Long.toBinaryString(((Long.parseLong(input,
					2) << Long.parseLong(shiftingValue, 2))));
		}else{
			output = Long.toBinaryString(((Long.parseLong(input,
					2) >> Long.parseLong(shiftingValue, 2))));
		}
	}
	
}
