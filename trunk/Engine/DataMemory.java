package Engine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class DataMemory {
	
	
	Map<String, String> myMemory = new HashMap<String, String>();
	ArrayList<String> addresses=new ArrayList<String>();

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
	
	
	public void writeMemory(String address , String data) {
		try {
			if(myMemory.get(address)==null) {
			    addresses.add(address);
			}
			    
				myMemory.put (address , data);
			
		} 
		catch(Exception e) {
			System.out.println("Trying to Accessing wrong address");
		}
	public void setWriteAddress(String input)throws Exception{
		if(input.length() != 32)
			throw new Exception("Wrong Input");
			this.inputAddress = input;
		}
	public void setWritingSignal(boolean input){
		writeSignal = input;
	}
	public void printContents() {
		for(int i=0;i<addresses.size();i++) {
			System.out.println("Address: "+addresses.get(i)+" Value: "+myMemory.get(addresses.get(i)));
		}
		
	}
}