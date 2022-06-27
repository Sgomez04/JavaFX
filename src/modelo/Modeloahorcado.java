
package modelo;


public class Modeloahorcado {

	private String word;
	private char[] mask;
	private int fails;
	
	public void setFails(int fails) {
		this.fails = fails;
	}

	public Modeloahorcado (String word) {
		this.word = word.toLowerCase();
		fails = 0;
		setMask();
		
	}
	
	private void setMask() {
		mask = new char[word.length()];
		for (int i=0;i< mask.length;i++)
			mask[i] = '_';
	}
	 public String maskToString() {
		 String m ="";
		 for (int i=0;i< mask.length;i++)
			 	m+= mask[i]+ " ";
		 return m;
	 }
	 public boolean youWin() {
		 for (int i=0;i< mask.length;i++) {
			 if(mask[i] != word.charAt(i))
				 return false;
		 }
		 return true;
	 }
	 public boolean checkLetter(char l) {
		 boolean esta = false;
		 for (int i=0;i< mask.length;i++) {
			 if(l == word.charAt(i)) {
				 mask[i] = l;
				 esta = true;	 
			 }
		 }
		 if (!esta) fails++;
		 return esta;
	 }
	 public int getFails() {
		 return fails;
	 }
	 
}
