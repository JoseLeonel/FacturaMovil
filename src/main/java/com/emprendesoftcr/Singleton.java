package com.emprendesoftcr;

public final class Singleton {
	private static final Singleton singleston = new Singleton();
	private static int cantidad;
	
	private Singleton() {
	   System.out.println("hola h, he sido llamado una vez");	
	}
	
	public static Singleton obtenerSingleton() {
		cantidad ++;
		return singleston;
		
	}
	public static void vecesLlamado() {
		 System.out.println("Se ha llamado :" + cantidad);
		
	}
}
