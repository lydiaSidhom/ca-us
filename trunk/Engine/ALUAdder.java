public class ALUAdder {
	
	String input1;
	String input2;
	String output;
	
	public ALUAdder() {
	}
	
	public void add(String in1,String in2){	
		
		this.input1 = in1;
		this.input2 = in2;
		this.output = Long.toBinaryString(Long.parseLong(input1, 2) + Long.parseLong(input1, 2));
	}

	public getOutput() {
		return this.output;
	}

}