package principales;

import java.util.List;
import java.util.Scanner;
import modelo.dao.ClienteDaoImplMy5Jdbc;
import modelo.dao.ClienteDao;
import modelo.javabeans.Cliente;
/**
 * Clase principal para la gestion de clientes desde consola.
 * Permite insertar, buscar. lsitar, eliminar, exportar e importar clientes
 * mediante un menu interactivo.
 * 
 * Utiliza una implemenatcion de {@link ClienteDao} para interactuar con la base de datos
 * 
 * @author Alessandro Garcia Mereu
 */

public class GestionClientes {

		
		private static Scanner leer = new Scanner(System.in);
		private static  ClienteDao micliente = new ClienteDaoImplMy5Jdbc();
		
		/**
		 * Metodo principal que lanza el menu de gestion de clientes.
		 * Ejecuta opraciones CRUD, asi como importacion de clientes
		 * 
		 * @param args argumentos de linea de comandos.
		 */
		
		
		public static void main(String[] args) {
			int opcion;
			
			do {
				opcion =pintarMenu();
				leer.nextLine();// Limpieza de buffer.
				
				switch(opcion) {
				case 1:
					Cliente cliente = leerClientePorConsola();
					micliente.insertOne(cliente);
					break;
				case 2:
					System.out.println("Introduce el cif del cliente");
					/*Captura de la entarada del usuario desde consola usando Scanner.nextLine()
					 * y se asigna a la variable cifBuscar, que contendra el CIF a buscar*/
					String cifBuscar = leer.nextLine();                
					Cliente encontrado = micliente.findById(cifBuscar); // asignacion de valor por retorno
					System.out.println(encontrado !=null ? encontrado : //invocacion al metodo e inicilizacion de variable
						"Cliente no encontrado");// operador ternario, lo usamos poco pero es muy util
					break;
				case 3:
					List<Cliente> lista = micliente.findAll();
					for (Cliente ele: lista)
						System.out.println(ele);;
					break;
				case 4:
					System.out.println("Introduce el cif del cliente a eliminar");
					String cifEliminar = leer.nextLine();
					micliente.deleteOne(cifEliminar);
					break;
				case 5:
					System.out.println("Introduzca ruta donde exportar el fichero: ");
					String exportarClientes = leer.nextLine();
					String mensajeExportacion = micliente.exportar(exportarClientes);
					System.out.println(mensajeExportacion);
					break;
				case 6:
					System.out.println("Introduzca ruta de fichero de clientes: ");
					String importarClientes = leer.nextLine();
					List<Cliente> importados = micliente.importar(importarClientes);
					for(Cliente aux : importados) {
					System.out.println(aux);
					}
					break;
				case 7:
					System.out.println("SALIENDO DEL MENU");
					break;
				
				}
				
				
			}while (opcion != 7);
			
			
			leer.close();

		}
		/**
		 * Muestra el menu de opciones por consola y devuelve la opcopn elegida por el usuario
		 * @return numero de opcipn seleccionaada por el usuario ( entre 1 y 7).
		 */
		
		
		public static int pintarMenu() {
			System.out.println("\n---GESTION DE CLIENTES---\n");
			System.out.println("Elige una opcion: ");
			System.out.println("1.-Alta cliente");
			System.out.println("2.-Buscar a un cliente");
			System.out.println("3.-Buscar a todos los clientes");
			System.out.println("4.-Eliminar un cliente");
			System.out.println("5.-Exportar clientes");
			System.out.println("6.-Importar clientes");
			System.out.println("7.-Salir");
			
			
			int opcion = leer.nextInt();
			
			while(opcion<1 || opcion>7) {
				System.out.println("Opcion incorrecta, validos del 1 al 7");
				opcion = leer.nextInt();
			}
			return opcion;
			
		}
		/**
		 * Solicita los datos de un cliente por consola y devuelve un objeto 
		 * {@link Cliente}
		 * 
		 * @return Un nuevo Cliente con los datos introducidos por el usuario.
		 */
		
		public static Cliente leerClientePorConsola() {
			System.out.println("Introduce los datos del cliente");
			System.out.println("Cif: ");
			String cif = leer.nextLine();
			System.out.println("Nombre: ");
			String nombre = leer.nextLine();
			System.out.println("Apellidos: ");
			String apellidos = leer.nextLine();
			System.out.println("Domicilio: ");
			String domicilio = leer.nextLine();
			System.out.println("Facturacion anual : ");
			double facturacionAnual = leer.nextDouble();
			System.out.println("Numero de empleados: ");
			int numeroEmpleados = leer.nextInt();
			
			return new Cliente(cif, nombre, apellidos, domicilio, facturacionAnual, numeroEmpleados);
		}

}
