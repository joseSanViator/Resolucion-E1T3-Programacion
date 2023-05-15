package EjercicioJPA;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class GestorEmpleados {
	public void insertar(EntityManager em, Empleado e) {
		EntityTransaction tran=em.getTransaction();
		tran.begin();
		em.persist(e);		
		tran.commit();
	}
	
	public boolean borrarEmpleado(EntityManager em,int idEmpleado) {
		String jpql="DELETE FROM Empleado e where e.idEmpleado="+idEmpleado;
		Query query=em.createQuery(jpql);
		EntityTransaction tran=em.getTransaction();
		tran.begin();
		int valor=query.executeUpdate();
		tran.commit();
		return valor==1?true:false;
	}
	//Método para borrar un objeto de la base de datos sin utilizar JPQL
	public boolean borrarEmpleadoV2(EntityManager em,int idEmpleado) {
		boolean borrado=false;
		Empleado e=new Empleado(idEmpleado,"pepito","informatica","valladolid",25,2000);
		Empleado encontrado=em.find(Empleado.class, e);
		if(encontrado!=null) {			
			EntityTransaction tran=em.getTransaction();
			tran.begin();
			//si es distinto de NULL, es que ha encontrado el objeto 
			em.remove(encontrado);
			borrado=true;
			tran.commit();
		}		
		return borrado;
	}
	
	public List<Empleado> buscarNombre(EntityManager em, String nombre){
		/*String jpql="SELECT e FROM Empleado e WHERE e.nombre LIKE '%"+nombre+"%'";
		Query query=em.createQuery(jpql);
		List<Empleado> empleados=query.getResultList();
		return empleados;*/
		String jpql="SELECT e FROM Empleado e WHERE e.nombre LIKE '%?1%'";
		Query query=em.createQuery(jpql);
		query.setParameter(1, nombre);
		List<Empleado> empleados=query.getResultList();
		return empleados;
	}
	public List<Empleado> buscarDepartamento(EntityManager em,String departamento){
		Query query=em.createNamedQuery("Empleado.buscarPorDepartamento");
		query.setParameter("departamento", departamento);
		List<Empleado> empleados=query.getResultList();
		return empleados;
	}
	
	public List<Empleado> todosEmpleados(EntityManager em, boolean ordenSueldo){
		String jpql="SELECT e FROM Empleado e ORDER BY e.sueldo ";
		if(ordenSueldo) {
			jpql+="desc";
		}else {
			jpql+="asc";
		}
		Query query=em.createQuery(jpql);
		List<Empleado> empleados=query.getResultList();
		return empleados;
	}
	
	public boolean actualizarSede(EntityManager em, int idEmpleado,String nuevaSede){
		Query query=em.createNamedQuery("Empleado.actualizarSede");
		query.setParameter(1, nuevaSede);
		query.setParameter(2, idEmpleado);
		//Como voy a modificar la base de datos, tengo que crear una transacción.
		EntityTransaction tran=em.getTransaction();
		tran.begin();
		int valor=query.executeUpdate();
		tran.commit();
		return valor==1?true:false; 
	}
	public int actualizarSueldo(EntityManager em, String departamento, String sede, double porcentaje) {
		Query query=em.createNamedQuery("Empleado.actualizarSueldo");
		query.setParameter("porcentaje", porcentaje/100);
		query.setParameter("departamento",departamento);
		query.setParameter("sede",sede);
		//Como voy a modificar la base de datos, tengo que crear una transacción.
		EntityTransaction tran=em.getTransaction();
		tran.begin();
		int empleadosActualizados=query.executeUpdate();
		tran.commit();
		return empleadosActualizados;
	}
	
}
