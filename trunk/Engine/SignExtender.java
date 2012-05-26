package Engine;

public class SignExtender {

	String input;
	String output;

	public SignExtender() {

	}

	public void setInput(String input) throws Exception {
		if (input.length() == 16) {
			this.input = input;
		} else {
		   throw new Exception("Wrong Input signExtender input");
		}
	}
	public String getOutput(){
		return output;
	}

	public void ExtendSign() throws Exception {
		if (input.length() == 16) {
			if (input.charAt(0) == '1') {
				output = "1111111111111111" + input;
			} else {
				output = "0000000000000000" + input;
			}
		} else {
			throw new Exception("Wrong Input signexrender extend");
		}
	}
}