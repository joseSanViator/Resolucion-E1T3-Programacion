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
}
