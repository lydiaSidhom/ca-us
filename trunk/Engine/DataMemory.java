package Engine;
import java.util.HashMap;
import java.util.Map;


public class DataMemory {
	
	
	Map<String, String> myMemory = new HashMap<String, String>();

	public String readMemory(String address) {
		try {
			String value = myMemory.get(address);
			return value;
		
		} catch(Exception e) {
			System.out.println("Trying to Accessing wrong address No value ");
			return "Error";
		}
	}
	
	
	public void writeMemory(String address , String data) {
		try {
				myMemory.put (address , data);
				
		} 
		catch(Exception e) {
			System.out.println("Trying to Accessing wrong address");
		}
	}
}