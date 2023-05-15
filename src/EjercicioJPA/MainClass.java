package EjercicioJPA;

import java.util.Iterator;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainClass {

	public static void main(String[] args) {
		Empleado e1=new Empleado("Juana","informatica","Valladolid",30,2000);
		Empleado e2=new Empleado("Daniel","informatica","Valladolid",23,2500);
		Empleado e3=new Empleado("Juan Ramón","informatica","Burgos",21,1800);
		Empleado e4=new Empleado("Alejandro","ventas","Valladolid",28,1900);
		Empleado e5=new Empleado("Marta","ventas","Valladolid",26,2100);
		
		GestorEmpleados gestor=new GestorEmpleados();
		
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("gestorEmpleados");
		EntityManager em=factory.createEntityManager();

		gestor.insertar(em, e1);
		gestor.insertar(em, e2);
		gestor.insertar(em, e3);
		gestor.insertar(em, e4);
		gestor.insertar(em, e5);
		
		int numEmpleados=gestor.actualizarSueldo(em,"informatica","Valladolid", 15);
		System.out.println("Se ha actualizado el sueldo de "+numEmpleados+" empleados");
		
		List<Empleado> empleados=gestor.todosEmpleados(em, true);
		Iterator<Empleado> iteradorEmpleados=empleados.iterator();
		while(iteradorEmpleados.hasNext()) {
			System.out.println(iteradorEmpleados.next());
		}
		gestor.borrarEmpleado(em,empleados.get(0).getIdEmpleado());
	}
}
