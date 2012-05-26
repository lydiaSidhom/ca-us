package Engine;

public class ALUControlUnit {
	
	String ALUOpcode;
	String funct;
	String ResultALUFunction;
	
	static String RFORMAT 	= "000";
	static String BEQN 		= "001";
	static String SLTI 		= "010";
	static String SLTIU 	= "011";
	static String ADDI 		= "100";
	static String ANDI 		= "101";
	static String ORI 		= "110";
	static String LWSW 		= "111";
	
	static String ADDCODE 	= "010000";
	static String SUBCODE 	= "100010";
	static String SLLCODE 	= "000000";
	static String SRLCODE	= "000010";
	static String ANDCODE 	= "100100";
	static String ORCODE	= "100101";
	static String NORCODE	= "100111";
	static String SLTCODE	= "101010";
	static String SLTUCODE	= "101011";
	
	int ALUOpcodeId;
	int functId;
	
	public ALUControlUnit(){
	}
	
	public void generateALUFunction(){
		switch(ALUOpcodeId){
		case 0:{
			switch(functId){
			case 0:	ResultALUFunction = "0010";break;
			case 1: ResultALUFunction = "0110";break;
			case 2: ResultALUFunction = "1010";break;
			case 3: ResultALUFunction = "1001";break;
			case 4: ResultALUFunction = "0000";break;
			case 5: ResultALUFunction = "0001";break;
			case 6: ResultALUFunction = "1100";break;
			case 7: ResultALUFunction = "0111";break;
			case 8: ResultALUFunction = "1000";break;
			default: System.out.println("ALUControl Idle");
			}
		}break;
		case 1:ResultALUFunction = "0110";break;
		case 2:ResultALUFunction = "0111";break;
		case 3:ResultALUFunction = "1000";break;
		case 4:ResultALUFunction = "0010";break;
		case 5:ResultALUFunction = "0000";break;
		case 6:ResultALUFunction = "0001";break;
		case 7:ResultALUFunction = "0010";break;
		}
	}
	
	public void setALUopcode(String input) throws Exception{
		if(input.length()==3)
		ALUOpcode = input;
		else
		throw new Exception("Wrong Input to ALUOpcode !");
		if(input.equals(RFORMAT)){
			ALUOpcodeId = 0;
			return;
		}
		if(input.equals(BEQN)){
			ALUOpcodeId = 1;
			return;
		}
		if(input.equals(SLTI)){
			ALUOpcodeId = 2;
			return;
		}
		if(input.equals(SLTIU)){
			ALUOpcodeId = 3;
			return;
		}
		if(input.equals(ADDI)){
			ALUOpcodeId = 4;
			return;
		}
		if(input.equals(ANDI)){
			ALUOpcodeId = 5;
			return;
		}
		if(input.equals(ORI)){
			ALUOpcodeId = 6;
			return;
		}
		if(input.equals(LWSW)){
			ALUOpcodeId = 7;
			return;
		}
		throw new Exception("Wrong Input to ALUOpcode !");
	}
	
	public void setFunct(String input) throws Exception{
		if(ALUOpcode.equals("000")) {
		if(input.length()==6)
			funct = input;
			else {
				throw new Exception("Wrong Input to funct !");
	
			}
			if(input.equals(ADDCODE)){
				functId = 0;
				return;
			}
			if(input.equals(SUBCODE)){
				functId = 1;
				return;
			}
			if(input.equals(SLLCODE)){
				functId = 2;
				return;
			}
			if(input.equals(SRLCODE)){
				functId = 3;
				return;
			}
			if(input.equals(ANDCODE)){
				functId = 4;
				return;
			}
			if(input.equals(ORCODE)){
				functId = 5;
				return;
			}
			if(input.equals(NORCODE)){
				functId = 6;
				return;
			}
			if(input.equals(SLTCODE)){
				functId = 7;
				return;
			}
			if(input.equals(SLTUCODE)){
				functId = 8;
				return;
			}
		}
	}
	
	public String getResultALUFuncion(){
		return ResultALUFunction;
	}
	
	public static void main(String [] args){
		ALUControlUnit TEST = new ALUControlUnit();
		try {
			TEST.setALUopcode("000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			TEST.setFunct("000010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TEST.generateALUFunction();
		System.out.println(TEST.getResultALUFuncion());
	}
}
