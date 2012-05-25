package Engine;

import java.util.HashMap;

public class RegistersFile {
	HashMap<String,Register> Registers;
	String readAddress1;
	String readAddress2;
	String writeAddress;
	String inputData;
	String outputRegister1;
	String outputRegister2;
	boolean WriteSignal;
	
	public RegistersFile(){
		Registers.put("00000",new Register("$zero"));
		Registers.put("00001",new Register("$at"));
		Registers.put("00010",new Register("$r0"));
		Registers.put("00011",new Register("$r1"));
		Registers.put("00100",new Register("$a0"));
		Registers.put("00101",new Register("$a1"));
		Registers.put("00110",new Register("$a2"));
		Registers.put("00111",new Register("$a3"));
		Registers.put("01000",new Register("$t0"));
		Registers.put("01001",new Register("$t1"));
		Registers.put("01010",new Register("$t2"));
		Registers.put("01011",new Register("$t3"));
		Registers.put("01100",new Register("$t4"));
		Registers.put("01101",new Register("$t5"));
		Registers.put("01110",new Register("$t6"));
		Registers.put("01111",new Register("$t7"));
		Registers.put("10000",new Register("$s0"));
		Registers.put("10001",new Register("$s1"));
		Registers.put("10010",new Register("$s2"));
		Registers.put("10011",new Register("$s3"));
		Registers.put("10100",new Register("$s4"));
		Registers.put("10101",new Register("$s5"));
		Registers.put("10110",new Register("$s6"));
		Registers.put("10111",new Register("$s7"));
		Registers.put("11000",new Register("$t8"));
		Registers.put("11001",new Register("$t9"));
		Registers.put("11111",new Register("$ra"));
	}
	public void setAddress1(String input) throws Exception{
		if(input.length() != 5)
			throw new Exception("Wrong Input");
			this.readAddress1 = input;
		}
	public void setAddress2(String input) throws Exception{
		if(input.length() != 5)
			throw new Exception("Wrong Input");
			this.readAddress2 = input;
		}
	public void read() throws Exception{
		if (readAddress1.length() != 5 || readAddress2.length() !=5){
			throw new Exception("Wrong Address");
		}
		this.outputRegister1 = Registers.get(readAddress1).getValue();
		this.outputRegister2 = Registers.get(readAddress2).getValue();
	}
	public void setData(String input)throws Exception{
		if(input.length() != 32)
			throw new Exception("Wrong Input");
			this.inputData = input;
		}
	public void setWriteAddress(String input)throws Exception{
		if(input.length() != 5)
			throw new Exception("Wrong Input");
			this.writeAddress = input;
		}
	public void setWritingSignal(boolean input){
		WriteSignal = input;
	}
	public void write() throws Exception{
		if(this.WriteSignal){
			Register temp = Registers.get(writeAddress);
			temp.setValue(inputData);
			this.Registers.put(writeAddress, temp);
		}
	}
}
