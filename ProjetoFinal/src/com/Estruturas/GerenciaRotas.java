package com.Estruturas;

import java.util.ArrayList;

public class GerenciaRotas {

    @SuppressWarnings("rawtypes")
	private static ArrayList CidadesDestino = new ArrayList<Cidades>();

    @SuppressWarnings("unchecked")
	public static void addCidades(Cidades cidade) {
        CidadesDestino.add(cidade);
    }
    
    public static Cidades getCidade(int index){
        return (Cidades)CidadesDestino.get(index);
    }
    @SuppressWarnings("unchecked")
	public static ArrayList<Cidades> todasCidades(){
        return CidadesDestino;
    }
    
    public static int numeroCidades(){
        return CidadesDestino.size();
    }
}