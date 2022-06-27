package modelo;

public class Registro {
	String nombre,puntos,juego;
	int posicion;
	public Registro(String nombre,int posicion){
		this.nombre=nombre;
		this.posicion=posicion;
	}
	
	public Registro(String juego,String puntos){
		this.juego=juego;
		this.puntos=puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPuntos() {
		return puntos;
	}

	public int getPosicion() {
		return posicion;
	}

	public String getJuego() {
		return juego;
	}

}
