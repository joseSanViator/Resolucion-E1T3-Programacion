package EjercicioJPA;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity

@NamedQueries
	({
		@NamedQuery
		(
				name="Empleado.buscarPorDepartamento",
				query="SELECT e FROM Empleado e WHERE e.departamento=:departamento"
		),
		@NamedQuery
		(
				name="Empleado.actualizarSede",
				query="UPDATE Empleado e SET e.sede=?1 WHERE e.idEmpleado=?2"
		),
		@NamedQuery
		(
				name="Empleado.actualizarSueldo",
				query="UPDATE Empleado e SET e.sueldo=e.sueldo +:porcentaje*e.sueldo WHERE "
						+ "e.departamento=:departamento AND e.sede=:sede"
		)
	})

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
	public Empleado(String nombre, String departamento, String sede, int edad, double sueldo) {
		this.nombre = nombre;
		this.departamento = departamento;
		this.sede = sede;
		this.edad = edad;
		this.sueldo = sueldo;
	}
	public Empleado(int idEmpleado, String nombre, String departamento, String sede, int edad, double sueldo) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.departamento = departamento;
		this.sede = sede;
		this.edad = edad;
		this.sueldo = sueldo;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", departamento=" + departamento
				+ ", sede=" + sede + ", edad=" + edad + ", sueldo=" + sueldo + "]";
	}
}
