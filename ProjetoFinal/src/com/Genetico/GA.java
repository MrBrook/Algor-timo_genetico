package com.Genetico;

import com.Estruturas.Cidades;

public class GA {

    private static final double indiceMutacao = 0.015;
    private static final int tamanhoRota = 5;
    private static final boolean elitismo = true;

  
    public static Populacao evoluiPopulacao(Populacao pop) {
        Populacao novaPopulacao = new Populacao(pop.tamaPopulacao(), false);

        int elitismOffset = 0;
        if (elitismo) {
            novaPopulacao.salvaRota(0, pop.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < novaPopulacao.tamaPopulacao(); i++) {

            Rotas pais1 = selecaoRota(pop);
            Rotas pais2 = selecaoRota(pop);
            Rotas filhos = crossover(pais1, pais2);
       
            novaPopulacao.salvaRota(i, filhos);
        }

        for (int i = elitismOffset; i < novaPopulacao.tamaPopulacao(); i++) {
            mutacao(novaPopulacao.getRota(i));
        }

        return novaPopulacao;
    }

    public static Rotas crossover(Rotas pais1, Rotas pais2) {
        Rotas filhos = new Rotas();

        int startPos = (int) (Math.random() * pais1.tamanhoRota());
        int endPos = (int) (Math.random() * pais1.tamanhoRota());

        for (int i = 0; i < filhos.tamanhoRota(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                filhos.setCidade(i, pais1.getCidade(i));
            }
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    filhos.setCidade(i, pais1.getCidade(i));
                }
            }
        }

        for (int i = 0; i < pais2.tamanhoRota(); i++) {
        	if (!filhos.containsCity(pais2.getCidade(i))) {
                for (int ii = 0; ii < filhos.tamanhoRota(); ii++) {
                    if (filhos.getCidade(ii) == null) {
                        filhos.setCidade(ii, pais2.getCidade(i));
                        break;
                    }
                }
            }
        }
        return filhos;
    }
    private static void mutacao(Rotas rota) {
       for(int rotaPos1=0; rotaPos1 < rota.tamanhoRota(); rotaPos1++){
           if(Math.random() < indiceMutacao){
              int rotaPos2 = (int) (rota.tamanhoRota() * Math.random());

                Cidades cidade1 = rota.getCidade(rotaPos1);
                Cidades cidade2 = rota.getCidade(rotaPos2);
                
                rota.setCidade(rotaPos2, cidade1);
                rota.setCidade(rotaPos1, cidade2);
            }
        }
    }
    private static Rotas selecaoRota(Populacao pop) {
       
        Populacao nomeRota = new Populacao(tamanhoRota, false);
        
        for (int i = 0; i < tamanhoRota; i++) {
            int id = (int) (Math.random() * pop.tamaPopulacao());
            nomeRota.salvaRota(i, pop.getRota(id));
        }
        Rotas fittest = nomeRota.getFittest();
        return fittest;
    }
}