package Engine;

/*
Author: Christine.

This class is the one where we will put mapping of most instructions to binary
till now it contains:-
1- Instructions.
2- Constuctor.
3- get type of instruction R for R-type.
						   I for I-type.
						   J for J-type.
4- startThis will result in the end with list of binary instruction (Not completed yet).
5- readInstructions read from file get list of word instructions.
6- checkOrgisfirst checks if first instruction is org.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Initializer {
	HashMap<String,String> Rinstructions, Iinstructions, Jinstructions, Registers;
	HashMap<String, Integer> labelValues;
	ArrayList<String> wordInstructions;
	ArrayList<String> binaryInstructions;
	int startingAddress;
	public Initializer() {
		wordInstructions = new ArrayList<String>();
		binaryInstructions = new ArrayList<String>();
		Rinstructions = new HashMap<String,String>();
		Iinstructions = new HashMap<String,String>();
		Jinstructions = new HashMap<String,String>();
		Registers = new HashMap<String,String>();
		labelValues = new HashMap<String, Integer>();
		fillall();
	}
	public void fillall() {
		Rinstructions.put("add", "100000");
		Rinstructions.put("sub", "100010");	
		Rinstructions.put("or", "100101");
		Rinstructions.put("nor", "100111");
		Rinstructions.put("slt", "101010");
		Rinstructions.put("sltu", "101011");
		Rinstructions.put("sll", "000000");
		Rinstructions.put("srl", "000010");
		Rinstructions.put("and", "100100");
		Rinstructions.put("jr", "001000");
		Iinstructions.put("addi", "001000");
		Iinstructions.put("sltui", "001011");
		Iinstructions.put("beq", "000100");
		Iinstructions.put("bne", "000101");
		Iinstructions.put("lw", "100011");
		Iinstructions.put("sw", "101011");
		Iinstructions.put("andi", "001100");
		Iinstructions.put("ori", "001101");
		Iinstructions.put("slti", "001010");
		Jinstructions.put("j", "000010");
		Jinstructions.put("jal", "000011");
		Registers.put("$zero", "00000");
		Registers.put("$at", "00001");
		Registers.put("$r0", "00010");
		Registers.put("$r1", "00011");
		Registers.put("$a0", "00100");
		Registers.put("$a1", "00101");
		Registers.put("$a2", "00110");
		Registers.put("$a3", "00111");
		Registers.put("$t0", "01000");
		Registers.put("$t1", "01001");
		Registers.put("$t2", "01010");
		Registers.put("$t3", "01011");
		Registers.put("$t4", "01100");
		Registers.put("$t5", "01101");
		Registers.put("$t6", "01110");
		Registers.put("$t7", "01111");
		Registers.put("$s0", "10000");
		Registers.put("$s1", "10001");
		Registers.put("$s2", "10010");
		Registers.put("$s3", "10011");
		Registers.put("$s4", "10100");
		Registers.put("$s5", "10101");
		Registers.put("$s6", "10110");
		Registers.put("$s7", "10111");
		Registers.put("$t8", "11000");
		Registers.put("$t9", "11001");
		Registers.put("$ra", "11111");
	}
	public void start(File f) throws Exception {
		readInstructions(f);
		checkOrgisfirst();
		for(int i=1; i<wordInstructions.size();i++) {
			String currentInstruction = wordInstructions.get(i);
			if(currentInstruction.contains(":")) {
				String label=currentInstruction.split(":")[0];
				int no= startingAddress+(i -1);
				labelValues.put(label, no);
				currentInstruction=currentInstruction.substring(label.length()+1);
			}
			String opcode=currentInstruction.split(" ")[0].replace(" ", "");
			if(opcode.equalsIgnoreCase("")) {
				opcode=currentInstruction.split(" ")[1].replace(" ", "");
			}
			currentInstruction=currentInstruction.substring(opcode.length()+1);
			currentInstruction=currentInstruction.replace(" ", "");
			String[] registersUsed=currentInstruction.split(",");
			for(int k=0;k<registersUsed.length;k++) {
				registersUsed[k]=registersUsed[k].replace(" ", "");
			}
			String type=typeOfInstruction(opcode);
			if(type.equalsIgnoreCase("R")) {
			  formRInstructions(opcode,registersUsed, i);	
			} else if(type.equalsIgnoreCase("I")) {
			  formIInstructions(opcode,registersUsed, i);
			} else {
				formJInstructions(opcode,registersUsed, i);
			}
		}
		
	}
	public void formRInstructions(String opcode, String[] registersUsed, int i) throws Exception {
		String result="000000";
		if(opcode.equalsIgnoreCase("sll")||opcode.equalsIgnoreCase("srl")) {
			String[]registers={registersUsed[0],registersUsed[1]};
			if(!(registersUsed.length==3 && checkIfValidRegisters(registers))) {
				throw new Exception("Error Wrong format of instructiona at line "+ (i+1));
			} else {
				result+=Registers.get(registersUsed[1]);
				result+="00000";
				result+=Registers.get(registersUsed[0]);
				result+=Registers.get(registersUsed[2]);
				result+=Rinstructions.get(opcode);
			}
		} else if(opcode.equalsIgnoreCase("jr")) {
			if(registersUsed.length!=1 || !registersUsed[0].equalsIgnoreCase("$ra")) {
				throw new Exception("Error Wrong format of instruction at line "+ (i+1));
			} else {
				result+=Registers.get(registersUsed[0]);
				result+="000000000000000";
				result+=Rinstructions.get(opcode);
			}
		} else {
			if(!(registersUsed.length==3 && checkIfValidRegisters(registersUsed))) {
				throw new Exception("Error Wrong format of instruction at line "+ (i+1));
			} else {
				result+=Registers.get(registersUsed[1]);
				result+=Registers.get(registersUsed[2]);
				result+=Registers.get(registersUsed[0]);
				result+="00000";
				result+=Rinstructions.get(opcode);
			}
		}
		binaryInstructions.add(result);
		
	}
	public void formIInstructions(String opcode, String[] registersUsed, int i) throws Exception {
		String result=Iinstructions.get(opcode);
		if(opcode.equalsIgnoreCase("lw") || opcode.equalsIgnoreCase("sw")) {
			String offset=registersUsed[1].split("\\(")[0];
			String register2=registersUsed[1].split("\\(")[1].substring(0, registersUsed[1].split("\\(")[1].length()-1);
			if(registersUsed.length!=2 || !checkIfValidRegisters(new String[]{registersUsed[0],register2})) {
				throw new Exception("Error wrong format of instruction at line "+ (i+1));
			} else {
				result+=Registers.get(register2);
				result+=Registers.get(registersUsed[0]);
				int constant=Integer.parseInt(offset);
				String xx=Integer.toBinaryString(constant);
				if(xx.length()>16) {
				   xx=xx.substring(xx.length()-16, xx.length());
				}
				for(int j=xx.length();j < 16. ;j++) {
					result+="0";
				}
				result+=xx;
			}
		} else {
			if((opcode.equalsIgnoreCase("beq") || opcode.equalsIgnoreCase("bne")) && labelValues.get(registersUsed[2])!=null) {
				result+=Registers.get(registersUsed[0]);
				result+=Registers.get(registersUsed[1]);
				String xx=Integer.toBinaryString(labelValues.get(registersUsed[2]));
				if(xx.length()>16) {
					   xx=xx.substring(xx.length()-16, xx.length());
					}
				for(int j=xx.length();j < 16. ;j++) {
					result+="0";
				}
				result+=xx;
			} else {
				result+=Registers.get(registersUsed[1]);
				result+=Registers.get(registersUsed[0]);
				int offset=Integer.parseInt(registersUsed[2]);
				String xx=Integer.toBinaryString(offset);
				if(xx.length()>16) {
					   xx=xx.substring(xx.length()-16, xx.length());
					}
				for(int j=xx.length();j < 16. ;j++) {
					result+="0";
				}
				result+=xx;
			}
		}
		binaryInstructions.add(result);
	}
	public void formJInstructions(String opcode, String[] registersUsed, int i) throws Exception {
		String result="";
		if(registersUsed.length!=1) {
			throw new Exception("Error wrong format of instruction at line "+ (i+1));		
		} else if(labelValues.get(registersUsed[0])!=null) {
			result=Jinstructions.get(opcode);
			int val=labelValues.get(registersUsed[0]);
			String xx=Integer.toBinaryString(val);
			if(xx.length()>26) {
				   xx=xx.substring(xx.length()-16, xx.length());
				}
			for(int j=xx.length();j < 26. ;j++) {
				result+="0";
			}
			result+=xx;
		} else {
			result=Jinstructions.get(opcode);
			int val=Integer.parseInt(registersUsed[0]);
			String xx=Integer.toBinaryString(val);
			if(xx.length()>26) {
				   xx=xx.substring(xx.length()-16, xx.length());
				}
			for(int j=xx.length();j < 26. ;j++) {
				result+="0";
			}
			result+=xx;
		}
		binaryInstructions.add(result);
	}
	public void readInstructions(File f) throws Exception {
		BufferedReader br= new BufferedReader(new FileReader(f));
		String instr="";
		while((instr=br.readLine()) != null) {
			wordInstructions.add(instr);
		}
	}
	public void checkOrgisfirst() throws Exception {
		String first=wordInstructions.get(0);
		String[] xx=first.split(" ");
		if(xx.length != 2 || !xx[0].equalsIgnoreCase("org") ) {
			throw new Exception("Should contain org in first line");
		} 
		try {
			this.startingAddress= Integer.parseInt(xx[1]);
		} catch(NumberFormatException e) {
			System.out.println("Must go to an address");
		}
	}
	
	
	public String typeOfInstruction(String opcode) throws Exception {
		if(Rinstructions.containsKey(opcode)) {
			return "R";
		} 
		if(Iinstructions.containsKey(opcode)) {
			return "I";
		} 
		if(Jinstructions.containsKey(opcode)) {
			return "J";
		} 
		throw new Exception("Invalid instruction Opcode");
	}
	public boolean checkIfValidRegisters(String[] registersUsed) {
		for(int i=0;i<registersUsed.length;i++) {
			if(!Registers.containsKey(registersUsed[i])) {
				return false;
			} 
		}
		return true;
	}
}
