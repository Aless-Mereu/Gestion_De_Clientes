package modelo.dao;

import java.util.List;

import modelo.javabeans.Cliente;

/**
 * Interfaz que define las operacionnes CRUD y de importacion/exportacion
 * que deben implementar las clases DAO que gestionen objetos de tipo {@link Cliente}.
 * 
 * Esta interfaz permite innsertar, buscar, eliminar, listar, importar y exportar
 * 
 * @author Alessandro Garcia Mereu
 */


public interface ClienteDao {
	
	/**
	 * Inserta un nuevo cliente en el origen de datos
	 * 
	 * @param cliente Objeto Cliente a insertar.
	 * @return Nnumero de filas afectadas (1 si se inserta correctamente, 0 si no)
	 */
	int insertOne(Cliente cliente);
	
	/**
	 * Busca a un cliente por su CIF
	 * 
	 * @param cif Codigo de identificacionn fiscal del cliente
	 * @return Objeto Cliente si se encuenntra, o null si no existe
	 */
	Cliente findById(String cif);
	
	/**
	 * recupera todos los clientes del origen de datos.
	 * 
	 * @return Lista de todods los clientes disponibles
	 */
	List<Cliente> findAll();
	
	/**
	 * Elimina un cliente del origen de datos a partir de su CIF
	 * 
	 * @param cif Codigo de identificacion fiscal del cliente a eliminar
	 * @return Numero de filas afectadas (1 si se elimina correctamente, 0 si no es el caso)
	 */
	
	int deleteOne (String cif);
	
	/**
	 * Exporta los datos de todos los clientes a un fichero
	 * 
	 * @param nombreFichero Ruta del fichero donde se guardaran los datos del cliente
	 * @return Nombre del fichero si la exportacion se realiza correctamente
	 */
	String exportar (String nombreFichero);//lee y graba los clientes en el fichero 
											//que le pase en la cabecera del metodo.
											//Finalmente decido que se lea por pantalla.
	
	/**
	 * Importa datos de clientes desde un fichero y los devuelve como una lista
	 * 
	 * @param nombreFichero Ruta y nombre del fichero desde donde se lerran los datos
	 * @return Lista de clientes importados desde el fichero.
	 */
	List<Cliente>importar (String nombreFichero); // lee los clientes y los coloca en
												// un List y los devuelve, para que el
												//menu los pueda imprimir por pantalla

}
