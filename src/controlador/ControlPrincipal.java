package controlador;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;
import DataBase.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import modelo.Registro;

public class ControlPrincipal {
	// Variables vista
	@FXML
	private Pane Pjuego, Plogin, Pregister, PConfirmacionA, PConfirmacionR, Cpuntos, Ppuntos, Pinicio, Pmenu,
			PConBorrar, PBorrar;
	@FXML
	private TextField TFusuario, tfNuevaC, tfRepetirC, tfNuevoU, tf_Posicion, tf_TPuntos, tfnombre, tfpuntos;
	@FXML
	private PasswordField TFpassword;
	@FXML
	private Label RCorrecto, RNoCorrecto1a, RNoCorrecto1b, RNoCorrecto2a, RNoCorrecto2b;
	@FXML
	private TableView<Registro> Tpartidas, Tjugadores;
	@FXML
	private TableColumn<Registro, String> juego, puntos1, usuarios, posicion;
	@FXML
	private ImageView fondo1a, fondo2, Isound1, Isound2;
	@FXML
	private AnchorPane juegos;
	@FXML
	private Button btComenzar, btBorrar;
	@FXML
	private MediaView musica;

	// Variables necesarias
	private Timer pausa;
	private int contador = 0, contSound = 0;
	private boolean sonido;

	// eventos
	@FXML
	void click_bComenzar(ActionEvent event) {
		Plogin.setVisible(true);
		fondo2.setVisible(true);
		Pinicio.setVisible(false);

		String[] img = { "/img2/pizarra.png", "/img2/proyector.gif" };
		for (int i = 0; i < 2; i++) {
			ImageView imagen = (ImageView) Pmenu.getChildren().get(i);
			Image ico = new Image(img[i]);
			imagen.setImage(ico);
		}
		fondo1a.setVisible(false);
	}

	@FXML
	void click_bBorrar(ActionEvent event) {
		fondo2.setVisible(true);
		PBorrar.setVisible(true);
		Pmenu.setVisible(false);
	}

	@FXML
	void clicl_BorrarCuenta(ActionEvent event) throws Exception {
		ControlDeUsuarios c = new ControlDeUsuarios();
		if (contador > 0)
			pausa.stop();

		if (c.borrarUsuario(tfnombre.getText().toString())) {
			PConBorrar.setVisible(true);
			PBorrar.setVisible(false);
			delay(true, "borrar");
			contador++;
		} else {
			System.out.println("no se pudo");
		}
	}

	@FXML
	void click_bPartidas(ActionEvent event) throws Exception {
		Cpuntos.setVisible(true);
		Pmenu.setVisible(false);
		fondo2.setVisible(true);
		registro();
	}

	@FXML
	void click_bRanking(ActionEvent event) {
		Ppuntos.setVisible(true);
		Pmenu.setVisible(false);
		fondo2.setVisible(true);
		Pinicio.setVisible(false);
		registro();
	}

	@FXML
	void click_juego(MouseEvent event) throws IOException {
		if (contador > 0)
			pausa.stop();

		MediaView evento = (MediaView) event.getSource();
		int id = Integer.parseInt(Character.toString(evento.getId().charAt(1)));
		if (id == 1) {

			juego(event, "/Vista/VistaAhorcado.fxml", id);

		} else if (id == 2) {

			juego(event, "/Vista/VistaMemorion.fxml", id);

		} else {
			juego(event, "/Vista/VistaMatematico.fxml", id);

		}

	}

	@FXML
	void clicl_bAtras(ActionEvent event) {
		Ppuntos.setVisible(false);
		Cpuntos.setVisible(false);
		Pmenu.setVisible(true);
		fondo2.setVisible(false);
		PBorrar.setVisible(false);
	}

	@FXML
	void click_Blogout(ActionEvent event) {
		Pmenu.setVisible(false);
		Plogin.setVisible(true);
		fondo2.setVisible(true);
		musica(0, sonido);
	}

	@FXML
	void click_BAceptar(ActionEvent event) throws Exception {
		if (contador > 0)
			pausa.stop();

		if(!tfNuevoU.getText().toString().equals("")&&!tfNuevaC.getText().toString().equals("")) {
		PConfirmacionR.setVisible(true);
		Pregister.setVisible(false);
		cambiarP("register");
		}
	}

	@FXML
	void click_BLogin(ActionEvent event) throws Exception {
		if (contador > 0)
			pausa.stop();

		if (!Pmenu.isVisible()) {
			cambiarP("login");
			Isound2.setImage(Isound1.getImage());
		}
	}

	@FXML
	void click_BVolver(ActionEvent event) {
		if (contador > 0 || contador > 0)
			pausa.stop();
		Plogin.setVisible(true);
		Pregister.setVisible(false);
	}

	@FXML
	void click_Bregistrarse(ActionEvent event) {
		if (contador > 0 || contador > 0)
			pausa.stop();
		Plogin.setVisible(false);
		Pregister.setVisible(true);
	}

	@FXML
	void click_sound(MouseEvent event) {
		Image sound;
		if (contSound == 0) {
			sound = new Image("/img2/muted.png");
			Isound1.setImage(sound);
			Isound2.setImage(sound);
			contSound++;
			sonido = true;
			musica = (MediaView) juegos.getChildren().get(3);
			musica.getMediaPlayer().setMute(true);
		} else {
			sound = new Image("/img2/sound.png");
			Isound1.setImage(sound);
			Isound2.setImage(sound);
			contSound--;
			sonido = false;
			musica.getMediaPlayer().setMute(false);
		}
	}

	@FXML
	void click_btSalir(ActionEvent event) {
		Plogin.setVisible(false);
		Pinicio.setVisible(true);
		btComenzar.setVisible(true);
		fondo1a.setVisible(true);
		fondo2.setVisible(false);
	}

	// Metodos necesario
	void delay(boolean r, String l) {

		pausa = new Timer(2500, new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (l.equals("login")) {
					Plogin.setVisible(true);
					PConfirmacionA.setVisible(false);
				}
				
				else if (l.equals("register")) {
					if (!r) {
						Pregister.setVisible(true);
						PConfirmacionR.setVisible(false);

					} else {
						Plogin.setVisible(true);
						Pregister.setVisible(false);
						PConfirmacionR.setVisible(false);
					}
				} else {
					PConBorrar.setVisible(false);
					Plogin.setVisible(true);

				}
			}
		});
		pausa.start();
	}

	public void juego(MouseEvent event, String vista, int i) throws IOException {
		String usuario = tfnombre.getText().toString().toLowerCase();
		AnchorPane nuevo = FXMLLoader.load(getClass().getResource(vista));
		Scene vistaJuego = new Scene(nuevo);
		EntreJuegos.setScene(event, vistaJuego, usuario);

		colocarImg(nuevo, i);

	}

	public void cambiarP(String o) throws Exception {
		ControlDeUsuarios controlUsers = new ControlDeUsuarios();

		if (o.equals("register")) {
			PConfirmacionR.setVisible(true);

			if (tfNuevaC.getText().toString().toLowerCase().equals(tfRepetirC.getText().toString().toLowerCase())) {
				UserDB usuario = new UserDB(tfNuevoU.getText().toLowerCase().toString(),
						tfRepetirC.getText().toString().toLowerCase());

				Pregister.setVisible(false);

				if (controlUsers.nuevoUsuario(usuario)) {
					RCorrecto.setVisible(true);
					RNoCorrecto1a.setVisible(false);
					RNoCorrecto1b.setVisible(false);
					RNoCorrecto2a.setVisible(false);
					RNoCorrecto2b.setVisible(false);
					delay(true, o);
					contador++;

				} else {
					RCorrecto.setVisible(false);
					RNoCorrecto1a.setVisible(false);
					RNoCorrecto1b.setVisible(false);
					RNoCorrecto2a.setVisible(true);
					RNoCorrecto2b.setVisible(true);
					delay(false, o);
					contador++;
				}
			} else {
				Plogin.setVisible(false);
				RCorrecto.setVisible(false);
				RNoCorrecto2a.setVisible(false);
				RNoCorrecto2b.setVisible(false);
				RNoCorrecto1a.setVisible(true);
				RNoCorrecto1b.setVisible(true);
				delay(false, o);
				contador++;
			}
		} else {
			Plogin.setVisible(false);
			UserDB usuario = new UserDB(TFusuario.getText().toString(), TFpassword.getText().toString().toLowerCase());
			if (!controlUsers.estaUsuario(usuario)) {
				PConfirmacionA.setVisible(true);
				delay(false, o);
				contador++;
			} else {
				Pmenu.setVisible(true);
				Plogin.setVisible(false);
				fondo2.setVisible(false);
				tfnombre.setText(TFusuario.getText().toString());
				TFusuario.setText("");
				TFpassword.setText("");
				musica(1, sonido);
			}

		}

	}

	public void colocarImg(AnchorPane root, int i) {
		ImageView imagen;
		Image ico;

		// Memorion
		if (i == 2) {
			String[] f = { "/img/fondo1.jpg", "/img/fondo2.jpg", "/img/menu2.png" };
			for (int j = 0; j < 3; j++) {
				ico = new Image(f[j]);
				imagen = (ImageView) root.getChildren().get(j);
				imagen.setImage(ico);
			}

			// Ahorcado
		} else if (i == 1) {

			ico = new Image("/img1/fondo.jpg");
			imagen = (ImageView) root.getChildren().get(0);
			imagen.setImage(ico);

			ico = new Image("/img1/ahorcado_0.png");
			Pane paneAhorcado = (Pane) root.getChildren().get(1);
			ImageView imgAhorcado = (ImageView) paneAhorcado.getChildren().get(4);
			imgAhorcado.setImage(ico);

		} else {

			// Matematico
			String[] url = { "/img3/fondo1.jpg", "/img3/fondo2.png", "/img3/fondo3.gif", "/img3/fondo6.png" };
			// fondos principales
			for (int j = 0; j < 2; j++) {
				ico = new Image(url[j]);
				imagen = (ImageView) root.getChildren().get(j);
				imagen.setImage(ico);
			}

			// fondos Pane
			Pane panel = (Pane) root.getChildren().get(2);
			for (int j = 0; j < 2; j++) {
				ico = new Image(url[j + 2]);
				imagen = (ImageView) panel.getChildren().get(j);
				imagen.setImage(ico);
			}
		}

	}

	public void musica(int i, boolean muted) {

		musica = (MediaView) juegos.getChildren().get(3);
		musica.getMediaPlayer().stop();
		Media media;
		MediaPlayer reproductor;

		if (i == 1) {
			media = new Media((this.getClass().getResource("/musc/juegos.mp3").toExternalForm()));

		} else {
			media = new Media((this.getClass().getResource("/musc/inicio.mp3").toExternalForm()));

		}
		reproductor = new MediaPlayer(media);
		musica.setMediaPlayer(reproductor);
		reproductor.setAutoPlay(true);
		reproductor.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		if (i == 1)
			reproductor.setVolume(0.2);

		if (muted)
			reproductor.setMute(true);
	}

	public void registro() {
		try {
			ControlDeUsuarios c = new ControlDeUsuarios();
			juego.setCellValueFactory(new PropertyValueFactory<Registro, String>("juego"));
			puntos1.setCellValueFactory(new PropertyValueFactory<Registro, String>("puntos"));
			posicion.setCellValueFactory(new PropertyValueFactory<Registro, String>("posicion"));
			usuarios.setCellValueFactory(new PropertyValueFactory<Registro, String>("nombre"));

			tf_TPuntos.setText(c.puntosTotales(tfnombre.getText().toString()));
			Tpartidas.setItems(c.getverPuntos(tfnombre.getText().toString()));
			Tjugadores.setItems(c.getRanking());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("no se pudo");
		}
	}
}
