package DataBase;

public class UserDB {

	private String nombre;
	private String contrase�a;
	
	public UserDB(String nombre, String contrase�a){
	     this.nombre = nombre;
	     this.contrase�a = contrase�a;

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
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

}
