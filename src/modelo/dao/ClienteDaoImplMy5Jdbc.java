package modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.javabeans.Cliente;

/**
 * Implementacion de la interfaz {@link ClienteDao} utilizando JDBC con MySQL.
 * Esta clase permita realizar operaciones CRUD sobre la tabla de clientes;
 * asi como exportar e importar clientes desde/hacia archivos mediante serailizacion.
 * 
 * Extiende/hereda de {@link AbsGenericoImplMysql} que gestiona la conexion y recursos JDBC.
 */
public class ClienteDaoImplMy5Jdbc extends AbsGenericoImplMysql implements ClienteDao{

	/**
	 * Inserta un nuevo cliente en la base de datos
	 * 
	 * @param cliente el cliente a insertar
	 * @return el numero de filas afectadas por la operacion
	 */
	@Override
	public int insertOne(Cliente cliente) {
		
		//se define la sentencia SQL de insercion con parametros
		sql = "insert into clientes(cif, nombre, apellidos, domicilio,"
				+ "facturacion_anual, numero_empleados) value(?,?,?,?,?,?)";
		
		//Se inicializa a 0 la variable que contara las filas afectadas
		filas = 0;
		try {
			//Se prepara la sentencia con preparedStatement y se asignan los valores
			//de los clientes a los parametros de la sentencia
			ps = conn.prepareStatement(sql);
			ps.setString(1,cliente.getCif());
			ps.setString(2,cliente.getNombre());
			ps.setString(3,cliente.getApellidos());
			ps.setString(4,cliente.getDomicilio());
			ps.setDouble(5,cliente.getFacturacionAnual());
			ps.setInt(6,cliente.getNumeroEmpleados());
			
			//Se ejecuta la sentencia SQL de insercion
			filas = ps.executeUpdate();
			
			//Si ocurre una excepcion SQL, se imprime el error
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return filas;
	}
	
	/**
	 * Busca un cliente por su CIF en la base de datos
	 */
	

	@Override
	public Cliente findById(String cif) {
		
		//Se define la sentencia SQL para buscar por su CIF
		sql = "select * from clientes where cif = ?";
		
		//Se declara un objetoCliente que se devolvera al final
		Cliente cliente = null; 
		try {
			//Se prepara la consulta  y se establece el valor del parametro CIF
			ps = conn.prepareStatement(sql);
			ps.setString(1,cif);
			
			//Se ejecuta la consulta y se obtiene el resultado
			rs = ps.executeQuery();
			
			//Si existe una fila en el resultado, se crea un objeto Cliente y se
			//rellenan con los datos de la BD
			if(rs.next()) {
				
				cliente = new Cliente();
				cliente.setCif(rs.getString("cif"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDomicilio(rs.getString("domicilio"));
				cliente.setFacturacionAnual(rs.getDouble("facturacion_anual"));
				cliente.setNumeroEmpleados(rs.getInt("numero_empleados"));
			}
			
			//Si ocurre una excepcion SQL, se imprime el error
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//Se devuelve el objeto cliente ( o null si no se encontra)
		return cliente;
	}
	
	/**
	 * Recupera todos los clientes almacenados en la base de datos
	 */

	@Override
	public List<Cliente> findAll() {
		
		//Se define la sentencia sql para obtener todos los registros
		sql = "select * from clientes";
		
		//Se crea una lista vacia donde se guardaran los clientes
		List<Cliente> aux = new ArrayList<Cliente>();
		try {
			
			//Se prepara la sentencia y se ejecuta la consulta
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			//Por cada fila del resultado, se crea un objeto Cliente, se rellena
			//con los datos y se aniade a la lista
			while(rs.next()) {
				
				Cliente cliente = new Cliente();
				cliente.setCif(rs.getString("cif"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDomicilio(rs.getString("domicilio"));
				cliente.setFacturacionAnual(rs.getDouble("facturacion_anual"));
				cliente.setNumeroEmpleados(rs.getInt("numero_empleados"));
				
				aux.add(cliente);
			}
			
			//Si ocurrre una excepcion SQL, se imprime el error.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Se devuelve la lista de clientes
		return aux;
	}
	
	/**
	 * Elimina un cliente de la base de daos usando su CIF
	 */

	@Override
	public int deleteOne(String cif) {
		
		//Como los anteriores
		sql = "delete from clientes where cif = ?";
		filas=0;
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, cif);
			filas = ps.executeUpdate();
			
			//Si ocurre una excepcion, se asigna -1 a filas como valor de error
		} catch (SQLException e) {
			//e.printStackTrace();
			filas =-1;
		}
		return filas;
	}
	
	/**
	 * Exporta todos los clientes almacenados en la base de datos a un fichero.
	 * El fichero se crea o sobreescribe en la ruta especificada.
	 * 
	 * @param nombreFichero la ruta del archivo donde exportar llos clientes
	 * @return el nombre del fichero si la operacion tiene exito, o un mensaje de 
	 * error si falla
	 */

	@SuppressWarnings("resource")//esto me ha salido al poner el return en el FileNotFounndException.
	@Override
	public String exportar(String nombreFichero) {
		
		//Se crea un objeto File con el nombre del fichero indicado
		File fichero = new File(nombreFichero);
		
		//Se declaran los flujos de salida (FileOutStream y ObjectOutputStream)
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		//Se intenta abrir el flujio de archivo
		try {
			fos = new FileOutputStream(fichero);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "El fichero no existe";
		}
		
		//Se intenta abrir el flujo nde objetos
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Se recupera la lista de todos los clientes con findAll()
		List<Cliente> clientes = findAll();
		
		//POr cada cliente en la lista, se ecribe su objeto en el fichero
		for (Cliente cliente : clientes) {
			try {
				oos.writeObject(cliente);
				
				//Si ocurre una excepcion durante la escritura, se imprime el error y
				//se imprime el mensaje.
			}catch (IOException e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Fichero no existe");
			}
		}
		return "Clienntes bien exportados en: " + nombreFichero;
	}
	
	/**
	 * Importa clientes desde un fichero previamnete exportado
	 */

	@Override
	public List<Cliente> importar(String nombreFichero) {
		
		//Se crea una lista vacia de clientes
		List<Cliente> lista = new ArrayList<>();
		
		//Se abre un ObjerctInputStream para leer el fichero
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {
			
			//Se lee en bucle objetos del fichero mientras no se alcance el final
	        while (true) {
	            try {
	                Object obj = ois.readObject();
	                
	                //si el objeto leido es de tipo Cliente, se aniade a la lista
	                if (obj instanceof Cliente) {
	                    lista.add((Cliente) obj);
	                }
	                
	                //Cuando detecta el final del fichero (EOFException), se rompe el bucle
	            } catch (EOFException e) {
	                break;
	            }
	        }
	        
	        //Si ocurre uana excepcion, se imprime el error
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

		//Devuelve la lista de clientes importados
	    return lista;
	}
	

}
