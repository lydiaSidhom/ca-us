package Engine;

import java.io.File;

public class Main {
	Initializer ourinitializer;
	InstructionsMemory IM;
	ALU alu;
	RegistersFile registers;
	Register PC;
	DataMemory DM;
	ControlUnit CU;
	ALUControlUnit ALUCU;
	SignExtender signextender;
	Shifter shifter;
	ShifterJump shiftjump;
	ALUAdder increment4,branchadder;
	MUX2to1 ALUsrc, branch;
	MUX4to1 MemtoRegister,RegDestination, JumpMux;
	public Main() {
		ourinitializer=new Initializer();
		try {
			ourinitializer.start(new File("inprogram.in"));
			IM= new InstructionsMemory(ourinitializer.startingAddress, ourinitializer.binaryInstructions);
			alu= new ALU();
			registers= new RegistersFile();
			DM= new DataMemory();
			CU = new ControlUnit();
			ALUCU = new ALUControlUnit();
			signextender = new SignExtender();
			shiftjump = new ShifterJump();
			increment4 = new ALUAdder();
			branchadder = new ALUAdder();
			ALUsrc = new MUX2to1();
			branch = new MUX2to1();
			MemtoRegister = new MUX4to1();
			RegDestination = new MUX4to1();
			JumpMux = new MUX4to1();
			PC = new Register("PC");
			shifter= new Shifter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void simulate() {
		String xx=Integer.toBinaryString(ourinitializer.startingAddress);
		String input="";
		for(int i=xx.length();i<32;i++) {
			input+="0";
		}
		input+=xx;
		try {
			PC.setValue(input);
			IM.printall();
			while(Long.parseLong(PC.getValue(),2)<ourinitializer.startingAddress+ourinitializer.binaryInstructions.size()*4) {
				System.out.println(Long.parseLong(PC.getValue(),2));
				
				String instruction=IM.getInstructionAt(Long.parseLong(PC.getValue(),2));	
				
				String opcode= instruction.substring(0, 6);
				System.out.println(opcode);
				String rs= instruction.substring(6,11);
				System.out.println(rs);
				String rt= instruction.substring(11, 16);
				System.out.println(rt);
				String rd= instruction.substring(16, 21);
				System.out.println(rd);
				String shamount= instruction.substring(21, 26);
				System.out.println(shamount);
				String funcode= instruction.substring(26, 32);
				System.out.println(funcode);
				CU.setControls(opcode, funcode);
				System.out.println("ALUOP0 "+CU.ALUOp0);
				System.out.println("ALUOP1 "+CU.ALUOp1);
				System.out.println("ALUOP2 "+CU.ALUOp2);
				System.out.println("ALUSrc "+CU.ALUSrc);
				System.out.println("BranchEq "+CU.BranchEQ);
				System.out.println("BranchNeq "+CU.BranchNE);
				System.out.println("Jump0 "+CU.Jump0);
				System.out.println("Jump1 "+CU.Jump1);
				System.out.println("Memread "+CU.MemRead);
				System.out.println("MemReg0 "+CU.MemtoReg0);
				System.out.println("MemReg1 "+CU.MemtoReg1);
				System.out.println("MemWrite "+CU.MemWrite);
				System.out.println("Regdst0 "+CU.RegDst0);
				System.out.println("Regdst1 "+CU.RegDst1);
				System.out.println("Regwrite "+CU.RegWrite);
		
				ALUCU.setALUopcode(CU.ALUOp2+""+CU.getALUOp1()+""+CU.getALUOp0());
			
				ALUCU.setFunct(funcode);
				ALUCU.generateALUFunction();
				System.out.println(ALUCU.getResultALUFuncion());
				signextender.setInput(rd+""+shamount+funcode);
				signextender.ExtendSign();
				registers.setAddress1(rs);
				registers.setAddress2(rt);
				registers.read();
				ALUsrc.setMUXValues(registers.outputRegister2, signextender.getOutput(), CU.getALUSrc());
				alu.setReadInput1(registers.outputRegister1);
				alu.setReadInput2(ALUsrc.output);
				alu.setALUFunction(ALUCU.getResultALUFuncion());
				alu.doOperation();
				String dataread="";
				if(CU.getMemRead()==1) {
					dataread=DM.readMemory(alu.getALUResult());
					System.out.println("data read "+dataread );
				}
				System.out.println("IS PC incremented"+ PC.incrementBy4());
				String PCincremented=PC.incrementBy4();
				MemtoRegister.setMUXValues(CU.MemtoReg1, CU.MemtoReg0, alu.getALUResult(), dataread, PCincremented, "");
				if(CU.getMemWrite()==1) {
					DM.writeMemory(alu.getALUResult(), registers.outputRegister2);
				}
				RegDestination.setMUXValues(CU.getRegDst1(), CU.getRegDst0(), rt,rd , Integer.toBinaryString(31), "");
				System.out.println(signextender.getOutput());
				shifter.setInput(signextender.getOutput());
				shifter.setShiftingValue("00000000000000000000000000000010");
				shifter.shift(0);
				System.out.println("PC Value "+PC.getValue()+" value of 4 "+Integer.toBinaryString(4));
				increment4.add(PC.getValue(), Integer.toBinaryString(4));
				System.out.println("increment4 "+increment4.getOutput());
				System.out.println("shifter "+shifter.getOutput());
				branchadder.add(increment4.getOutput(), shifter.getOutput());
				int selectbranch=0;
				if(CU.getBranchEQ()==1 && alu.ALUZero || CU.getBranchNE()==1 && !alu.ALUZero) {
					selectbranch=1;
				}
				branch.setMUXValues(increment4.getOutput(), branchadder.getOutput(), selectbranch);
				shiftjump.shiftLeftJump(rs+rt+rd+shamount+funcode);
				String madeAddress=increment4.getOutput().substring(0,4)+shiftjump.output;
				JumpMux.setMUXValues(CU.getJump1(), CU.getJump0(), branch.output, madeAddress, registers.outputRegister1, "");
				registers.setWritingSignal(CU.RegWrite);
				registers.setWriteAddress(RegDestination.output);
				registers.setData(MemtoRegister.output);
				registers.write();
				System.out.println("We reached here");
				System.out.println("Jump address "+JumpMux.output);
				PC.setValue(JumpMux.output);
				System.out.println("PC value"+PC.getValue());
				System.out.println("Register Prints");
				registers.printall();
				System.out.println("Memory Prints");
				DM.printContents();
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] arg) throws Exception {
		File infile=new File("inprogram.in");
		Main xx= new Main();
		xx.simulate();
		
	}
}
