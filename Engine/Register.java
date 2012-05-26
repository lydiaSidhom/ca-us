package Engine;

public class Register {
	String name;
	String value;
	public Register(String name){
		this.name = name;
		this.value = "00000000000000000000000000000000";
	}
	
	public void setValue(String input) throws Exception{
		if(input.length() ==32){
			this.value = input;
		}else{
			throw new Exception("Wron input into register");
		}
	}
	public String getValue(){
		return  this.value;
	}
	public void increment(){
		value = Long.toBinaryString((Long.parseLong(value, 2) + 1));
	}
	public void clear(){
		value =  "00000000000000000000000000000000";
	}
	public String incrementBy4() {
		return Long.toBinaryString((Long.parseLong(value, 2) + 4));
	}
}
