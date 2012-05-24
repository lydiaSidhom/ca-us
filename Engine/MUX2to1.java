package Engine;
/* 
Author: Christine.
MUX 2 to 1
create one using an empty instruction
want to get the output call it using setMUXValues method..
*/

public class MUX2to1 {
	String input1;
	String input2;
	int select;
	String output;
	public MUX2to1() {
		
	}
	public void setMUXValues(String inp1, String inp2, int select) {
		this.input1=inp1;
		this.input2=inp2;
		this.select=select;
		if(select==0) 
			this.output=inp1;
		else 
			this.output=inp2;
		
	}
}