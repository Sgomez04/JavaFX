package Vista;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Image ico = new Image("/img2/icono.png");
			AnchorPane root = FXMLLoader.load(getClass().getResource("VistaPrincipal.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(ico);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			imagenes(root);
			videos(root);
			musica(root);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void imagenes(AnchorPane root) {
		Image ico;
		ImageView imagen;
		String[] url = { "/img2/fondo1.jpg", "/img2/fondo1a.gif", "/img2/fondo2.jpg","/img2/sound.png"};
		
		// fondos
		for (int i = 0; i < 3; i++) {
			ico = new Image(url[i]);
			imagen = (ImageView) root.getChildren().get(i);
			imagen.setImage(ico);
		}
		
		//Icono sonido
		 imagen = (ImageView)((Pane)root.getChildren().get(12)).getChildren().get(0);
		 imagen.setImage(ico = new Image(url[3]));

	}
	
	public void videos(AnchorPane root) {

				ArrayList<MediaView> juegos = new ArrayList<MediaView>();
				String[] enlaces = { "/vid/1.mp4", "/vid/2.mp4","/vid/3.mp4" };

				for (int i = 6; i <= 8; i++) {
					juegos.add((MediaView) ((Pane)root.getChildren().get(13)).getChildren().get(i));
				}

				for (int j = 0; j < juegos.size(); j++) {
					Media media = new Media((this.getClass().getResource(enlaces[j]).toExternalForm()));
					MediaPlayer player = new MediaPlayer(media);
					juegos.get(j).setMediaPlayer(player);
					player.setAutoPlay(true);
					player.setMute(true);
					player.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);

				}
	}

	public void musica(AnchorPane root) {
		MediaView view = (MediaView) root.getChildren().get(3);
		Media media = new Media((this.getClass().getResource("/musc/inicio.mp3").toExternalForm()));
		MediaPlayer musica = new MediaPlayer(media);
		view.setMediaPlayer(musica);	
		musica.play();
		musica.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		
	}
}
