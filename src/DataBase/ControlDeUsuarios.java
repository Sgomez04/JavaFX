package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Registro;

public class ControlDeUsuarios extends DBConecction {
	public ControlDeUsuarios() throws Exception {

	}

	public boolean estaUsuario(UserDB usuario) {
		try {
			String query = "SELECT count(*) FROM usuario WHERE user_id = '" + usuario.getNombre() + "' AND password = '"
					+ usuario.getContraseña() + "';";
			Statement sentencia = this.getCon().createStatement();
			ResultSet resultado = sentencia.executeQuery(query);
			int i = 0;
			if (resultado.next())
				i = resultado.getInt(1);
			if (i != 0) {
				return true;
			}
			return false;
		}

		catch (Exception e) {
			System.out.println("Algo falló, contacta con el servicio técnico. Disculpa las molestias");
			return false;
		}

	}

	public boolean nuevoUsuario(UserDB usuario) throws SQLException {

		// comprobar nombre usuario repetido
		String query = "SELECT count(*) FROM usuario WHERE user_id = '" + usuario.getNombre() + "';";
		Statement sentencia2 = this.getCon().createStatement();
		ResultSet resultado = sentencia2.executeQuery(query);

		int i = 0;
		if (resultado.next()) {
			i = resultado.getInt(1);
			if (i == 0) {
				// Introducir nuevo usuario
				String st = "INSERT INTO usuario VALUES( '" + usuario.getNombre() + "' ,'" + usuario.getContraseña()
						+ "');";
				Statement sentencia = this.getCon().createStatement();
				sentencia.executeUpdate(st);
				
				return true;
			}
		} 
		return false;
	}

	public boolean setPuntos(GameDB juego) {

		// comprobar nombre usuario repetido
		try {
			// Introducir nuevo usuario
			String st = "INSERT INTO game VALUES( " + juego.getPuntos() + ",'" + juego.getJuego() + "','"
					+ juego.getUser() + "');";
			Statement sentencia = this.getCon().createStatement();
			sentencia.executeUpdate(st);
		
			return true;

		} catch (Exception e) {
			System.out.println("Algo fallo, contacta con el servicio tecnico. Error 0xc12578Df");
			return false;
		}
	}

	public boolean borrarUsuario(String nombre) {
		try {
			String st = "DELETE FROM usuario WHERE user_id = '" + nombre + "';";
			Statement sentencia = this.getCon().createStatement();
			sentencia.executeUpdate(st);
			st = "DELETE FROM game WHERE user = '" + nombre + "';";
			sentencia = this.getCon().createStatement();
			sentencia.executeUpdate(st);
			return true;
		}

		catch (Exception e) {
			System.out.println("Algo fallo, contacta con el servicio tecnico. Disculpa las molestias");
			return false;
		}
	}

	public ObservableList<Registro> getverPuntos(String usuario) throws Exception {

		ObservableList<Registro> lista = FXCollections.observableArrayList();
		String query = "SELECT `puntos`,`juego` FROM `game` WHERE user='" + usuario + "'";

		Statement sentencia = getCon().createStatement();
		ResultSet resultado = sentencia.executeQuery(query);
		String juego, puntos;

		while (resultado.next()) {
			juego = resultado.getString("juego");
			puntos = resultado.getString("puntos");
			lista.add(new Registro(juego, puntos));
		}
		
		return lista;
	}

	public ObservableList<Registro> getRanking() throws Exception {

		ObservableList<Registro> lista = FXCollections.observableArrayList();
		String query = "SELECT user, SUM(puntos) FROM game GROUP BY user ORDER BY SUM(puntos) DESC;";

		Statement sentencia = getCon().createStatement();
		ResultSet resultado = sentencia.executeQuery(query);
		String user;
		int posicion, contador = 1;

		while (resultado.next()) {
			user = contador++ + "º - " + resultado.getString("user");
			posicion = resultado.getInt("SUM(puntos)");
			lista.add(new Registro(user, posicion));
		}
		return lista;
	}

	public String puntosTotales(String nombre) throws Exception {
		String puntos = "";
		String query = "SELECT SUM(puntos) FROM `game` where user ='" + nombre + "'";
		Statement sentencia = getCon().createStatement();
		ResultSet resultado = sentencia.executeQuery(query);
		while (resultado.next()) {
			puntos = resultado.getString("SUM(puntos)");
		 
		}
		return puntos;
	}

}
