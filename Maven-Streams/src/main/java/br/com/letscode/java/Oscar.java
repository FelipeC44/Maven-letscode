package br.com.letscode.java;

import lombok.Value;

import static java.lang.Integer.parseInt;

@Value
public class Oscar {
    //1.Quem foi o ator mais jovem a ganhar um Oscar?
    //2.Quem foi a atriz que mais vezes foi premiada?
    //3.Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?
    //4.Quais atores ou atrizes receberam mais de um Oscar? Elabore uma única estrutura contendo atores e atrizes.
    //5.Quando informado o nome de um ator ou atriz, dê um resumo de quantos prêmios ele/ela recebeu e liste ano, idade e nome de cada filme pelo qual foi premiado(a).


     Integer indice;
     Integer ano;
     Integer idade;
     String nome;
     String filme;

    public static Oscar fromLine(String line){
        String[] split = line.split("; ");
        return new Oscar(
                parseInt(split[0]),
                parseInt(split[1]),
                parseInt(split[2]),
                split[3],
                split[4]
        );
    }
}



