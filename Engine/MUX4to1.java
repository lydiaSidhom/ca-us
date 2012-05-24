package Engine;
/* 
Author: Christine.
MUX 4to 1
create one using an empty instruction
want to get the output call it using setMUXValues method..
where if we want to get no 2   10 ===> (select1=1), (select0=0) 
*/

public class MUX4to1 {
	int select1;
	int select0;
	String inpsrc0;
	String inpsrc1;
	String inpsrc2;
	String inpsrc3;
	String output;
	public MUX4to1() {
		
	}
	public void setMUXValues(int select1,int select0,String inpsrc0, String inpsrc1, String inpsrc2, String inpsrc3) {
		switch(select1) {
		case 1:output= (select0==1)?inpsrc3:inpsrc2; break;
		case 0:output= (select1==1)?inpsrc1:inpsrc0; break;
		}
	}
}
