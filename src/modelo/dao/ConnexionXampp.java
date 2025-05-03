package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Clase encargada de establecer una conexion unica (Singleton) con la base de datos
 * 
 * Utiliza el driver JDBC de MySQL pata conectar con la base de datos definida por
 * la URL, usuario y contrasenia especificados. La conexion es comnpartida y se accede
 * a ella mediante el metodo estatico (@code getConnexio()}.
 * 
 * Esta clase esta diseniada como Singleton: solo se establece una conexion 
 * mientras el atributo {@code conn} permanezca nulo.
 * 
 * La conexion se realiza contra una base de datos local (localhost) llamada
 * proyectos_FP_2025 usando el usuario por defecto root sin contrasenia.
 * 
 * @author Alessandro garcia
 * @versio 1.0
 * @see java.sql.Connection
 * @see java.sql.Drivermanager
 */

public class ConnexionXampp {
	/**
	 * URL de la base de datos a la que se conecta
	 */
	private String url = "jdbc:mysql://localhost:3306/proyectos_FP_2025";
	
	/**
	 * Usuario para acceder a la base de datos
	 */
	private String user = "root";
	
	/**
	 * Contrasenia para acceder a la base de datos
	 */
	private String password = "";
	
	/**
	 * Objeto de conexion JDBC compartido.
	 */

	private static Connection conn;
	
	/**
	 * Constructor privado que establece la conexion con la base de datos.
	 * Si la conexion es exitosa, se muestra un mensaje en consola.
	 * En caso de error, se imprime el stack trace y se muestra un mennsaje de error.
	 */
	
	private  ConnexionXampp() {
		try {
			conn = DriverManager.getConnection(url, user,password);
			System.out.println("\nCONEXION ESTABLECIDA");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("\nCONEXION NO ESTABLECIDA");
		}
		
	}
	
	/**
	 * Metodo que devuelve unna connexxion activa con la base de datos
	 * 
	 * Si la conexion aun noi ha sido creda, se instacia un nuevo objeto
	 * de {@code ConnexionXampp}, lo que genera la conexion interna.
	 * 
	 * @return una innstacia de {@link Connection} conectada la base de datos MySQL
	 */
	public static Connection getConexion() {
		if (conn == null)
			new ConnexionXampp();
		
		return conn;
	}
}
