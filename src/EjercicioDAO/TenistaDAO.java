package EjercicioDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TenistaDAO {
	
	private Connection conexion=null;
	public TenistaDAO() {
		conexion=crearConexion();
	}
	private Connection crearConexion(){
		Connection con=null;
		String url="jdbc:mysql://localhost:3306/ATP";
		String usuario="root";
		String password="123456";
		try {
		con=DriverManager.getConnection(url,usuario,password);
		}catch(SQLException ex) {
			System.out.println("Error al establecer conexión: "+ex.toString());
		}
		return con;
	}
	
	public boolean crear(Tenista t) {
		int valores=0;
		try {
		String sql="INSERT INTO tenista(idTenista,nombre,fechaNacimiento,puntos,titulosGS) VALUES (?,?,?,?,?)";
		PreparedStatement statement=conexion.prepareStatement(sql);		
		statement.setInt(1, t.getIdTenista());
		statement.setString(2, t.getNombre());
		statement.setDate(3, t.getFechaNacimiento());
		statement.setInt(4, t.getPuntos());
		statement.setInt(5, t.getTitulosGS());
		valores=statement.executeUpdate();
		}catch(SQLException ex) {
			System.out.println("Error al insertar un Tenista "+ex.toString());
		}
		//usamos una expresión ternaria.
		return valores==1?true:false;
	}
	
	public Tenista leer(int idTenista) {
		Tenista t=null;
		try {
		String sql="SELECT * FROM tenista WHERE idTenista="+idTenista;
		Statement statement=conexion.createStatement();
		ResultSet resultados=statement.executeQuery(sql);
			if(resultados.next()) {
				String nombre=resultados.getString(2);
				Date fechaNacimiento=resultados.getDate(3);
				int puntos=resultados.getInt(4);
				int titulosGS=resultados.getInt(5);
				t=new Tenista(idTenista,nombre,fechaNacimiento,puntos,titulosGS);
			}
		}catch(SQLException ex) {
			System.out.println("Error al leer el Tenista "+ex.toString());
		}
		return t;
	}
	public boolean actualizar(Tenista t) {
		
		int valores=0;
		try {
			String sql="UPDATE tenista SET nombre=?,fechaNacimiento=?,puntos=?,titulosGS=? where idTenista=?";
			PreparedStatement statement=conexion.prepareStatement(sql);		
			statement.setString(1, t.getNombre());
			statement.setDate(2, t.getFechaNacimiento());
			statement.setInt(3, t.getPuntos());
			statement.setInt(4, t.getTitulosGS());
			statement.setInt(5, t.getIdTenista());
			valores = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valores==1?true:false;
	}
	public boolean borrar(int idTenista){
		String sql="DELETE FROM tenista where idTenista="+idTenista;		
		int valores=0;
		try {
			Statement statement=conexion.createStatement();
			valores = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valores==1?true:false;
	}
	
	public List<Tenista> todosTenistas(boolean ordenPuntos){
		List<Tenista> tenistas=new ArrayList<Tenista>();
		int idTenista=0,puntos=0,titulosGS=0;
		Tenista t=null;
		Date fechaNacimiento=null;
		String nombre="";
		try {
			String sql="SELECT * FROM tenista";
			//Si ordenPuntos es true se deben devolver todos los tenistas ordenados por puntos de mayor a menor
			if(ordenPuntos) {
				sql+=" ORDER BY puntos desc";
			}
			Statement statement=conexion.createStatement();
			ResultSet resultados=statement.executeQuery(sql);
			
			while(resultados.next()) {
				idTenista=resultados.getInt(1);
				nombre=resultados.getString(2);
				fechaNacimiento=resultados.getDate(3);
				puntos=resultados.getInt(4);
				titulosGS=resultados.getInt(5);
				t=new Tenista(idTenista,nombre,fechaNacimiento,puntos,titulosGS);
				tenistas.add(t);
			}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return tenistas;
	}
	
	public List<Tenista> todosTenistas(int minTitulosGS, boolean ordenFechaNacimiento){
		List<Tenista> tenistas=new ArrayList<Tenista>();
		int idTenista=0,puntos=0,titulosGS=0;
		Tenista t=null;
		Date fechaNacimiento=null;
		String nombre="";
		try {
			String sql="SELECT * FROM tenista WHERE titulosGS>="+minTitulosGS+" ORDER BY fechaNacimiento ";
			
			if(ordenFechaNacimiento) { 
				sql+="desc";
			}else { 
				sql+="asc";
			}			
			Statement statement=conexion.createStatement();
			ResultSet resultados=statement.executeQuery(sql);
			
			while(resultados.next()) {
				idTenista=resultados.getInt(1);
				nombre=resultados.getString(2);
				fechaNacimiento=resultados.getDate(3);
				puntos=resultados.getInt(4);
				titulosGS=resultados.getInt(5);
				t=new Tenista(idTenista,nombre,fechaNacimiento,puntos,titulosGS);
				tenistas.add(t);
			}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return tenistas;
	}
}
