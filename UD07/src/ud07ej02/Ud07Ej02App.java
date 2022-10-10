package ud07ej02;

import java.util.Set;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;

public class Ud07Ej02App {

	public static void main(String[] args) {
		//Creo una lista inicial para que el usuario vea los elementos en el almacen
		elementosEnAlmacen();
		listaCompra();
	}
	
	//base de datos de cantidades
	public static Hashtable<String,Hashtable<String,Integer>> stockSuper () {
		// Donde se guardan todas las unidades
		final Hashtable<String,Hashtable<String,Integer>> ALMACEN = new Hashtable<String,Hashtable<String,Integer>>();
		//Unidades de manzanas
		final Hashtable<String,Integer> CONTENEDOR_MANZANAS = new Hashtable<String,Integer>();
		CONTENEDOR_MANZANAS.put("MANZANAS_AMARILLAS",20);
		CONTENEDOR_MANZANAS.put("MANZANAS_ROJAS",7);
		CONTENEDOR_MANZANAS.put("MANZANAS_VERDES",16);
		//Unidades de zanahorias
		final Hashtable<String,Integer> CONTENEDOR_ZANAHORIAS = new Hashtable<String,Integer>();
		CONTENEDOR_ZANAHORIAS.put("ZANAHORIAS_LARGAS",56);
		CONTENEDOR_ZANAHORIAS.put("ZANAHORIAS_CORTAS",23);
		CONTENEDOR_ZANAHORIAS.put("ZANAHORIAS_MEDIAS",30);
		//Unidades de zanahorias
		final Hashtable<String,Integer> CONTENEDOR_TOMATES = new Hashtable<String,Integer>();
		CONTENEDOR_TOMATES.put("TOMATES_CHERRY",112);
		CONTENEDOR_TOMATES.put("TOMATES_ROJOS",20);
		CONTENEDOR_TOMATES.put("TOMATES_PERA",315);
		
		ALMACEN.put("MANZANAS", CONTENEDOR_MANZANAS);
		ALMACEN.put("ZANAHORIAS", CONTENEDOR_ZANAHORIAS);
		ALMACEN.put("TOMATES", CONTENEDOR_TOMATES);			
		
		return ALMACEN;
	}
	
	//Base de dato de precios
	public static Hashtable<String,Hashtable<String,Double>> precioSuper () {
		// Donde se guardan todas las unidades
		final Hashtable<String,Hashtable<String,Double>> PRECIO = new Hashtable<String,Hashtable<String,Double>>();
		//Unidades de manzanas
		final Hashtable<String,Double> PRECIO_MANZANAS = new Hashtable<String,Double>();
		PRECIO_MANZANAS.put("MANZANAS_AMARILLAS",23.7);
		PRECIO_MANZANAS.put("MANZANAS_ROJAS",7.59);
		PRECIO_MANZANAS.put("MANZANAS_VERDES",16.8);
		//Unidades de zanahorias
		final Hashtable<String,Double> PRECIO_ZANAHORIAS = new Hashtable<String,Double>();
		PRECIO_ZANAHORIAS.put("ZANAHORIAS_LARGAS",56.9);
		PRECIO_ZANAHORIAS.put("ZANAHORIAS_CORTAS",23.5);
		PRECIO_ZANAHORIAS.put("ZANAHORIAS_MEDIAS",3.50);
		//Unidades de zanahorias
		final Hashtable<String,Double> PRECIO_TOMATES = new Hashtable<String,Double>();
		PRECIO_TOMATES.put("TOMATES_CHERRY",1.12);
		PRECIO_TOMATES.put("TOMATES_ROJOS",17.5);
		PRECIO_TOMATES.put("TOMATES_PERA",38.65);
		
		PRECIO.put("MANZANAS", PRECIO_MANZANAS);
		PRECIO.put("ZANAHORIAS", PRECIO_ZANAHORIAS);
		PRECIO.put("TOMATES", PRECIO_TOMATES);			
		
		return PRECIO;
	}
	
	//Base de datoS de IVA
	public static Hashtable<String,Hashtable<String,Double>> ivaSuper () {
		// Donde se guardan todas las unidades
		final Hashtable<String,Hashtable<String,Double>> IVA = new Hashtable<String,Hashtable<String,Double>>();
		//Unidades de manzanas
		final Hashtable<String,Double> IVA_MANZANAS = new Hashtable<String,Double>();
		IVA_MANZANAS.put("MANZANAS_AMARILLAS",0.21);
		IVA_MANZANAS.put("MANZANAS_ROJAS",0.04);
		IVA_MANZANAS.put("MANZANAS_VERDES",0.21);
		//Unidades de zanahorias
		final Hashtable<String,Double> IVA_ZANAHORIAS = new Hashtable<String,Double>();
		IVA_ZANAHORIAS.put("ZANAHORIAS_LARGAS",0.21);
		IVA_ZANAHORIAS.put("ZANAHORIAS_CORTAS",0.04);
		IVA_ZANAHORIAS.put("ZANAHORIAS_MEDIAS",0.04);
		//Unidades de zanahorias
		final Hashtable<String,Double> IVA_TOMATES = new Hashtable<String,Double>();
		IVA_TOMATES.put("TOMATES_CHERRY",0.04);
		IVA_TOMATES.put("TOMATES_ROJOS",0.21);
		IVA_TOMATES.put("TOMATES_PERA",0.21);
		
		IVA.put("MANZANAS", IVA_MANZANAS);
		IVA.put("ZANAHORIAS", IVA_ZANAHORIAS);
		IVA.put("TOMATES", IVA_TOMATES);			
			
		return IVA;
	}
	
	//metodo para mostrar en pantalla los elementos que hay en almacen
	public static void elementosEnAlmacen() {
		System.out.println("Estos són los elementos en alamcen:");
		//Saco la Hashtable de una de las bases de datos, dado que todas tienen el mismo numero de elementos
		Hashtable<String,Hashtable<String,Integer>> elementos = stockSuper();
		//Saco las llaves que son las categrias
		Set<String> keys = elementos.keySet(); 
		for (String key:keys) {
			//Obtengo las subcategorias por cada categoria
			Hashtable<String,Integer> elemento = elementos.get(key);
			//Creo un set con las subcategrias según la categoria principal
			Set<String> keys_e = elemento.keySet();
			System.out.println("De "+key+" hay los diferentes tipos: "+keys_e);
		}
	}
	
	//metodo para añadir a la lista
	public static void listaCompra() {
		//variable interna puesta para salir del ciclo cuando el usuario decida no poner nada mas en la lista de la cesta
		int i=0;
		//variable para que deje de correr el programa si no se ha añadido nada a la cesta
		int j=0;
		//variable inicializada donde se guardara el valor del precio total
		double precio_total=0;
		Scanner sc = new Scanner(System.in);
		//Creo ArrayList para  producto, cantidad, precio, iva
		ArrayList<String> producto = new ArrayList<>();
		ArrayList<Integer> cantidad = new ArrayList<>();
		ArrayList<Double> precio = new ArrayList<>();
		ArrayList<Double> iva = new ArrayList<>();
		do {
			String add = seguirCompra(sc);
			//entra en el ciclo si el usuario desea añadr algo mas a la cesta escribiendo si, en caso que escriba otra cosa, se acaba la lista
			if (add.toLowerCase().equals("si")) {
				String categoria = elegirCategoria(sc);
				String subcategoria = elegirSubcategoria(sc, categoria);
				producto.add(subcategoria);
				cantidad.add(comparativa(categoria, subcategoria, sc));
				precio.add(sacarPrecio(categoria, subcategoria));
				iva.add(sacarIva(categoria, subcategoria));
				j++;
			} else {
				i++;
			}
		} while (i<1) ;
		
		if (j>0) {
			enseñarIva(producto,iva);
			sacarPrecioBruto(cantidad, precio);
			precio_total = sacarPrecioIva(cantidad, precio, iva);
			pagar(precio_total,sc);
			sc.close();
		}
		
	}
	
	//hacer la lista comparativa con el stock, sacar precio y iva
	public static int comparativa(String a, String b, Scanner sc) {
		Hashtable<String,Hashtable<String,Integer>> elementos = stockSuper();
		Hashtable<String,Integer> elemento = elementos.get(a);
		int subelemento = elemento.get(b);
		System.out.println("Hay disponibles "+subelemento);
		int cantidad=0;
		do {
			//te obliga a meter una cantidad que sea inferior o igual a la disponible
			System.out.println("Introducir el número de unidades que quiere comprar:");
			cantidad = sc.nextInt();
		}while(cantidad>subelemento && cantidad>0);
		
		return cantidad;
	}
	
	//sacar el precio 
	public static double sacarPrecio(String a, String b) {
		Hashtable<String,Hashtable<String,Double>> elementos = precioSuper();
		Hashtable<String,Double> elemento = elementos.get(a);
		double precio = elemento.get(b);
		return precio;
	}
	
	//sacar el Iva 
	public static double sacarIva(String a, String b) {
		Hashtable<String,Hashtable<String,Double>> elementos = ivaSuper();
		Hashtable<String,Double> elemento = elementos.get(a);
		double iva = elemento.get(b);
		return iva;
	}	

	//enseñar el iva de cada producto
	public static void enseñarIva(ArrayList<String> elemento, ArrayList<Double> iva) {
		for (int i=0; i<elemento.size();i++) {
			System.out.println("El IVA aplicado a "+elemento.get(i)+" es de "+(iva.get(i)*100)+"%");
		}
	}
	
	// Multiplica la cantidad cojida de cada producto, por su precio, pasa por todos los productos guardados en el array, asi saca el precio bruto
	public static void sacarPrecioBruto(ArrayList<Integer> cantidad, ArrayList<Double> precio) {
		int j=0;
		for (int i=0; i<cantidad.size();i++) {
			j+=cantidad.get(i)*precio.get(i);
		}
		System.out.println("El precio bruto total es de "+j+"€");
	}
	
	//Hace el mismo calculo que en el precio bruto pero poniendole el iva tambien
	public static double sacarPrecioIva(ArrayList<Integer> cantidad, ArrayList<Double> precio, ArrayList<Double> iva) {
		int j=0;
		int k=0;
		for (int i=0; i<cantidad.size();i++) {
			j+=cantidad.get(i)*precio.get(i)*(1+iva.get(i));
			k+=cantidad.get(i);
		}
		System.out.println("El precio total es de "+j+"€");
		System.out.println("Se han adquirido de " + cantidad.size() + " productos diferentes, un total de "+k+" articulos");
		return j;
	}

	//te pide cuanto dinero vas a dar, en caso de ser inferior te solicita que des otro valor y si es superior o igual te printa la diferencia
	public static void pagar(double precio_total, Scanner sc) {
		int i=0;
		do {
			System.out.println("Cuanto dinero pagara? ");
			double pago = sc.nextDouble();
			if (pago>=precio_total) {
				double diff = Math.round((pago-precio_total) * 100d) / 100d;
				System.out.printf("Perfecto, se le devolvera "+diff+"€");
				i++;
			}else {
				System.out.println("Cantidad insuficiente");
			}
		}while(i<1);
		sc.close();
	}
	
	//metodo bucleque repite la pergunta hasta que obtenga si o no
	public static String seguirCompra(Scanner sc) {
		int i=0;
		String add[]={""};
		do {
			System.out.println("Quiere añadir algo a la cesta? (si/no)");
			add[0] = sc.next();
			if (add[0].toLowerCase().equals("si")||add[0].toLowerCase().equals("no")) {
				i++;
			} else {
				System.out.println("El comando introducido no es coorecto, porfavor introduzca si o no");
			}
		}while(i<1);
		return add[0];
	}
	//metodo bucleque repite la pergunta hasta que le ofrezcas una categoria que valga
	public static String elegirCategoria(Scanner sc) {
		//variables interna para bucle 
		int i=0;
		String add[]={""};
		//Hago el ciclo para que solo salga del bucle si responden una de las tres opciones, da igual si mayuscula o minsucula
		do {
			System.out.println("Que elemento quiere añadir? [TOMATES,MANZANAS,ZANAHORIAS]");
			add[0] = sc.next().toUpperCase();
			if (add[0].equals("TOMATES")||add[0].equals("MANZANAS")||add[0].equals("ZANAHORIAS")) {
				i++;
			}else {
				System.out.println("Disculpa, no esta la categoria que ha solicitado");
			}
		}while(i<1);
		return add[0];
	}
	
	//metodo bucleque repite la pergunta hasta que le ofrezcas una subcategoria que valga
		public static String elegirSubcategoria(Scanner sc, String categoria) {
			//variables internas para bucle i arrays
			int i=0;
			int j=0;
			String add[]={""};
			String subcategoria[]=new String[3];
			//Llamo a la base de datos i obtengo la hastable de la categoria en concreto
			Hashtable<String,Hashtable<String,Integer>> elementos = stockSuper();
			//Cojo la saco la hashtable de la categoria que deseo
			Hashtable<String,Integer> elemento = elementos.get(categoria);
			//Hago un set
			Set<String> keys_e = elemento.keySet();
			//Meto las llaves del Hashtag en question en un array, para que se personalize en el if de mas abajo
			for (String key:keys_e) {
				subcategoria[j]=key;
				j++;
			}
			//Hago el ciclo para que solo salga del bucle si responden una de las tres opciones, da igual si mayuscula o minsucula
			do {
				System.out.println("Que subelemento quiere añadir? "+keys_e);
				add[0] = sc.next().toUpperCase();
				if (add[0].equals(subcategoria[0])||add[0].equals(subcategoria[1])||add[0].equals(subcategoria[2])) {
					i++;
				}else {
					System.out.println("Disculpa, no esta la subcategoria que ha solicitado");
				}
			}while(i<1);
			return add[0];
		}
	
	
}