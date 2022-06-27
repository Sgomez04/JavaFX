package modelo;

public class ModeloMatematico {
	private int dificultad;
	private String[] operacion = new String[6];
	private int[] rOperacion = new int[6];

	public ModeloMatematico(int dificultad) {
		this.dificultad = dificultad;
		operacion();
	}

	public void operacion() {
		int dg1, dg2;
		String[] calculo = { "+", "-", "*", "/" };

		for (int i = 0; i < 6; i++) {
		String o = calculo[(int)(Math.random() * 4)];
			
			if (dificultad == 1) {
				// 1 digito
				dg1 = (int) (Math.random() * 10 + 1);
				dg2 = (int) (Math.random() * 10 + 1);
			} else if (dificultad == 2) {
				// 2 digitos
				dg1 = (int) (Math.random() * 50 + 10);
				dg2 = (int) (Math.random() * 50 + 10);
			}

			else {
				// 3 digitos
				dg1 = (int) (Math.random() * 1000 + 100);
				dg2 = (int) (Math.random() * 1000 + 100);
			}
			
			
			if (dg1 < dg2) {
			operacion[i] = dg2 + " " +  o + " " + dg1;
			rOperacion[i] = resultado(dg2, o ,dg1);		
			} else {

			operacion[i] = dg1 + " " +  o + " " + dg2;
			rOperacion[i] = resultado(dg1, o ,dg2);		
			}
		}
	}



	public String[] getOperacion() {
		return operacion;
	}

	public int[] getrOperacion() {
		return rOperacion;
	}
	
	public int resultado(int n1, String o, int n2) {

		if (o.equals("*"))
		return n1 * n2;
		else if (o.equals("+"))
		return	n1 + n2;
		else if (o.equals("-"))
		return n1 - n2;
		else 
		return n1 / n2;
			
	}

}
