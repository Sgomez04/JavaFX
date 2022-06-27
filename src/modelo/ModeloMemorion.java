package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class ModeloMemorion {

	private int fails;

	private ArrayList<CartaMemorion> fotos;

	public ModeloMemorion() {
		fails = 0;
		fotos = new ArrayList<CartaMemorion>();

		for (int i = 1; i <= 5; i++) {
			fotos.add(new CartaMemorion(i));
			fotos.add(new CartaMemorion(i));
		}

		Collections.shuffle(fotos);

	}

	public boolean comparaF(int f1, int f2) {

		if (f1 == f2)
			return true;
		else {
			fails++;
			return false;
		}
	}

	public int getFails() {
		return fails;
	}

	public ArrayList<CartaMemorion> getFotos() {
		return fotos;
	}

}