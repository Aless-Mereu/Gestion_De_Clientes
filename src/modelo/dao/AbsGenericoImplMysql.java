package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase que proporciona los atributos y la conexion base
 * para acceder a una base de datos MySQL mediante JDBC.
 * 
 * Esta clase sirve como superclase para los DAOs concretos que necesiten
 * ejecutar sentencias SQL.Proporciona atributos comunes como la conexion,
 * los objetos de acesso ("PreparedStatement","ResultSet"), la sentencia SQL y
 * un contador de filas afectadas.
 * 
 * La conexion se obtiene directamente a traves de la clase {@code ConnexionXampp},
 * que se encarga de establecerla con la base de datos configurada
 * 
 * @author Alessandro Garcia Mereu
 * @version 1.0
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see modelo.dao.ConnexionXampp
 */

public abstract class  AbsGenericoImplMysql {
	
	/**
	 * Objeto para manejar la conexion a la base de datos
	 */
	protected Connection conn;
	
	/**
	 * Objeto para preparar sentencias SQL parametrizadas
	 */
	protected PreparedStatement ps;
	
	/**
	 * Objeto para almacenar y recorrer los resultados de una consulta SQL
	 */
	protected ResultSet rs;
	
	/**
	 * Sentencia SQL que se ejecutara
	 */
	protected String sql;
	
	/**
	 * Numero de filas afectadas por una operacion SQL ( por ejemplo, inserciones,
	 * actualizacionnes o eliminaciones
	 */
	protected int filas;

	/**
	 * Constructor que inicializa la conexion con la base de datos
	 * mediante la clase {@code ConnexionXampp}.
	 */
	public AbsGenericoImplMysql() {
		conn = ConnexionXampp.getConexion();
	}
	

}
