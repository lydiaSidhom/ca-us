package Engine;

public class ControlUnit {
	int RegDst0;
	int RegDst1;
	int Branch;
	int MemRead;
	int MemtoReg0;
	int MemtoReg1;
	int ALUOp0;
	int ALUOp1;
	int ALUOp2;
	int MemWrite;
	int ALUSrc;
	int RegWrite;
	int Jump0;
	int Jump1;

	public ControlUnit() {
	}

	public void setControls(String opcode, String function) {

		if (opcode.equals("000000") && function.equals("001000")) {
			//jr instruction
			RegDst0 = -1;
			RegDst1 = -1;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = -1;
			MemtoReg1 = -1;
			ALUOp0 = -1;
			ALUOp1 = -1;
			ALUOp2 = -1;
			MemWrite = 0;
			ALUSrc = -1;
			RegWrite = 0;
			Jump0 = 0;
			Jump1 = 1;
		} else if (opcode.equals("000000")) {
			//other R-format instruction
			RegDst0 = 1;
			RegDst1 = 0;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = 0;
			MemtoReg1 = 0;
			ALUOp0 = 0;
			ALUOp1 = 0;
			ALUOp2 = 0;
			MemWrite = 0;
			ALUSrc = 0;
			RegWrite = 1;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("000010")) {
			//jump instruction
			RegDst0 = -1;
			RegDst1 = -1;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = -1;
			MemtoReg1 = -1;
			ALUOp0 = -1;
			ALUOp1 = -1;
			ALUOp2 = -1;
			MemWrite = 0;
			ALUSrc = -1;
			RegWrite = 0;
			Jump0 = 1;
			Jump1 = 0;
		}
		if (opcode.equals("000011")) {
			//jump and link instruction
			RegDst0 = 0;
			RegDst1 = 1;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = 0;
			MemtoReg1 = 1;
			ALUOp0 = -1;
			ALUOp1 = -1;
			ALUOp2 = -1;
			MemWrite = 0;
			ALUSrc = -1;
			RegWrite = 1;
			Jump0 = 1;
			Jump1 = 0;
		}
		if (opcode.equals("000100")) {
			//beq
			RegDst0 = -1;
			RegDst1 = -1;
			Branch = 1;
			MemRead = 0;
			MemtoReg0 = -1;
			MemtoReg1 = -1;
			ALUOp0 = 1;
			ALUOp1 = 0;
			ALUOp2 = 0;
			MemWrite = 0;
			ALUSrc = 0;
			RegWrite = 0;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("000101")) {
			//bne
			RegDst0 = -1;
			RegDst1 = -1;
			Branch = 1;
			MemRead = 0;
			MemtoReg0 = -1;
			MemtoReg1 = -1;
			ALUOp0 = 1;
			ALUOp1 = 0;
			ALUOp2 = 0;
			MemWrite = 0;
			ALUSrc = 0;
			RegWrite = 0;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("001000")) {
			//addi
			RegDst0 = 0;
			RegDst1 = 0;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = 0;
			MemtoReg1 = 0;
			ALUOp0 = 0;
			ALUOp1 = 0;
			ALUOp2 = 1;
			MemWrite = 0;
			ALUSrc = 1;
			RegWrite = 1;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("001010")) {
			//slti
			RegDst0 = 0;
			RegDst1 = 0;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = 0;
			MemtoReg1 = 0;
			ALUOp0 = 0;
			ALUOp1 = 1;
			ALUOp2 = 0;
			MemWrite = 0;
			ALUSrc = 1;
			RegWrite = 1;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("001011")) {
			//sltui
			RegDst0 = 0;
			RegDst1 = 0;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = 0;
			MemtoReg1 = 0;
			ALUOp0 = 1;
			ALUOp1 = 1;
			ALUOp2 = 0;
			MemWrite = 0;
			ALUSrc = 1;
			RegWrite = 1;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("001100")) {
			//andi
			RegDst0 = 0;
			RegDst1 = 0;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = 0;
			MemtoReg1 = 0;
			ALUOp0 = 1;
			ALUOp1 = 0;
			ALUOp2 = 1;
			MemWrite = 0;
			ALUSrc = 1;
			RegWrite = 1;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("001101")) {
			//ori
			RegDst0 = 0;
			RegDst1 = 0;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = 0;
			MemtoReg1 = 0;
			ALUOp0 = 0;
			ALUOp1 = 1;
			ALUOp2 = 1;
			MemWrite = 0;
			ALUSrc = 1;
			RegWrite = 1;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("100011")) {
			//lw
			RegDst0 = 0;
			RegDst1 = 0;
			Branch = 0;
			MemRead = 1;
			MemtoReg0 = 1;
			MemtoReg1 = 0;
			ALUOp0 = 1;
			ALUOp1 = 1;
			ALUOp2 = 1;
			MemWrite = 0;
			ALUSrc = 1;
			RegWrite = 1;
			Jump0 = 0;
			Jump1 = 0;
		}
		if (opcode.equals("101011")) {
			//sw
			RegDst0 = -1;
			RegDst1 = -1;
			Branch = 0;
			MemRead = 0;
			MemtoReg0 = -1;
			MemtoReg1 = -1;
			ALUOp0 = 1;
			ALUOp1 = 1;
			ALUOp2 = 1;
			MemWrite = 1;
			ALUSrc = 1;
			RegWrite = 0;
			Jump0 = 0;
			Jump1 = 0;
		}
	}

	public int getRegDst0() {
		return RegDst0;
	}

	public int getRegDst1() {
		return RegDst1;
	}

	public int getBranch() {
		return Branch;
	}

	public int getMemRead() {
		return MemRead;
	}

	public int getMemtoReg0() {
		return MemtoReg0;
	}

	public int getMemtoReg1() {
		return MemtoReg1;
	}

	public int getALUOp0() {
		return ALUOp0;
	}

	public int getALUOp1() {
		return ALUOp1;
	}

	public int getMemWrite() {
		return MemWrite;
	}

	public int getALUSrc() {
		return ALUSrc;
	}

	public int getRegWrite() {
		return RegWrite;
	}

	public int getJump0() {
		return Jump0;
	}

	public int getJump1() {
		return Jump1;
	}
}
