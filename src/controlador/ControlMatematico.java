package controlador;

import java.awt.event.ActionListener;
import javax.swing.Timer;
import DataBase.ControlDeUsuarios;
import DataBase.GameDB;
import modelo.ModeloMatematico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControlMatematico {

	@FXML
	private Pane Pmenu, Pjuego, PWin_Loose, Pdificultad, PInstrucciones;
	@FXML
	private TextField tfOperacion, tfResultado, tfVidas;
	@FXML
	private Label Lwin, Llose;
	@FXML
	private Button btCorregir;

	private Timer pausa;
	private Image ico;
	private ImageView imageView;
	private int dificultad, vidas, contador, contPartida = 0;
	private String[] operacion;
	private int[] rOperacion;
	private ModeloMatematico operaciones;

	@FXML
	void click_btAtras(ActionEvent event) {
		Scene escenaM = EntreJuegos.getMenu();
		Stage appStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		appStage.setScene(escenaM);
		appStage.show();
	}

	@FXML
	void click_btCorregir(ActionEvent event) throws Exception {
		String[] imagen = { "/img3/bien.png", "/img3/mal.png" };

		if (!tfResultado.getText().toString().isEmpty()) {
			btCorregir.setDisable(true);

			if (contador > 0)
				pausa.stop();

			imageView.setVisible(true);
			
			acierto(imagen);

			if (contador == 6 || vidas == 0) {
				if (youWin()) {
					Pjuego.setVisible(false);
					PWin_Loose.setVisible(true);
					Lwin.setVisible(true);
					Llose.setVisible(false);
					puntos("+2");
				} else {
					Pjuego.setVisible(false);
					PWin_Loose.setVisible(true);
					Lwin.setVisible(false);
					Llose.setVisible(true);
					puntos("-1");
				}
			}



			tfResultado.setText("");
		}

	}

	@FXML
	void click_btDificultad(ActionEvent event) {
		Pdificultad.setVisible(false);
		Pjuego.setVisible(true);

		Button evento = (Button) event.getSource();
		String ldificultad = evento.getText().toString().toLowerCase();

		if (ldificultad.equals("fácil"))
			dificultad = 1;
		else if (ldificultad.equals("normal"))
			dificultad = 2;
		else
			dificultad = 3;

		System.out.println(dificultad);
		Njuego();

	}

	@FXML
	void click_btNjuego(ActionEvent event) {
		PWin_Loose.setVisible(false);
		Pmenu.setVisible(false);
		Pdificultad.setVisible(true);

	}

	@FXML
	void click_btInstrucciones(ActionEvent event) {
		Pmenu.setVisible(false);
		PInstrucciones.setVisible(true);

	}

	@FXML
	void click_btMenu(ActionEvent event) {
		PWin_Loose.setVisible(false);
		PInstrucciones.setVisible(false);
		Pmenu.setVisible(true);
	}

	public void Njuego() {
		String[] imagen = { "/img3/fondo5.png", "/img3/fondo4.png" };
		contador = 0;

		for (int i = 1; i < 3; i++) {
			imageView = (ImageView) Pjuego.getChildren().get(i);
			ico = new Image(imagen[i - 1]);
			imageView.setImage(ico);
		}

		operaciones = new ModeloMatematico(dificultad);
		operacion = operaciones.getOperacion();
		rOperacion = operaciones.getrOperacion();

		tfOperacion.setText(operacion[contador]);
		vidas = 3;
		tfVidas.setText("" + vidas);

		imageView = (ImageView) Pjuego.getChildren().get(0);
		imageView.setVisible(false);
		btCorregir.setDisable(false);

		if (contPartida > 0)
			pausa.stop();

		contPartida++;
	}

	public void acierto(String[] imagen) {

		if (tfResultado.getText().toString().matches(".*[a-z].* *")||tfResultado.getText().toString().equals(" ")) {
			delay(1);
		} else if (Integer.parseInt(tfResultado.getText().toString()) != rOperacion[contador]) {
			tfVidas.setText("" + --vidas);
			ico = new Image(imagen[1]);
			imageView.setImage(ico);
			contador++;
			delay(2);

		} else {
			ico = new Image(imagen[0]);
			imageView.setImage(ico);
			contador++;
			delay(2);
		}
	}

	public boolean youWin() {

		if (contador == 6 && vidas != 0) {
			return true;
		}
		return false;
	}

	void delay(int i) {
		if (contador < 6) {
			
		if (i == 1) {
			imageView.setVisible(false);
			btCorregir.setDisable(false);

		} else {
			pausa = new Timer(1000, new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					imageView.setVisible(false);
					tfOperacion.setText(operacion[contador]);
					btCorregir.setDisable(false);
				}
			});
			pausa.start();
		}
		}
	}

	void puntos(String i) throws Exception {

		GameDB game = new GameDB("Matematico", i, EntreJuegos.getUser());
		ControlDeUsuarios juego = new ControlDeUsuarios();
		juego.setPuntos(game);

	}

}
