package Engine;

public class MUX {
	String input1;
	String input2;
	int select;
	String output;
	public MUX(String inp1, String inp2, int select) {
		this.input1=inp1;
		this.input2=inp2;
		this.select=select;
		if(select==0) 
			this.output=inp1;
		else 
			this.output=inp2;
		
	}
}
