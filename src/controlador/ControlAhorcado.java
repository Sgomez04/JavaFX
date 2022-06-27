package controlador;

import javafx.scene.image.*;
import java.io.IOException;
import java.util.ArrayList;
import DataBase.ControlDeUsuarios;
import DataBase.GameDB;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Modeloahorcado;

public class ControlAhorcado {

	String[] word = {"perro", "gato", "girafa", "mono", "elefante", "ordenador", "television", "alfombra", "cerveza",
			"lampara", "botella", "banco", "griego", "egipcio", "español", "japones", "telefono", "libro", "vaso"};
	ArrayList<Button> botones = new ArrayList<Button>();
	ArrayList<ImageView> ividas = new ArrayList<ImageView>();
	ArrayList<Image> iahorcado = new ArrayList<Image>();
	int posicionV,indice;
	Modeloahorcado game;
	@FXML
	private Pane PInicio,PJuego,PWin,Plose,Pinstruccion;
	@FXML
	private GridPane vidas;
	@FXML
	private TextField palabra,prueba;
    @FXML
    private ImageView losemov,a1;
	@FXML
	private Button btInstruccion1,btlose2,btlose1,btWin2,btWin1,btA,btW,btInicio2,btInicio1;

	@FXML
	void click_btInicio1(ActionEvent event) {
		PInicio.setVisible(false);
		nuevoJuego();
	}

	@FXML
	void click_btInicio2(ActionEvent event) {
		PInicio.setVisible(false);
		Pinstruccion.setVisible(true);
	}
	
	@FXML
	void click_btSalir(ActionEvent event) throws IOException {
			
		Scene escenaM = EntreJuegos.getMenu();
		Stage appStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		appStage.setScene(escenaM);
		appStage.show();
}
		 
	@FXML
	void click_btWin1(ActionEvent event) {
		PWin.setVisible(false);
		nuevoJuego();
	}

	@FXML
	void click_btWin2(ActionEvent event) {
		PWin.setVisible(false);
		PInicio.setVisible(true);

	}

	@FXML
	void click_btlose1(ActionEvent event) {
		Plose.setVisible(false);
		nuevoJuego();

	}

	@FXML
	void click_btlose2(ActionEvent event) {
		Plose.setVisible(false);
		PInicio.setVisible(true);
	}

	@FXML
	void click_btInstruccion1(ActionEvent event) {
		Pinstruccion.setVisible(false);
		PInicio.setVisible(true);
	}

	@FXML
	void clickletra(ActionEvent event) throws Exception {
		Button evento = (Button) event.getSource();
		
		if (!game.youWin() && game.getFails() < 6) {
			botones.add(evento);
			char letras = evento.getText().toLowerCase().charAt(0);

			if (!game.checkLetter(letras)) {
				// ocultar imagen corazon
				ividas.get(posicionV--).setVisible(false);
				// cambiar imagen ahorcado
				a1.setImage(iahorcado.get(++indice));

			}
			palabra.setText(game.maskToString());

		}
		evento.setDisable(true);

		if (game.youWin()) {
			PJuego.setVisible(false);
			PWin.setVisible(true);
			puntos("+2");

		} else if (game.getFails() == 6) {
			PJuego.setVisible(false);
			Plose.setVisible(true);
			movMuñeco();
			puntos("-1");

		}
	}

	void imagenes() {
		String[] urlAhorcado = { "/img1/ahorcado_6.png", "/img1/ahorcado_5.png", "/img1/ahorcado_4.png",
				"/img1/ahorcado_3.png", "/img1/ahorcado_2.png", "/img1/ahorcado_1.png", "/img1/ahorcado_0.png" };
		
		// imagenes vidas
		for (int i = 0; i < 6; i++) {
			Image imagen = new Image("/img1/simbolo.png");
			ividas.add((ImageView) vidas.getChildren().get(i));
			ividas.get(i).setImage(imagen);
		}

		// imagenes ahorcado
		for (int i = 0; i < 7; i++) {
			Image imagen = new Image(urlAhorcado[i]);
			iahorcado.add(imagen);
		}
	}

	void nuevoJuego() {
		PJuego.setVisible(true);
		imagenes();

		// Vidas
		posicionV = 5;
		for (int i = 0; i < ividas.size(); i++) {
			ividas.get(i).setVisible(true);
		}

		//palabras();
		// Palabra oculta
		int i = (int) (Math.random() * word.length);
		game = new Modeloahorcado(word[i]);
		System.out.println(word[i]);
		palabra.setText(game.maskToString());

		// Imagen ahorcado
		indice = 0;
		a1.setImage(iahorcado.get(indice));

		// Letras visibles
		for (Button b : botones) {
			b.setDisable(false);
		}
		botones.clear();

	}

	void movMuñeco() {
		  // Create a circle
	    Arc arco = new Arc(80, 130, 200, 30, 280, 5);
	    arco.setFill(Color.WHITE);
	    arco.setStroke(Color.WHITE);
		
		 PathTransition pt = new PathTransition();
		    pt.setDuration(Duration.millis(4000));
		    pt.setPath(arco);
		    pt.setNode(losemov);
		    pt.setOrientation(
		      PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		    pt.setCycleCount(Timeline.INDEFINITE);
		    pt.setAutoReverse(true);
		    pt.play();
		
	}
	
	void puntos(String i) throws Exception {

		GameDB game = new GameDB("Ahorcado",i,EntreJuegos.getUser());
		ControlDeUsuarios juego = new ControlDeUsuarios();
		juego.setPuntos(game);
		
	}

}