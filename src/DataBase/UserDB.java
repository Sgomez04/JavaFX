package DataBase;

public class UserDB {

	private String nombre;
	private String contraseña;
	
	public UserDB(String nombre, String contraseña){
	     this.nombre = nombre;
	     this.contraseña = contraseña;

	}
	
	public UserDB(String nombre){
	     this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
