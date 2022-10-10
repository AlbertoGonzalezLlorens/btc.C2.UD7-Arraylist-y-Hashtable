package ud07ej03;

import java.util.Hashtable;

public class BaseDatos {
	private Hashtable<String,Hashtable<String,Integer>> stockSuper;
	private Hashtable<String,Hashtable<String,Double>> precioSuper;
	private Hashtable<String,Hashtable<String,Double>> ivaSuper;
	final private Hashtable<String,Hashtable<String,Integer>> ALMACEN = new Hashtable<String,Hashtable<String,Integer>>();
	final private Hashtable<String,Hashtable<String,Double>> PRECIO = new Hashtable<String,Hashtable<String,Double>>();
	final private Hashtable<String,Hashtable<String,Double>> IVA = new Hashtable<String,Hashtable<String,Double>>();
	
					
	
	public BaseDatos() {
		//base de datos de cantidades
		this.stockSuper= ALMACEN;
		this.precioSuper = PRECIO;
		this.ivaSuper = IVA;
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
			
				
		}



	public Hashtable<String, Hashtable<String, Integer>> getStockSuper() {
		return stockSuper;
	}



	public void setStockSuper(Hashtable<String, Hashtable<String, Integer>> stockSuper) {
		this.stockSuper = stockSuper;
	}



	public Hashtable<String, Hashtable<String, Double>> getPrecioSuper() {
		return precioSuper;
	}



	public void setPrecioSuper(Hashtable<String, Hashtable<String, Double>> precioSuper) {
		this.precioSuper = precioSuper;
	}



	public Hashtable<String, Hashtable<String, Double>> getIvaSuper() {
		return ivaSuper;
	}



	public void setIvaSuper(Hashtable<String, Hashtable<String, Double>> ivaSuper) {
		this.ivaSuper = ivaSuper;
	}
		
	
			
					
			
	
		
	
			
			
		
		
		
	}
