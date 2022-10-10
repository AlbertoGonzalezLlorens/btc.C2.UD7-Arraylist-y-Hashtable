package ud07ej01;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Ud07Ej01app {
	public static void main(String[] args) {
		pedirValores();
	}
	
	//pongo todo el codigo para liberar el main
	public static void pedirValores() {
		double v_media=0;
		//Creo la hashtable
		Hashtable<String,Double> contenedor = new Hashtable<String,Double>();
		//Objeto pedir por comando
		Scanner sc = new Scanner(System.in);
		System.out.print("Cuantos alumnos va a introducir? ");
		int n_alumnos = sc.nextInt();
		for (int i=0;i<n_alumnos;i++) {
			System.out.print("Introduzca el nombre del alumno: ");
			String notas_alumno = sc.next();
			System.out.print("Cuantas notas va a introducir? ");
			int n_notas = sc.nextInt();
			v_media = calcularMedia(n_notas,sc);
			contenedor.put(notas_alumno, v_media);
		}
		sc.close();
		
		Set<String> keys = contenedor.keySet();
		for (String key: keys) {
			System.out.println("La media de "+ key +" es de "+contenedor.get(key));
		}
		
	}
	//Metodo que calcula la media
	public static double calcularMedia(int n_notas, Scanner sc) {
		//creo una variable donde se va a guardar la media
		double media = 0.0;
		//creo una variable donde se ira guardando la suma de las notas
		double sum_notas = 0;
		ArrayList<Double> notas = new ArrayList<>();
		for (int i=0; i<n_notas; i++) {
			System.out.print("Introduzca el valor de la nota "+(i+1)+": ");
			double nota = sc.nextDouble();
			notas.add(nota);
			sum_notas+=nota;
		}
		media = sum_notas/n_notas;
		return media;
	}
	
	
}
