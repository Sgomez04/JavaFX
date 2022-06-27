package controlador;

import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class EntreJuegos {
	private static Scene menu;
	private static String user;

	public EntreJuegos() {

	}

	public static void setScene(javafx.scene.input.MouseEvent event, Scene vistaJuego, String user) {
		EntreJuegos.menu = (Scene) ((MediaView) event.getSource()).getScene();
		Stage appStage = (Stage) ((MediaView) event.getSource()).getScene().getWindow();
		appStage.setScene(vistaJuego);
		appStage.show();
		EntreJuegos.user = user;

	}

	public static String getUser() {
		return user;
	}

	public static Scene getMenu() {
		return menu;
	}

}
