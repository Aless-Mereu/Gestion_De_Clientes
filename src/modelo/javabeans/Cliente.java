package modelo.javabeans;

import java.io.Serializable;
import java.util.Objects;
/**
 * Clase que representa un cliente con sus datos personales y comerciales.
 * Implementa {@link Serializable} para permitir la serializacion del objeto.
 * 
 * Los objetos Cliente se consideran iguales si comparten el mismo Cif.
 * 
 * @author Alessandro garcia Mereu
 */

public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cif;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private double facturacionAnual;
	private int numeroEmpleados;
	
	/**
	 * Constructor por defecto.
	 * Crea un objeto Cliente nsinn inicializar sus atributos
	 */
	
	
	public Cliente() {
		super();
	}
	
	/**
	 * Constructor con todos los atributos del cliente
	 * 
	 * @param cif Codigo de identificacion fiscal del cliente
	 * @param nombre Nombre del cliente
	 * @param apellidos Apellidos del cliente
	 * @param domicilio Direccion del cliente
	 * @param facturacionAnual Facturacion annual del cliente
	 * @param numeroEmpleados Numero de empleados que tiene el cliente
	 */
	
	public Cliente(String cif, String nombre, String apellidos, String domicilio, double facturacionAnual,
			int numeroEmpleados) {
		super();
		this.cif = cif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.facturacionAnual = facturacionAnual;
		this.numeroEmpleados = numeroEmpleados;
	}

	/**
	 * @return CIF del cliente
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @return Nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return Apellidos del cliennte
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @return Domicilio del cliente
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @return facturacion anual del cliente
	 */
	public double getFacturacionAnual() {
		return facturacionAnual;
	}


	/**
	 * 
	 * @return Numero de empleados del cliente
	 */ 
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}

	/**
	 * Establace el CIF del cliente
	 * 
	 * @param cif Nuevo valor del CIF
	 */

	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * Establece el nombre del cliente
	 * 
	 * @param nombre Nuevo nombre del cliente
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Establece los apllidos del cliente
	 * 
	 * @param apellidos Nuevos apellidos del cliente
	 */

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Establece lel domicilio del cliente
	 * 
	 * @param domicilio Nuevo del cliente
	 */


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	/**
	 * Establece la facturacion anula del cliente
	 * 
	 * @param facturacionAnual Nueva facturacion anual del cliente
	 */


	public void setFacturacionAnual(double facturacionAnual) {
		this.facturacionAnual = facturacionAnual;
	}
	
	/**
	 * Establece el numero de empleados del cliente
	 * @param numeroEmpleados Nuevo numero de empleados del cliente
	 */


	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	
	/**
	 * calcula el codigo hash a parti del CIF
	 * 
	 * @return valor hash a partir del cif
	 */


	@Override
	public int hashCode() {
		return Objects.hash(cif);
	}
	
	/**
	 * Compara dos clienntes por su cif.
	 * 
	 * @param obj Objeto a comprar.
	 * @return true si ambos clientes tienen el mismo CIF, false en casi contrario
	 */


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cif, other.cif);
	}
	
	/**
	 * Represenntacion textual del cliente
	 * 
	 * @return Cadenna con los valores de los atributos del cliente.
	 */


	@Override
	public String toString() {
		return "Cliente [cif=" + cif + ", nombre=" + nombre + ", apellidos=" + apellidos + ", domicilio=" + domicilio
				+ ", facturacionAnual=" + facturacionAnual + ", numeroEmpleados=" + numeroEmpleados + "]";
	}
	
	
	
	
	

}
