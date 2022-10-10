package ud07ej04;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

import ud07ej04.BaseDatos;

public class Ud07Ej04App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//No he sabido como hacer que se me guardaran los cambios hechos en la base de datos, mediante el uso exclusivo de metodos, asi que he generado un objeto que unicamente guarda la base de datos, con sus geters y seters para poder modificarlo
		BaseDatos base_datos = new BaseDatos();
		int i=0;
		while(i<1) {
			System.out.println("Que desea hacer, comprar o acceder a la base de datos? [COMPRAR,DATOS]");
			String eleccion = sc.next().toUpperCase();
			if (eleccion.equals("COMPRAR")) {
				elementosEnAlmacen(base_datos);
				listaCompra(sc, base_datos);
			}else if (eleccion.equals("DATOS")) {
				visMod(sc,base_datos);
			}
			System.out.println("Desea realizar alguna otra operacion? [SI/CUALQUIER OTRO COMANDO]");
			String seguir = sc.next().toUpperCase();
			if (seguir.equals("SI")) {
				
			}else {
				i++;
			}
		}
		sc.close();

		
	}

	// METODOS ACTIVIDAD 2
	// metodo para mostrar en pantalla los elementos que hay en almacen
	public static void elementosEnAlmacen(BaseDatos base_datos) {
		System.out.println("Estos són los elementos en alamcen:");
		// Saco la Hashtable de una de las bases de datos, dado que todas tienen el
		// mismo numero de elementos
		Hashtable<String, Hashtable<String, Integer>> elementos = base_datos.getStockSuper();
		// Saco las llaves que son las categrias
		Set<String> keys = elementos.keySet();
		for (String key : keys) {
			// Obtengo las subcategorias por cada categoria
			Hashtable<String, Integer> elemento = elementos.get(key);
			// Creo un set con las subcategrias según la categoria principal
			Set<String> keys_e = elemento.keySet();
			System.out.println("De " + key + " hay los diferentes tipos: " + keys_e);
		}
	}

	// metodo para añadir a la lista
	public static void listaCompra(Scanner sc, BaseDatos base_datos) {
		// variable interna puesta para salir del ciclo cuando el usuario decida no
		// poner nada mas en la lista de la cesta
		int i = 0;
		// variable para que deje de correr el programa si no se ha añadido nada a la
		// cesta
		int j = 0;
		// variable inicializada donde se guardara el valor del precio total
		double precio_total = 0;
		// Creo ArrayList para producto, cantidad, precio, iva
		ArrayList<String> producto = new ArrayList<>();
		ArrayList<Integer> cantidad = new ArrayList<>();
		ArrayList<Double> precio = new ArrayList<>();
		ArrayList<Double> iva = new ArrayList<>();
		do {
			String add = seguirCompra(sc);
			// entra en el ciclo si el usuario desea añadr algo mas a la cesta escribiendo
			// si, en caso que escriba otra cosa, se acaba la lista
			if (add.toLowerCase().equals("si")) {
				String categoria = elegirCategoria(sc,base_datos);
				String subcategoria = elegirSubcategoria(sc, categoria,base_datos);
				producto.add(subcategoria);
				cantidad.add(comparativa(categoria, subcategoria, sc,base_datos));
				precio.add(obtenerPrecio(categoria, subcategoria,base_datos));
				iva.add(obtenerIva(categoria, subcategoria,base_datos));
				j++;
			} else {
				i++;
			}
		} while (i < 1);

		if (j > 0) {
			enseñarIva(producto, iva);
			sacarPrecioBruto(cantidad, precio);
			precio_total = sacarPrecioIva(cantidad, precio, iva);
			pagar(precio_total, sc);
		}

	}

	// hacer la lista comparativa con el stock, sacar precio y iva
	public static int comparativa(String a, String b, Scanner sc, BaseDatos base_datos) {
		Hashtable<String, Hashtable<String, Integer>> elementos = base_datos.getStockSuper();
		Hashtable<String, Integer> elemento = elementos.get(a);
		int subelemento = elemento.get(b);
		System.out.println("Hay disponibles " + subelemento);
		int cantidad = 0;
		do {
			// te obliga a meter una cantidad que sea inferior o igual a la disponible
			System.out.println("Introducir el número de unidades que quiere comprar:");
			cantidad = sc.nextInt();
		} while (cantidad > subelemento && cantidad > 0);
		//La cantidad que saca en la lista de la compra, la sobre escribo en la base de datos
		elemento.replace(b, subelemento-cantidad);
		elementos.remove(a);
		elementos.put(a, elemento);
		base_datos.setStockSuper(elementos);
		return cantidad;
	}

	// enseñar el iva de cada producto
	public static void enseñarIva(ArrayList<String> elemento, ArrayList<Double> iva) {
		for (int i = 0; i < elemento.size(); i++) {
			System.out.println("El IVA aplicado a " + elemento.get(i) + " es de " + (iva.get(i) * 100) + "%");
		}
	}

	// Multiplica la cantidad cojida de cada producto, por su precio, pasa por todos los productos guardados en el array, asi saca el precio bruto
	public static void sacarPrecioBruto(ArrayList<Integer> cantidad, ArrayList<Double> precio) {
		int j = 0;
		for (int i = 0; i < cantidad.size(); i++) {
			j += cantidad.get(i) * precio.get(i);
		}
		System.out.println("El precio bruto total es de " + j + "€");
	}

	//Hace el mismo calculo que en el precio bruto pero poniendole el iva tambien
	public static double sacarPrecioIva(ArrayList<Integer> cantidad, ArrayList<Double> precio, ArrayList<Double> iva) {
		int j = 0;
		int k = 0;
		for (int i = 0; i < cantidad.size(); i++) {
			j += cantidad.get(i) * precio.get(i) * (1 + iva.get(i));
			k += cantidad.get(i);
		}
		System.out.println("El precio total es de " + j + "€");
		System.out.println(
				"Se han adquirido de " + cantidad.size() + " productos diferentes, un total de " + k + " articulos");
		return j;
	}

	//te pide cuanto dinero vas a dar, en caso de ser inferior te solicita que des otro valor y si es superior o igual te printa la diferencia
	public static void pagar(double precio_total, Scanner sc) {
		int i = 0;
		do {
			System.out.println("Cuanto dinero pagara? ");
			double pago = sc.nextDouble();
			if (pago >= precio_total) {
				double diff = Math.round((pago - precio_total) * 100d) / 100d;
				System.out.println("Perfecto, se le devolvera " + diff + "€");
				i++;
			} else {
				System.out.println("Cantidad insuficiente");
			}
		} while (i < 1);
	}

	// metodo bucleque repite la pergunta hasta que obtenga si o no
	public static String seguirCompra(Scanner sc) {
		int i = 0;
		String add = "" ;
		do {
			System.out.println("Quiere añadir algo a la cesta? (si/no)");
			add = sc.next();
			if (add.toLowerCase().equals("si") || add.toLowerCase().equals("no")) {
				i++;
			} else {
				System.out.println("El comando introducido no es coorecto, porfavor introduzca si o no");
			}
		} while (i < 1);
		return add;
	}

	// METODOS ACTIVIDAD 3
	// METODOS ELECCIÓN DE OPCIONES
	// SON LOS METODOS CREADOS ÚNICAMENTE PARA QUE PASEN AL USUARIO DE UNA OPCIÓN A
	// OTRA

	// Este metodo te da la opción de escoger la decisión del usuario de ver datos
	// de la base de datos o modificar estos
	public static void visMod(Scanner sc, BaseDatos base_datos) {
		String decision = verModificar(sc);
		switch (decision) {
		case "VER":
			visualizar(sc, base_datos);
			break;
		case "MODIFICAR":
			addModifyEliminate(sc, base_datos);
			break;
		}
	}

	// Lee que opcion de las 3 deseeas y te lleva a al lugar donde modificar los
	// datos
	public static void addModifyEliminate(Scanner sc, BaseDatos base_datos) {
		String decision = añadirModificarEliminar(sc);
		// A partir de aqui las elecciones no estan obligadas por el programa, en caso
		// que se introduzca un valor que no esta contemplado entonces simplemente omite
		// esta parte.
		// La gran diferencia en este punto también es que los comandos pasan a ser
		// numéricos para la facilitación de los if
		if (decision.equals("AÑADIR")) {
			System.out.println(
					"Que desea crear una categoria nueva (con su subcategoria) o añadir una nueva a una ya existente? (0/1)");
			int add_create = sc.nextInt();
			if (add_create == 0) {
				String nombre = createCategoria(sc);
				String apellido = createSubcategoria(sc);
				añadirModificarCantidad(sc, decision, nombre, apellido, add_create, base_datos);
				añadirModificarPrecio(sc, decision, nombre, apellido, add_create, base_datos);
				añadirModificarIva(sc, decision, nombre, apellido, add_create, base_datos);
			} else if (add_create == 1) {
				String nombre = elegirCategoria(sc, base_datos);
				String apellido = createSubcategoria(sc);
				añadirModificarCantidad(sc, decision, nombre, apellido, add_create, base_datos);
				añadirModificarPrecio(sc, decision, nombre, apellido, add_create, base_datos);
				añadirModificarIva(sc, decision, nombre, apellido, add_create, base_datos);
			} else {
				System.out.println("No se ha añadido nada");
			}

		} else if (decision.equals("MODIFICAR")) {
			String nombre = elegirCategoria(sc, base_datos);
			String apellido = elegirSubcategoria(sc, nombre, base_datos);
			System.out.println("Que deseas modificar Cantidad, Precio, Iva o Todos (1/2/3/4)");
			int modificar = sc.nextInt();
			if (modificar == 1) {
				añadirModificarCantidad(sc, decision, nombre, apellido, 0, base_datos);
			} else if (modificar == 2) {
				añadirModificarPrecio(sc, decision, nombre, apellido, 0, base_datos);
			} else if (modificar == 3) {
				añadirModificarIva(sc, decision, nombre, apellido, 0, base_datos);
			} else if (modificar == 4) {
				añadirModificarCantidad(sc, decision, nombre, apellido, 0, base_datos);
				añadirModificarPrecio(sc, decision, nombre, apellido, 0, base_datos);
				añadirModificarIva(sc, decision, nombre, apellido, 0, base_datos);
			} else {
				System.out.println("No se ha modificado nada nada");
			}
		} else if (decision.equals("ELIMINAR")) {
			System.out.println("Que deseas eliminar una categoria entera o solo una subcategoria (1/2)");
			int eliminar = sc.nextInt();
			if (eliminar == 1) {
				String nombre = elegirCategoria(sc, base_datos);
				añadirModificarCantidad(sc, decision, nombre, "", 0, base_datos);
				añadirModificarPrecio(sc, decision, nombre, "", 0, base_datos);
				añadirModificarIva(sc, decision, nombre, "", 0, base_datos);
			} else if (eliminar == 2) {
				String nombre = elegirCategoria(sc, base_datos);
				String apellido = elegirSubcategoria(sc, nombre, base_datos);
				// En el caso de categorias que soo tengan una subclase, al eliminar esa
				// subclase y querer ver la informacion de esa categoria queda en un bucle dado
				// que no hay subcategoria a la que acceder, por tanto lo que hace esto es
				// obligar al usuario a eliminar la categoria entera, en caso que desee eliminar
				// de una categoria que solo tiene una subcategoria
				if (contarsubclases(nombre, base_datos)) {
					System.out.println(
							"Solo hay una subclase, se debe eliminar este elemento elimanando la clase completa");
				} else {
					añadirModificarCantidad(sc, decision, nombre, apellido, 0, base_datos);
					añadirModificarPrecio(sc, decision, nombre, apellido, 0, base_datos);
					añadirModificarIva(sc, decision, nombre, apellido, 0, base_datos);
				}
			} else {
				System.out.println("No se ha eliminado nada");
			}

		}
	}

	// METODOS EJECUTANTES
	// METODOS PRINCIPALES CON GRAN CARGA DE CODIGO

	// Metodo que permite únicamente la visualización de los datos guardados en la
	// base de datos
	public static void visualizar(Scanner sc, BaseDatos base_datos) {
		// Variable introducida para que se repita todo el comando de visualizar datos
		// mientras el usuario siga queriendo mantenerse en esta area
		int i = 0;
		// Variable introducida para poder leer luego de los arrays que se van creando
		int j = 0;
		// Genero un araylist por cada tipo de información que se ha de guardar
		ArrayList<String> producto = new ArrayList<>();
		ArrayList<Integer> cantidad = new ArrayList<>();
		ArrayList<Double> precio = new ArrayList<>();
		ArrayList<Double> iva = new ArrayList<>();

		do {
			// elección de categoria y subcategoria
			String categoria = elegirCategoria(sc, base_datos);
			String subcategoria = elegirSubcategoria(sc, categoria, base_datos);
			// Guardo los datos de categoria en los arryalist
			producto.add(subcategoria);
			cantidad.add(obtenerCantidad(categoria, subcategoria, base_datos));
			precio.add(obtenerPrecio(categoria, subcategoria, base_datos));
			iva.add(obtenerIva(categoria, subcategoria, base_datos));
			// print con la información
			System.out.println("La informacion del producto " + producto.get(j) + " es la siguiente\nPrecio: "
					+ precio.get(j) + "\nCantidad: " + cantidad.get(j) + "\nIva: " + iva.get(j));
			// te da la elección de ver los datos de otro elemento, ver los datos de todos
			// los elementos que se han visualizado mientras estas en el modo ver datos o
			// salir de este modo
			System.out.println(
					"Desea ver al información de otro producto, de todos los que has visto hasta ahora o ninguno más[OTRO/TODOS/CUALQUIER OTRO COMANDO]:");
			String info = sc.next().toUpperCase();
			// si eliges otro únicamente suma 1 a la variable j en caso de que en algún
			// momento se decida ver todos, pueda recorrer en un for todas las posiciones
			// del arraylist
			if (info.equals("OTRO")) {
				j++;
				// Si se elige la opcion de visualizar todos, con un ciclo for hago que se
				// printeen los valores guardados en cada una de las posiciones del arraylist,
				// en orden
			} else if (info.equals("TODOS")) {
				// No he tenido tiempo de investigar una forma mas técnica de obtener la
				// separación entre tipos a la hora de genenrar la tabla
				System.out.println("Producto         Precio      Cantidad     Iva");
				for (int k = 0; k <= j; k++) {
					System.out.println(producto.get(k) + "    " + precio.get(k) + "        " + cantidad.get(k)
							+ "         " + iva.get(k));
				}
				// Te vuelve a dar la opción de ver información de otro producto
				System.out.println("Desea ver al información de otro producto? [OTRO/CUALQUIER OTRO COMANDO]:");
				String info_1 = sc.next().toUpperCase();
				if (info_1.equals("OTRO")) {
					j++;
				} else {
					i++;
				}
			} else {
				i++;
			}
		} while (i < 1);
	}

	// Los tres metodos siguientes tienen la funcion de modificar la tabla de datos
	// segun las solicitudes del usuario
	// metodo coge la base de datos, la copia y es donde se trabaja para guardar los
	// datos de cantidad
	public static Hashtable<String, Hashtable<String, Integer>> añadirModificarCantidad(Scanner sc, String decision,
			String nombre, String apellido, int add_create, BaseDatos base_datos) {
		// cojo la base de datos
		Hashtable<String, Hashtable<String, Integer>> hash_cantidad = base_datos.getStockSuper();

		if (!decision.equals("")) {
			// en caso que se pida añadir, pido al usuario que especifique un nombre de
			// categoria, un nombre de sub categoria, y borro la categoria antigua para
			// meterle la categoria nueva con la sub clase añadida o si es una nueva
			// categoria simplemente la añado
			if (decision.equals("AÑADIR")) {
				if (add_create == 1) {
					Hashtable<String, Integer> subproducto = hash_cantidad.get(nombre);
					String nombre_completo = nombre + "_" + apellido;
					System.out.println("Introducir cantidad");
					int stock = sc.nextInt();
					subproducto.put(nombre_completo, stock);
					hash_cantidad.remove(nombre);
					hash_cantidad.put(nombre, subproducto);
				} else if (add_create == 0) {
					String nombre_completo = nombre + "_" + apellido;
					System.out.println("Introducir cantidad");
					int stock = sc.nextInt();
					Hashtable<String, Integer> subcontenedor = new Hashtable<String, Integer>();
					subcontenedor.put(nombre_completo, stock);
					hash_cantidad.put(nombre, subcontenedor);
				} else {
					System.out.println("No se añadira nada");
				}
				// Para modificar los valores saco la hastable de la categoria que se desea,
				// cambio el valor deseado, elimino de la hastable principal la categoria que he
				// modificado, y le añado la misma pero con el elemento modificado
			} else if (decision.equals("MODIFICAR")) {
				Hashtable<String, Integer> subproducto = hash_cantidad.get(nombre);
				System.out.println("Introducir nueva cantidad");
				int nueva_cantidad = sc.nextInt();
				subproducto.replace(apellido, nueva_cantidad);
				hash_cantidad.remove(nombre);
				hash_cantidad.put(nombre, subproducto);
				// Simplemente usar remove
			} else if (decision.equals("ELIMINAR")) {
				// Si no pasa ningun valor de subcategoria, significa que desea eliminar una
				// categoria entera
				if (apellido.equals("")) {
					hash_cantidad.remove(nombre);
				} else {
					Hashtable<String, Integer> subproducto = hash_cantidad.get(nombre);
					subproducto.remove(apellido);
					hash_cantidad.remove(nombre);
					hash_cantidad.put(nombre, subproducto);
				}
			} else {
			}
		}
		base_datos.setStockSuper(hash_cantidad);

		return hash_cantidad;
	}

	// metodo coge la base de datos, la copia y es donde se trabaja para guardar los
	// datos de cantidad
	public static Hashtable<String, Hashtable<String, Double>> añadirModificarPrecio(Scanner sc, String decision,
			String nombre, String apellido, int add_create, BaseDatos base_datos) {
		Hashtable<String, Hashtable<String, Double>> hash_precio = base_datos.getPrecioSuper();

		if (!decision.equals("")) {
			if (decision.equals("AÑADIR")) {
				if (add_create == 1) {
					Hashtable<String, Double> subproducto = hash_precio.get(nombre);
					String nombre_completo = nombre + "_" + apellido;
					System.out.println("Introducir precio");
					double precio = sc.nextDouble();
					subproducto.put(nombre_completo, precio);
					hash_precio.remove(nombre);
					hash_precio.put(nombre, subproducto);
				} else if (add_create == 0) {
					String nombre_completo = nombre + "_" + apellido;
					System.out.println("Introducir precio");
					double precio = sc.nextDouble();
					Hashtable<String, Double> subcontenedor = new Hashtable<String, Double>();
					subcontenedor.put(nombre_completo, precio);
					hash_precio.put(nombre, subcontenedor);
				} else {
					System.out.println("No se añadira nada");
				}
			} else if (decision.equals("MODIFICAR")) {
				Hashtable<String, Double> subproducto = hash_precio.get(nombre);
				System.out.println("Introducir nuevo precio");
				double nuevo_precio = sc.nextDouble();
				subproducto.replace(apellido, nuevo_precio);
				hash_precio.remove(nombre);
				hash_precio.put(nombre, subproducto);
			} else if (decision.equals("ELIMINAR")) {
				// Si no pasa ningun valor de subcategoria, significa que desea eliminar una
				// categoria entera
				if (apellido.equals("")) {
					hash_precio.remove(nombre);
				} else {
					Hashtable<String, Double> subproducto = hash_precio.get(nombre);
					subproducto.remove(apellido);
					hash_precio.remove(nombre);
					hash_precio.put(nombre, subproducto);
				}
			} else {
			}
		}
		base_datos.setPrecioSuper(hash_precio);
		return hash_precio;
	}

	// metodo coge la base de datos, la copia y es donde se trabaja para guardar los
	// datos de cantidad
	public static Hashtable<String, Hashtable<String, Double>> añadirModificarIva(Scanner sc, String decision,
			String nombre, String apellido, int add_create, BaseDatos base_datos) {
		Hashtable<String, Hashtable<String, Double>> hash_iva = base_datos.getIvaSuper();

		if (!decision.equals("")) {
			if (decision.equals("AÑADIR")) {
				if (add_create == 1) {
					Hashtable<String, Double> subproducto = hash_iva.get(nombre);
					String nombre_completo = nombre + "_" + apellido;
					System.out.println("Introducir iva");
					double iva = sc.nextDouble();
					subproducto.put(nombre_completo, iva);
					hash_iva.remove(nombre);
					hash_iva.put(nombre, subproducto);
				} else if (add_create == 0) {
					String nombre_completo = nombre + "_" + apellido;
					System.out.println("Introducir iva");
					double iva = sc.nextDouble();
					Hashtable<String, Double> subcontenedor = new Hashtable<String, Double>();
					subcontenedor.put(nombre_completo, iva);
					hash_iva.put(nombre, subcontenedor);
				} else {
					System.out.println("No se añadira nada");
				}
			} else if (decision.equals("MODIFICAR")) {
				Hashtable<String, Double> subproducto = hash_iva.get(nombre);
				System.out.println("Introducir nuevo iva");
				double nuevo_iva = sc.nextDouble();
				subproducto.replace(apellido, nuevo_iva);
				hash_iva.remove(nombre);
				hash_iva.put(nombre, subproducto);
			} else if (decision.equals("ELIMINAR")) {
				// Si no pasa ningun valor de subcategoria, significa que desea eliminar una
				// categoria entera
				if (apellido.equals("")) {
					hash_iva.remove(nombre);
				} else {
					Hashtable<String, Double> subproducto = hash_iva.get(nombre);
					subproducto.remove(apellido);
					hash_iva.remove(nombre);
					hash_iva.put(nombre, subproducto);
				}
			} else {
			}
		}

		base_datos.setIvaSuper(hash_iva);
		return hash_iva;
	}

	// METODOS PARA QUE EL USUARIO INTRODUZCA EL NOMBRE DE LA CATEGORIA Y
	// SUBCATEGORIA EN CASO DE CREAR UNA CATEGORIA NUEVA
	public static String createCategoria(Scanner sc) {
		System.out.println("Introduce el nombre de la categoria: ejemplo PIMIENTOS");
		String nombre = sc.next().toUpperCase();
		return nombre;
	}

	public static String createSubcategoria(Scanner sc) {
		System.out.println(
				"Introduzca la especialdida de la categoria que quiere añadir: ej AGRIAS (de manzanas_agrias)");
		String apellido = sc.next().toUpperCase();
		return apellido;
	}

	// METODO USADO PARA CONTAR CUANTAS SUBCATEGORIAS TIENE UNA CATEGORIA
	public static boolean contarsubclases(String categoria, BaseDatos base_datos) {
		int contador = 0;
		Hashtable<String, Hashtable<String, Integer>> elementos = base_datos.getStockSuper();
		// Cojo la saco la hashtable de la categoria que deseo
		Hashtable<String, Integer> elemento = elementos.get(categoria);
		// Hago un set
		Set<String> keys_e = elemento.keySet();
		for (String key : keys_e) {
			contador++;
		}
		if (contador == 1) {
			return true;
		}
		return false;
	}

	// METODOS BUCLE, PARA OBLIGAR AL USUARIO A ESCOGER UNA DE LAS OPCIONES QUE SE
	// LE PROPONE

	// Con tal de que el programa no deje de ejecutarse si el usuario escribe alguna
	// cosa diferente, se realiza este bucle que la única salida es introduciendole
	// ver o modificar, (da igual si en mayusculas o minusculas dado que se
	// convierten con toUpperCase)
	public static String verModificar(Scanner sc) {
		int i = 0;
		String add = "";
		do {
			System.out.println("Que deseas VER o MODIFICAR datos?");
			add = sc.next().toUpperCase();
			if (add.equals("VER") || add.equals("MODIFICAR")) {
				i++;
			} else {
				System.out.println("El comando introducido no es correcto, porfavor introduzca VER o MODIFICAR");
			}
		} while (i < 1);
		return add;
	}

	// Tiene el mismo objetivo que todos los metodos metidos en esta categoria. La
	// diferencia es que en este caso coge las posibilidades de las categorias que
	// estan introducidas en la base de datos
	public static String elegirCategoria(Scanner sc, BaseDatos base_datos) {
		// variables interna para bucle
		int i = 0;
		String add = "";
		// Hago el ciclo para que solo salga del bucle si responden una de las opciones,
		// da igual si mayuscula o minsucula
		// Llamo a la base de datos i obtengo la hastable de la categoria en concreto
		Hashtable<String, Hashtable<String, Integer>> elementos = base_datos.getStockSuper();
		// Hago un set
		Set<String> keys_e = elementos.keySet();
		do {
			System.out.println("Que elemento quiere elegir?" + keys_e);
			add = sc.next().toUpperCase();
			for (String key : keys_e) {
				if (add.equals(key)) {
					i++;
				}
			}
			if (i == 0) {
				System.out.println("Disculpa, no esta la categoria que ha solicitado");
			}
		} while (i < 1);
		return add;
	}

	// metodo bucleque exactamente igual al de arriba pero con las subcategorias
	public static String elegirSubcategoria(Scanner sc, String categoria, BaseDatos base_datos) {
		// variables internas para bucle i arrays
		int i = 0;
		String add = "";
		// Llamo a la base de datos i obtengo la hastable de la categoria en concreto
		Hashtable<String, Hashtable<String, Integer>> elementos = base_datos.getStockSuper();
		// Cojo la hashtable de la categoria que deseo
		Hashtable<String, Integer> elemento = elementos.get(categoria);
		// Hago un set
		Set<String> keys_e = elemento.keySet();
		// Hago el ciclo para que solo salga del bucle si responden una de las opciones,
		// da igual si mayuscula o minsucula
		do {
			System.out.println("Que subelemento quiere elegir? " + keys_e);
			add = sc.next().toUpperCase();
			for (String key : keys_e) {
				if (add.equals(key)) {
					i++;
				}
			}
			if (i == 0) {
				System.out.println("Disculpa, no esta la subcategoria que ha solicitado");
			}
		} while (i < 1);
		return add;
	}

	// Como los metodos anteriores, te obliga a escribir por terminal una de las
	// opciones ofrecidas
	public static String añadirModificarEliminar(Scanner sc) {
		int i = 0;
		String add = "";
		do {
			System.out.println("Que deseas AÑADIR, MODIFICAR o ELIMINAR datos?");
			add = sc.next().toUpperCase();
			if (add.equals("AÑADIR") || add.equals("MODIFICAR") || add.equals("ELIMINAR")) {
				i++;
			} else {
				System.out.println(
						"El comando introducido no es coorecto, porfavor introduzca AÑADIR, MODIFICAR O ELIMINAR");
			}
		} while (i < 1);
		return add;
	}

	// METODOS DE OBTENCIÓN DE DATOS
	// METODOS PARA OBTENER LOS DATOS DE LA BASE DE DATOS

	// metodo para obtener la cantidad de una categoria
	public static Integer obtenerCantidad(String categoria, String subcategoria, BaseDatos base_datos) {
		Hashtable<String, Hashtable<String, Integer>> producto = base_datos.getStockSuper();
		Hashtable<String, Integer> subproducto = producto.get(categoria);
		Integer cantidad = subproducto.get(subcategoria);
		return cantidad;
	}

	// metodo para obtener el precio de una categoria
	public static Double obtenerPrecio(String categoria, String subcategoria, BaseDatos base_datos) {
		Hashtable<String, Hashtable<String, Double>> producto = base_datos.getPrecioSuper();
		Hashtable<String, Double> subproducto = producto.get(categoria);
		Double precio = subproducto.get(subcategoria);
		return precio;
	}

	// metodo para obtener el iva de una categoria
	public static Double obtenerIva(String categoria, String subcategoria, BaseDatos base_datos) {
		Hashtable<String, Hashtable<String, Double>> producto = base_datos.getIvaSuper();
		Hashtable<String, Double> subproducto = producto.get(categoria);
		Double iva = subproducto.get(subcategoria);
		return iva;
	}

}
