package EjercicioJPA;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Empleado implements Serializable {
	@Id
	@GeneratedValue
	private int idEmpleado;
	private String nombre;
	private String departamento;
	private String sede;
	private int edad;
	private double sueldo;
	
	public Empleado() {
	}

	public Empleado(int idEmpleado, String nombre, String departamento, String sede, int edad, double sueldo) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.departamento = departamento;
		this.sede = sede;
		this.edad = edad;
		this.sueldo = sueldo;
	}
	
	
}
