package com.Estruturas;

public class Cidades {
	public int nome;
	int x;
    int y;
    
    public Cidades(){
    	this.nome = 0;
        this.x = 0;
        this.y = 0;
    }
    
    public Cidades(int nome,int x, int y){
        this.nome = nome;
        this.x = x;
        this.y = y;
    }
    
    public Cidades addCidades(int nome,int x,int y){
    	Cidades a = new Cidades(nome,x,y);
    	return a;
    }
    public int getNome(){
        return this.nome;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    
    public double distanciaA(Cidades cidade){
        int DistanciaX = Math.abs(getX() - cidade.getX());
        int DistanciaY = Math.abs(getY() - cidade.getY());
        double distancia =(double) Math.sqrt( (DistanciaX*DistanciaX) + (DistanciaY*DistanciaY) );
        return distancia;
    }
    
    @Override
    public String toString(){
        return getNome()+"";
    }
}