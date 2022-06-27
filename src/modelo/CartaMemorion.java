package modelo;

import java.io.File;
import javafx.scene.image.Image;

public class CartaMemorion {

	private int id;
	private Image adverso;
	private Image reverso;
	private int estado;

	public CartaMemorion(int id) {
		estado = 0;
		this.id = id;
		
		File archivo = new File("img/6.jpg");
		String efotos = archivo.toString();
		this.reverso = new Image(efotos);

		archivo = new File("img/" + id + ".jpg");
		efotos = archivo.toString();
		this.adverso = new Image(efotos);

	}

	public Image turn() {
		if (this.estado == 0) {
			return adverso;
		} else {
			estado = 0;
			return reverso;
		}
	}

	public Image getReverso() {
		return reverso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

}
