package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="persona")
public class Persona {
	
	public Persona(String nombre, String apellido, String fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPersona")
	int idPersona;
	
	@Column(name="nombre")
	String nombre;
	
	@Column(name="apellido")
	String apellido;
	
	@Column(name="fechaNacimiento")
	String fechaNacimiento;
	
	public Persona()
	{
		
	}

	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getfechaNacimiento() {
		return fechaNacimiento;
	}
	public void setfechaNacimiento(String date) {
		this.fechaNacimiento = date;
	}




	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	
	

}
