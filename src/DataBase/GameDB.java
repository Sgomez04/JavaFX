package DataBase;

public class GameDB {

	private String juego;
	private int puntos;
	private String user;
	
	public GameDB() {
		
	} 
		
	public GameDB(String juego, String puntos, String user) {

        this.juego = juego;
        this.puntos = Integer.parseInt(puntos);
        this.user = user;
	}

	public String getJuego() {
		return juego;
	}

	public int getPuntos() {
		return puntos;
	}


	public String getUser() {
		return user;
	}

}
