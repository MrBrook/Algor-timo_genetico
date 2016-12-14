package com.Genetico;

public class Populacao {

    Rotas[] rotas;

       public Populacao(int tamanhoPopulacao, boolean inicial) {
        rotas = new Rotas[tamanhoPopulacao];
        if (inicial) {
            for (int i = 0; i < tamaPopulacao(); i++) {
                Rotas novasRotas = new Rotas();
                novasRotas.generateIndividual();
                salvaRota(i, novasRotas);
            }
        }
    }
    public void salvaRota(int index, Rotas rota) {
        rotas[index] = rota;
    }

    public Rotas getRota(int index) {
        return rotas[index];
    }
    public Rotas getFittest() {
        Rotas fittest = rotas[0];
        for (int i = 1; i < tamaPopulacao(); i++) {
            if (fittest.getFitness() <= getRota(i).getFitness()) {
                fittest = getRota(i);
            }
        }
        return fittest;
    }
    public int tamaPopulacao() {
        return rotas.length;
    }
}