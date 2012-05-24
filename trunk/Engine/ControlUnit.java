package Engine;

public class ControlUnit {
	boolean RegDst;
	boolean Branch;
	boolean MemRead;
	boolean MemtoReg;
	boolean ALUOp;
	boolean MemWrite;
	boolean ALUSrc;
	boolean RegWrite;
	public ControlUnit(String state) {
		RegDst=state.charAt(0)=='1'?true:false;
		Branch=state.charAt(1)=='1'?true:false;
		MemRead=state.charAt(2)=='1'?true:false;
		MemtoReg=state.charAt(3)=='1'?true:false;
		ALUOp=state.charAt(4)=='1'?true:false;
		MemWrite=state.charAt(5)=='1'?true:false;
		ALUSrc=state.charAt(6)=='1'?true:false;
		RegWrite=state.charAt(7)=='1'?true:false;

	}
}
