package test.daos;

import modelo.dao.ClienteDao;
import modelo.dao.ClienteDaoImplMy5Jdbc;
import modelo.javabeans.Cliente;

public class TestClienteDao {

	private static ClienteDao cdao;
	
	static {
		cdao = new ClienteDaoImplMy5Jdbc();
	}
	
	public static void main(String[] args) {
		//insertar();
		//todos();
		//uno();
		//eliminar();
		//exportar();
		//importar();
		
	}
	
	public static void insertar() {
		System.out.println("\nInsert cliente\n");
		Cliente cliente = new Cliente("A34526762","Amadeo","Castellana","C/Los alamos",3500000,34);
		System.out.println("Cliente insertado, espero 1 : " + cdao.insertOne(cliente));
		Cliente cliente2 = new Cliente("A34526762","Amadeo","Castellana","C/Los alamos",3500000,34);
		System.out.println("Cliente duplicado, espero 0 : " + cdao.insertOne(cliente2));
		
		
		todos();
	}
	
	public static void todos() {
		System.out.println("\nListado de todos\n");
		for (Cliente ele: cdao.findAll())
			System.out.println(ele);
	}
	
	public static void uno() {
		System.out.println("\nUn cliente\n");
		System.out.println("Este existe : " + cdao.findById("A11111111"));
		System.out.println("Este no existe : " + cdao.findById("0000000000"));
	}
	
	public static void eliminar() {
		switch(cdao.deleteOne("A34526762")) {
		case 1:
			System.out.println("Eliminado");
			break;
		case 0:
			System.out.println("No existe");
			break;
		default:
			System.out.println("No puedes eliminar a este cliente");
			
		}
		
		
	}
	
	public static void exportar() {
		cdao.exportar("d:/ficheros/pruebas2.obj");
		
	}
	
	public static void importar() {
		System.out.println("Este existe: " + cdao.importar("d:/ficheros/pruebas4.obj"));
		System.out.println("Castañazo: " + cdao.importar("d:/ficheros/pruebas6.obj"));
		
		
	}
	
	

}
