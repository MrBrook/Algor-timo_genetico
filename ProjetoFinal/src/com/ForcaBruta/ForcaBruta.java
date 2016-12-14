package com.ForcaBruta;

import java.util.ArrayList;
import com.Estruturas.Cidades;
import com.Estruturas.GerenciaRotas;

public class ForcaBruta {
	
	private static int[] p; 
	private static int[] melhor; 
	private static double aux = Double.POSITIVE_INFINITY; ;
	
	public static void permuta(Cidades mapa, ArrayList<Cidades> vertices) {
		
		p = new int[vertices.size()+1];
		melhor = new int[vertices.size()+1];
		permuta(mapa,vertices,1);
	}
			
	private static void permuta(Cidades mapa,ArrayList<Cidades> vertices, int n) {
		if (n==vertices.size()) {
			calculaDistancia(mapa);
							
		} else {	
			for (int i=0; i < vertices.size(); i++) {
				boolean achou = false;
				for (int j = 0; j < n; j++) {
					if (p[j]==vertices.get(i).nome) achou = true;
				}
				if (!achou) {
					p[n] = vertices.get(i).nome;
					permuta(mapa,vertices,n+1);
				}
			}

		} 
	} 
		
	private static void calculaDistancia(Cidades mapa) {
		
		ArrayList<Cidades> cidades = GerenciaRotas.todasCidades();
		
		int x,y;
		double soma = 0;
		for (int i=0; i < p.length-1; i++){
				
			x = Math.abs(cidades.get(p[i+1]).getX()-cidades.get(p[i]).getX());
			y = Math.abs(cidades.get(p[i+1]).getY()-cidades.get(p[i]).getY());
			
			soma+=Math.sqrt((x*x)+(y*y));
			
		}
		if(soma<aux){
			aux = soma;
			melhor = p;
		}
	}
	public static double distancia(){
        return aux;
    }
	public static int[] cidades(){
        return melhor;
    }
}