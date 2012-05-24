package Engine;

public class ALU {
	String readInput1;
	String readInput2;
	String ALUFunction;
	String ALUResult;
	int operation;
	boolean ALUZero;
	
	static String AND 			= "0000";
	static String OR 			= "0001";
	static String ADD 			= "0010";
	static String SUBTRACT 		= "0110";
	static String SETONLESSTHAN = "0111";
	static String NOR 			= "1100";

	public ALU() {
		
	}

	public void doOperation() throws Exception {
		if(readInput1==null||readInput2==null||readInput1.length()<32 || readInput2.length()<32){
			throw new Exception("Wrong Input !!");
		}
		switch(operation){
		case 0 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) + Integer.parseInt(readInput2,2));break;
		case 1 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) | Integer.parseInt(readInput2,2));break;
		case 2 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) & Integer.parseInt(readInput2,2));break;
		case 3 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) - Integer.parseInt(readInput2,2));break;
		case 4 : ALUResult = Integer.toBinaryString((Integer.parseInt(readInput1,2) < Integer.parseInt(readInput2,2))?1:0);break;
		case 5 : ALUResult = Integer.toBinaryString(~((Integer.parseInt(readInput1,2) | Integer.parseInt(readInput2,2))));break;
		default : throw new Exception("Operation Failure !!");
		}
		if(ALUResult.length()>32){
			ALUResult.substring(1);
		}
		if(Integer.parseInt(ALUResult,2) == 0){
			ALUZero =true;
		}else{
			ALUZero = false;
		}
	}
	
	public void doOperation(String readInput1,String readInput2,int operation) throws Exception {
		if(readInput1==null||readInput2==null||readInput1.length()<32 || readInput2.length()<32){
			throw new Exception("Wrong Input !!");
		}
		switch(operation){
		case 0 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) + Integer.parseInt(readInput2,2));break;
		case 1 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) | Integer.parseInt(readInput2,2));break;
		case 2 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) & Integer.parseInt(readInput2,2));break;
		case 3 : ALUResult = Integer.toBinaryString(Integer.parseInt(readInput1,2) - Integer.parseInt(readInput2,2));break;
		case 4 : ALUResult = Integer.toBinaryString((Integer.parseInt(readInput1,2) < Integer.parseInt(readInput2,2))?1:0);break;
		case 5 : ALUResult = Integer.toBinaryString(~((Integer.parseInt(readInput1,2) | Integer.parseInt(readInput2,2))));break;
		default : throw new Exception("ERROR !!!");
		}
		if(ALUResult.length()>32){
			ALUResult.substring(1);
		}
		
	}

	public void setReadInput1(String input) {
		readInput1 = input;
	}

	public void setReadInput2(String input) {
		readInput2 = input;
	}
	
	public void setALUFunction(String input) {
		ALUFunction = input;
		if(ALUFunction.equals(AND)){
			operation = 0;
		}
		if(ALUFunction.equals(OR)){
			operation = 1;
		}
		if(ALUFunction.equals(ADD)){
			operation = 2;
		}
		if(ALUFunction.equals(SUBTRACT)){
			operation = 3;
		}
		if(ALUFunction.equals(SETONLESSTHAN)){
			operation = 4;
		}
		if(ALUFunction.equals(NOR)){
			operation = 5;
		}
		
	}

	public String getALUResult() {
		return ALUResult;
	}
}
