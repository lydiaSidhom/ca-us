package Engine;
public class ALUAdder {
	
	String input1;
	String input2;
	String output;
	
	public ALUAdder() {
	}
	
	public void add(String in1,String in2){	
		
		this.input1 = in1;
		this.input2 = in2;
		String xx = Long.toBinaryString(Long.parseLong(input1, 2) + Long.parseLong(input2, 2));
		this.output="";
		for(int i=xx.length();i<32;i++) {
			this.output+="0";
		}
		this.output+=xx;
	}

	public String getOutput() {
		if(output.length()>32) {
			output=output.substring(output.length()-32);
		}
		return this.output;
	}

}