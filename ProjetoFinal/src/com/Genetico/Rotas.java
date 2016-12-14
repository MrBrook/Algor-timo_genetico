package com.Genetico;

import java.util.ArrayList;
import java.util.Collections;
import com.Estruturas.Cidades;
import com.Estruturas.GerenciaRotas;

public class Rotas{

    @SuppressWarnings("rawtypes")
	private ArrayList rotas = new ArrayList<Cidades>();
    private double fitness = 0;
    private double distancia = 0;
    
    @SuppressWarnings("unchecked")
	public Rotas(){
        for (int i = 0; i < GerenciaRotas.numeroCidades(); i++) {
            rotas.add(null);
        }
    }
    
    public Rotas(@SuppressWarnings("rawtypes") ArrayList tour){
        this.rotas = tour;
    }

    public void generateIndividual() {
      
        for (int i = 0; i < GerenciaRotas.numeroCidades(); i++) {
        	setCidade(i, GerenciaRotas.getCidade(i));
        }
        Collections.shuffle(rotas);
    }

    public Cidades getCidade(int posicaoRota) {
        return (Cidades)rotas.get(posicaoRota);
    }

    @SuppressWarnings("unchecked")
	public void setCidade(int posicaoRota, Cidades cidade) {
        rotas.set(posicaoRota, cidade);
        fitness = 0;
        distancia = 0.0;
    }
    
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double) getDistancia();
        }
        return fitness;
    }
 
    public double getDistancia(){
        if (distancia == 0) {
            double distanciaRota = 0.0;
            for (int i=0; i < tamanhoRota(); i++) {

                Cidades origem = getCidade(i);
                Cidades destino;
                
                if(i+1 < tamanhoRota()) destino = getCidade(i+1);
                else   destino = getCidade(0);

                distanciaRota += origem.distanciaA(destino);
            }
            distancia = distanciaRota;
        }
        return distancia;
    }

    public int tamanhoRota() {
        return rotas.size();
    }
    
    public boolean containsCity(Cidades cidade){
        return rotas.contains(cidade);
    }
    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < tamanhoRota(); i++) {
            geneString += getCidade(i)+"-";
        }
        geneString+=""+geneString.charAt(0);
        return geneString;
    }
}