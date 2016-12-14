package com.Algoritimos;

import java.io.*;
import java.util.Scanner;

import com.Estatistica.Estatistica;
import com.Estruturas.Cidades;
import com.Estruturas.GerenciaRotas;
import com.ForcaBruta.ForcaBruta;
import com.Genetico.GA;
import com.Genetico.Populacao;
import java.util.ArrayList;

public class Projeto_Interdiciplinar {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		 
    	String[] linha;
		int n = 0,x,y;

		ArrayList<Double> tempos = new ArrayList<Double> ();
		Estatistica esta = new Estatistica();
		
		Cidades mapa = new Cidades();
		             
		try{
			 BufferedReader arquivo = new BufferedReader(new FileReader("/home/lucas/Documentos/dados.txt"));
			 
			 while(arquivo.ready()) {

				 linha = (arquivo.readLine()).split("	");
				 x = Integer.parseInt(linha[0]);
				 y = Integer.parseInt(linha[1]);

				 GerenciaRotas.addCidades(mapa.addCidades(n, x, y));
				 n++;
			 }
	        arquivo.close();
		}catch (Exception e) {
            System.out.println("Nome do arquivo errado\n");
		}
		int op = 5;

		while (op!=0){
			System.out.println("O numero de cidades é: "+n);
			System.out.println("Escolha o metodo para encontrar a rota");
			System.out.println("1 - Algoritimo genetico\n" +
					"2 - Força Bruta\n" +
					"3 - Relatório estatistico");

			Scanner ler = new Scanner(System.in);
			op = ler.nextInt();

			Populacao pop = new Populacao(100, true);

			long start = System.currentTimeMillis();

			pop = GA.evoluiPopulacao(pop);
			for (int i = 0; i < 100; i++) {
				pop = GA.evoluiPopulacao(pop);
			}

			switch (op){
				case 1:
					System.out.println("Solução G.A: "+pop.getFittest().getDistancia());
					System.out.println(pop.getFittest());
					System.out.println();
					break;
				case 2:
					System.out.println("Solução força Bruta");
					//long tempoFinal = System.currentTimeMillis();
					ForcaBruta brut = new ForcaBruta();
					brut.permuta(mapa, GerenciaRotas.todasCidades());

					int[] rota = brut.cidades();

					for (int i = 0; i < rota.length-1; i++) {
						System.out.print(rota[i]+"-");
					}
					System.out.println("0: "+brut.distancia());
					System.out.println();
					//System.out.println((double)(tempoFinal - start) / 1000d);
					break;
				case 3:
					System.out.println("---------Estatistica-------");
					System.out.println("1 - Executar teste novo:");
					System.out.println("2 - Executar teste memoria");
					op = ler.nextInt();
					switch(op){
						case 1: 
							for(int a=0;a<10;a++){
								long tempoFinal = System.currentTimeMillis();
								pop = GA.evoluiPopulacao(pop);
								for (int i = 0; i < 100; i++) {
									pop = GA.evoluiPopulacao(pop);
								}
								//System.out.println(pop.getFittest().getDistancia());
								tempos.add((double)(tempoFinal - start) / 1000d);
								//tempos.add(pop.getFittest().getDistancia());
							}
							esta.estatistica(tempos, tempos.size());
							System.out.println();
							break;
						case 2:
							esta.quiQuadrado();
							System.out.println();
							break;
					}
					
					break;
				default:
					break;
			}
		}
    }
}
