package controlador;

import modelo.*;
import javax.swing.Timer;

import DataBase.ControlDeUsuarios;
import DataBase.GameDB;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControlMemorion {

	// propiedades necesarias
	private ArrayList<ImageView> ividas = new ArrayList<ImageView>(), // ArrayList vidas
			fotoSe = new ArrayList<ImageView>(), // ArrayList fotos elegidas
			imageViews = new ArrayList<ImageView>(), // ArrayList ImageView creadas en scenebuilder
			imgFondos = new ArrayList<ImageView>();
	private ModeloMemorion juego; // modelo
	private ImageView evento; // recoger evento
	private int indice, posicionV;// Indice numero fotos elegidas | indice posicion de la vida | id fotos
	private int[] fotosID = new int[2]; //id de las imagenes para compararlas
	private Timer pausa;

	// propiedades conectoras con scenebuilder
	@FXML
	private Button btresultado, btInicio1, btInicio2, btinstrucciones, btwin_loose1, btwin_loose2, btatras;
	@FXML
	private Label labelvidas, labelnombre, LWin, LLoose;
	@FXML
	private Pane Pmenu, Pjuego, Pinstrucciones, PWin_Loose;
	@FXML
	private TextField fieldcuadro;
	@FXML
	private GridPane GPvidas;
	final PauseTransition pt = new PauseTransition(Duration.millis(6000));

	// eventos

	@FXML
	void click_btInicio3(ActionEvent event) {
		
		Scene escenaM = EntreJuegos.getMenu();
		Stage appStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		appStage.setScene(escenaM);
		appStage.show();
		
	}
	
	@FXML
	void click_btatras(ActionEvent event) {
		Pjuego.setVisible(false);
		PWin_Loose.setVisible(true);
		//btatras.setDisable(true);
		btatras.setVisible(false);
		labelvidas.setVisible(true);
		labelnombre.setVisible(true);
		fieldcuadro.setVisible(true);
		GPvidas.setVisible(true);
	}

	@FXML
	void click_btresultado(ActionEvent event) {
		PWin_Loose.setVisible(false);
		Pjuego.setVisible(true);
		btatras.setDisable(false);
		btatras.setVisible(true);
		labelvidas.setVisible(false);
		labelnombre.setVisible(false);
		fieldcuadro.setVisible(false);
		GPvidas.setVisible(false);

		for (int i = 0; i < juego.getFotos().size(); i++) {
			juego.getFotos().get(i).setEstado(0);
			imageViews.get(i).setImage(juego.getFotos().get(i).turn());

		}
		juego.getFotos().clear();
	}

	@FXML
	void click_btInicio1(ActionEvent event) {
		Pmenu.setVisible(false);
		nuevoJuego();
	}

	@FXML
	void click_btInicio2(ActionEvent event) {
		Pmenu.setVisible(false);
		Pinstrucciones.setVisible(true);
	}

	@FXML
	void click_btinstrucciones(ActionEvent event) {
		Pinstrucciones.setVisible(false);
		Pmenu.setVisible(true);
	}

	@FXML
	void click_btwin_loose1(ActionEvent event) {
		PWin_Loose.setVisible(false);
		nuevoJuego();
	}

	@FXML
	void click_btwin_loose2(ActionEvent event) {
		PWin_Loose.setVisible(false);
		Pmenu.setVisible(true);
	}

	@FXML
	void click_foto(MouseEvent event) throws Exception {
		evento = (ImageView) event.getSource();
		fotoSe.add(evento);

		// indices del ArrayList de Imagenes (juego.getFotos())
		fotosID[indice] = juego.getFotos().get(Integer.parseInt(Character.toString(evento.getId().toLowerCase().charAt(1)))).getId();
		// cambiar imagen
		evento.setImage(juego.getFotos().get(Integer.parseInt(Character.toString(evento.getId().toLowerCase().charAt(1)))).turn());

		// foto 1
		if (indice == 0) {
			fotoSe.get(indice).setDisable(true);
			indice++;

			// foto 2
		} else {
			fotoSe.get(indice).setDisable(true);

			// comparar fotos

			// fallo
			if (!juego.comparaF(fotosID[indice-1], fotosID[indice])) {

				// dar vuelta cartas
				for (int i = 0; i < fotoSe.size(); i++) {
					juego.getFotos().get(i).setEstado(1);
					
					if (i == 1) {
						delay(i);
					}
					fotoSe.get(i).setDisable(false);
				}

				// ocultar imagen corazon
				ividas.get(posicionV--).setVisible(false);

				
			}
			// acierto
			else {
				nCuadros(fotosID[indice]);
			}

			fotoSe.clear();
			indice = 0;
		}

		if (youWin()) {
			Pjuego.setVisible(false);
			PWin_Loose.setVisible(true);
			LLoose.setVisible(false);
			LWin.setVisible(true);
			puntos("2");
		}

		else if (juego.getFails() == 4) {
			Pjuego.setVisible(false);
			PWin_Loose.setVisible(true);
			LLoose.setVisible(true);
			LWin.setVisible(false);
			puntos("-1");
		}
	}

	// metodos necesarios
	public boolean youWin() {
		for (int i = 0; i < juego.getFotos().size(); i++) {
			if (!imageViews.get(i).isDisable())
				return false;
		}
		return true;
	}

	void nuevoJuego() {
		Pjuego.setVisible(true);
		juego = new ModeloMemorion();
		fotoSe = new ArrayList<ImageView>();
		indice = 0;

		// Fondos de los PANE
		imagenes();

		// Rellenar arrayList ividas(vidas) y ArrayList imageViews(cuadros)
		arrayImageViews();

		// Reverso cuadros
		for (int i = 0; i < juego.getFotos().size(); i++) {
			juego.getFotos().get(i).setEstado(1);
			imageViews.get(i).setImage(juego.getFotos().get(i).turn());
			imageViews.get(i).setDisable(false);
			// Rellenar Array de adverso
			juego.getFotos().get(i).setEstado(0);
		}

		// Vidas visibles
		posicionV = 3;
		for (int i = 0; i < ividas.size(); i++) {
			ividas.get(i).setVisible(true);
		}
	}

	void arrayImageViews() {
		ImageView img;

		for (int i = 0; i < 4; i++) {
			img = (ImageView) GPvidas.getChildren().get(i);
			ividas.add(img);
			Image vida = new Image("/img/vidas.png");
			ividas.get(i).setImage(vida);
		}

		for (int i = 5; i < 15; i++) {
			img = (ImageView) Pjuego.getChildren().get(i);
			imageViews.add(img);
		}
	}

	void imagenes() {
		String[] urlfondos = { "/img/juego.jpg", "/img/Win_Loose.jpg" };

		// imagen fondo juego

		for (int i = 0; i < 2; i++) {
			Image imagen = new Image(urlfondos[i]);
			imgFondos.add((ImageView) Pjuego.getChildren().get(0));
			imgFondos.add((ImageView) PWin_Loose.getChildren().get(0));
			imgFondos.get(i).setImage(imagen);

		}
	}

	void nCuadros(int i) {

		String[] ncuadroStrings = { "La Gioconda - Leonardo da Vinci", "El grito - Edvard Munch",
				"Las Meninas - Diego Velázquez", "Saturno devorando a su hijo - Francisco de Goya",
				"La lechera - Johannes Vermeer" };
		fieldcuadro.setText(ncuadroStrings[i - 1]);
	}

	void delay(int i) {
		Pjuego.setDisable(true);
		indice = i;
		ImageView[] fotoSEaux = new ImageView[2];
		
		fotoSEaux[0] = fotoSe.get(0);
		fotoSEaux[1] = fotoSe.get(1);
		
		pausa = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				fotoSEaux[0].setImage(juego.getFotos().get(indice).turn());
				fotoSEaux[1].setImage(juego.getFotos().get(indice+1).turn());
				pausa.stop();
				Pjuego.setDisable(false);
			}
		});
	
		pausa.start();}

	void puntos(String i) throws Exception {

		GameDB game = new GameDB("Memorion",i,EntreJuegos.getUser());
		ControlDeUsuarios juego = new ControlDeUsuarios();
		juego.setPuntos(game);
		
	}
}
	
