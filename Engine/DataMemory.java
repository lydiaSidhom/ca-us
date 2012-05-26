package Engine;
import java.util.HashMap;
import java.util.Map;


public class DataMemory {
	
	
	Map<String, String> myMemory = new HashMap<String, String>();

	public String readMemory(String address) {
		try {
			String value = myMemory.get(address);
			if(value== null) {
				value="";
				for(int i=0;i<32;i++) {
					value+="0";
				}
			}
			return value;
		
	}
	
	public void setData(String input)throws Exception{
		if(input.length() != 32)
			throw new Exception("Wrong Input");
			this.inputData = input;
		}
	public void setWriteAddress(String input)throws Exception{
		if(input.length() != 32)
			throw new Exception("Wrong Input");
			this.inputAddress = input;
		}
	public void setWritingSignal(boolean input){
		writeSignal = input;
	}
	public void setReadingSignal(boolean input){
		readSignal = input;
	}
	public void doDataMemoryAction(){
		if(writeSignal){
			myMemory.put (inputAddress , inputData);
			return;
		}
		if(readSignal){
			outData = myMemory.get (inputAddress);
			if(outData == null)
				outData = "00000000000000000000000000000000"; 
			return;
		}
	}
	public String getOutputData(){
		return outData;
	}
	public static void main(String [] args){
		DataMemory TEST = new DataMemory();
		TEST.myMemory.put("000000000000000000000000000000","11111111111111111111111111111111");
		String value =TEST.myMemory.get("000000000000000000000000000000");
		System.out.println(value);
	}
	
}