package Engine;

public class ALU {
	String readInput1;
	String readInput2;
	String ALUFunction;
	String ALUResult;
	int operation;
	boolean ALUZero;

	static String AND = "0000";
	static String OR = "0001";
	static String ADD = "0010";
	static String SUBTRACT = "0110";
	static String SETONLESSTHAN = "0111";
	static String SETONLESSTHANUNSIGNED = "1000";
	static String NOR = "1100";
	static String SLL = "1010";
	static String SRL = "1001";

	public ALU() {

	}

	public void doOperation() throws Exception {
		if (readInput1 == null || readInput2 == null
				|| readInput1.length() < 32 || readInput2.length() < 32) {
			throw new Exception("Wrong Input !!");
		}
		switch (operation) {
		case 0:
			ALUResult = Long.toBinaryString(Long.parseLong(readInput1, 2)
					& Long.parseLong(readInput2, 2));
			break;
		case 1:
			ALUResult = Long.toBinaryString(Long.parseLong(readInput1, 2)
					| Long.parseLong(readInput2, 2));
			break;
		case 2:
			ALUResult = Long
			.toBinaryString((Long.parseLong(readInput1.charAt(0)=='1'?"-"+twoComplement(readInput1):readInput1, 2) + Long
					.parseLong(readInput2.charAt(0)=='1'?"-"+twoComplement(readInput2):readInput2, 2)));
			break;
		case 3:
			ALUResult = Long
			.toBinaryString((Long.parseLong(readInput1.charAt(0)=='1'?"-"+twoComplement(readInput1):readInput1, 2) - Long
					.parseLong(readInput2.charAt(0)=='1'?"-"+twoComplement(readInput2):readInput2, 2)));
			break;
		case 4:
			ALUResult = Long
					.toBinaryString((Long.parseLong(readInput1, 2) < Long
							.parseLong(readInput2, 2)) ? 1 : 0);
			break;
		case 5:
			ALUResult = Long
					.toBinaryString((Long.parseLong(readInput1.charAt(0)=='1'?"-"+twoComplement(readInput1):readInput1, 2) < Long
							.parseLong(readInput2.charAt(0)=='1'?"-"+twoComplement(readInput2):readInput2, 2)) ? 1 : 0);
			break;
		case 6:
			ALUResult = Long.toBinaryString(~((Long.parseLong(readInput1,
					2) | Long.parseLong(readInput2, 2))));
			break;
		case 7:
			ALUResult = Long.toBinaryString(((Long.parseLong(readInput1,
					2) << Long.parseLong(readInput2, 2))));
			break;
		case 8:
			ALUResult = Long.toBinaryString(((Long.parseLong(readInput1,
					2) >> Long.parseLong(readInput2, 2))));
			break;
		default:
			System.out.print("ALU Idle");
		}
		if (ALUResult.length() > 32) {
			ALUResult = ALUResult.substring(ALUResult.length()-32);
		}
		if (ALUResult.length() < 32) {
			for(int i =ALUResult.length();i<32;i++){
				ALUResult = 0+ALUResult;
			}
		}
		if (Long.parseLong(ALUResult, 2) == 0) {
			ALUZero = true;
		} else {
			ALUZero = false;
		}
	}

	public void doOperation(String readInput1, String readInput2, int operation)
			throws Exception {
		if (readInput1 == null || readInput2 == null
				|| readInput1.length() < 32 || readInput2.length() < 32) {
			throw new Exception("Wrong Input !!");
		}
		switch (operation) {
		case 0:
			ALUResult = Long.toBinaryString(Long.parseLong(readInput1, 2)
					+ Long.parseLong(readInput2, 2));
			break;
		case 1:
			ALUResult = Long.toBinaryString(Long.parseLong(readInput1, 2)
					| Long.parseLong(readInput2, 2));
			break;
		case 2:
			ALUResult = Long.toBinaryString(Long.parseLong(readInput1, 2)
					& Long.parseLong(readInput2, 2));
			break;
		case 3:
			ALUResult = Long.toBinaryString(Long.parseLong(readInput1, 2)
					- Long.parseLong(readInput2, 2));
			break;
		case 4:
			ALUResult = Long
					.toBinaryString((Long.parseLong(readInput1, 2) < Long
							.parseLong(readInput2, 2)) ? 1 : 0);
			break;
		case 5:
			ALUResult = Long
					.toBinaryString((Long.parseLong(readInput1.charAt(0)=='1'?"-"+twoComplement(readInput1):readInput1, 2) < Long
							.parseLong(readInput2.charAt(0)=='1'?"-"+twoComplement(readInput2):readInput2, 2)) ? 1 : 0);
			break;
		case 6:
			ALUResult = Long.toBinaryString(~((Long.parseLong(readInput1,
					2) | Long.parseLong(readInput2, 2))));
			break;
		case 7:
			ALUResult = Long.toBinaryString(((Long.parseLong(readInput1,
					2) << Long.parseLong(readInput2, 2))));
			break;
		case 8:
			ALUResult = Long.toBinaryString(((Long.parseLong(readInput1,
					2) >> Long.parseLong(readInput2, 2))));
			break;
		default:
			System.out.print("ALU Idle");
		}
		if (ALUResult.length() > 32) {
			ALUResult.substring(1);
		}

	}

	public void setReadInput1(String input) throws Exception{
		if (input.length()!=32)
			throw new Exception("Error Wrong Input");
		readInput1 = input;
	}

	public void setReadInput2(String input) throws Exception{
		if (input.length()!=32)
			throw new Exception("Error Wrong Input");
		readInput2 = input;
	}

	public void setALUFunction(String input) throws Exception{
		if (input.length()!=4)
			throw new Exception("Error Wrong Input");
		ALUFunction = input;
		if (ALUFunction.equals(AND)) {
			operation = 0;
			return;
		}
		if (ALUFunction.equals(OR)) {
			operation = 1;
			return;
		}
		if (ALUFunction.equals(ADD)) {
			operation = 2;
			return;
		}
		if (ALUFunction.equals(SUBTRACT)) {
			operation = 3;
			return;
		}
		if (ALUFunction.equals(SETONLESSTHAN)) {
			operation = 4;
			return;
		}
		if (ALUFunction.equals(SETONLESSTHANUNSIGNED)) {
			operation = 5;
			return;
		}
		if (ALUFunction.equals(NOR)) {
			operation = 6;
			return;
		}
		if (ALUFunction.equals(SLL)) {
			operation = 7;
			return;
		}
		if (ALUFunction.equals(SRL)) {
			operation = 8;
			return;
		}
		throw new Exception("Error in Alu Function");
	}

	public static String twoComplement(String s) {
		boolean found = false;
		String temp = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			if (found)
				temp = s.charAt(i) == '1' ? 0 + temp : '1' + temp;
			else if (s.charAt(i) == '1') {
				found = true;
				temp = 1 + temp;
			} else
				temp = 0 + temp;
		}
		return temp;
	}

	public String getALUResult() {
		return ALUResult;
	}

	public static void main(String[] args) {
		ALU TEST = new ALU();
		TEST.operation = 8;
		TEST.readInput1 = "11111111111111111111111111101110";
		TEST.readInput2 = "00000000000000000000000000000011";
		try {
			TEST.doOperation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TEST.ALUResult);
	}
}
