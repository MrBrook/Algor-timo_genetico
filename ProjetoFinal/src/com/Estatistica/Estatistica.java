package com.Estatistica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Estruturas.GerenciaRotas;

import java.util.Arrays;
import java.util.Collections;

public class Estatistica {


    public static void estatistica(List<Double> notas, int tam){

    	double med=0;
    	double desV;
        Collections.sort(notas);

        med=media(notas,tam);
        desV=desvioPadrao(notas,tam,med);

        testeAciAba(notas,tam,med);
        System.out.println("Média: " +med);
        System.out.println("Desvio Padrão: " +desV);
    }
    public static double media(List<Double> notas, int tam){
        int i;
        double med=0.0;
        for(i=0;i<tam;i++){
            med+=notas.get(i);
        }
        return 	med/tam;
    }
    public static double desvioPadrao(List<Double> notas,int tam,double media){
        double desV=0;
        for (int i = 0; i < tam; i++){
            desV+=(double)Math.pow(notas.get(i)-media,2);
        }
        return Math.sqrt(desV/(tam));
    }
    public static void testeAciAba(List<Double> notas,int tam, double media){
        int aux1=0,aux2=0;
        for(int i=0; i<tam; i++ ){
            if (notas.get(i)<=media) aux1+=1;
            else aux2+=1;

        }
        System.out.println("Abaixo da Média: " +aux1);
        System.out.println("Acima da Média: " +aux2);
    }
    public static void quiQuadrado() {
    	
        List<Double> temposDistantes = new ArrayList<Double> ();
        List<Double>  temposProximos = new ArrayList<Double> ();

        double somaDistantes = 0;
        double somaProximas = 0;
        String linha;
        
        try{
			 BufferedReader arquivo = new BufferedReader(new FileReader("/home/lucas/Documentos/dadosEsta/tempoP.txt"));
			 int i = 0;
			 
			 while(arquivo.ready()) {
				 linha = arquivo.readLine();
				 temposProximos.add(Double.parseDouble(linha));
				 somaProximas+=temposProximos.get(i);
				 i++;
			 }
	        arquivo.close();
	        i=0;
	        arquivo = new BufferedReader(new FileReader("/home/lucas/Documentos/dadosEsta/tempoL.txt"));
			 
			 while(arquivo.ready()) {
				 linha = arquivo.readLine();
				 temposDistantes.add(Double.parseDouble(linha));
				 somaDistantes+=temposProximos.get(i);
				 i++;
			 }
	        arquivo.close();
		}catch (Exception e) {
           System.out.println("Nome do arquivo errado\n");
		}
        
        System.out.println("Cidades Distantes\n");
        estatistica(temposDistantes,temposDistantes.size());
        System.out.println();
        System.out.println("Cidades Proximas\n");
        estatistica(temposProximos,temposDistantes.size());
        System.out.println();
        
        double media = (somaDistantes + somaProximas) / 2000;

        double o12 = 0;
        double o22 = 0;

        for(int i = 0; i < 1000; i++) {
            if(temposDistantes.get(i) > media)
                o22++;
            if(temposProximos.get(i) > media)
                o12++;
        }

        double o11 = 1000 - o12;
        double o21 = 1000 - o22;

        double totalProximo = o11 + o12;
        double totalDistante = o21 + o22;
        double totalAbaixoIgual = o21 + o11;
        double totalAcima = o22 + o12;
        double totaltotal = totalProximo + totalDistante;

        double e11, e12, e21, e22;

        e11 = (totalProximo * totalAbaixoIgual) / totaltotal;
        e12 = (totalProximo * totalAcima) / totaltotal;
        e21 = (totalDistante * totalAbaixoIgual) / totaltotal;
        e22 = (totalDistante * totalAcima) / totaltotal;

        double xQuadrado = ((Math.pow((o11 - e11),2))/e11) + ((Math.pow((o12 - e12),2))/e12) + ((Math.pow((o21 - e21),2))/e21) +
                ((Math.pow((o22 - e22),2))/e22);

        if(xQuadrado < 6.6355)
            System.out.println("Aceita-se H0");
        else
            System.out.println("Rejeita-se H0");
    }
}
