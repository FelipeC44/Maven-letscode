package br.com.letscode.java;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//1 - Quem foi o ator mais jovem a ganhar um Oscar?
//2 - Quem foi a atriz que mais vezes foi premiada?
//3 - Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?
//4 - Quais atores ou atrizes receberam mais de um Oscar? Elabore uma única estrutura contendo atores e atrizes.
//5 - Quando informado o nome de um ator ou atriz, dê um resumo de quantos prêmios ele/ela recebeu e liste

public class Aplicacao {

    private final FileReader readerFemale;
    private final FileReader readerMale;

    public Aplicacao() throws IOException {

        this.readerFemale = new FileReader("oscar_age_female.csv");
        this.readerMale = new FileReader("oscar_age_male.csv");

    }
        public static void main(String[] args) throws IOException {

            Aplicacao app = new Aplicacao();

            app.atorMaisJovem();
            app.atrizMaisPremiada();
            app.jovemMaisPremiada();
            app.multiPremiados();

    }

    private void atorMaisJovem() {
        System.out.println("#1 - ator mais jovem");
        this.readerMale.getOscarList().stream()
                .min(Comparator.comparingInt(Oscar::getIdade))
                .ifPresent(x -> System.out.printf("O ator %s foi o mais jovem premiado a receber o Oscar "
                        + "pelo filme %s aos %d anos em %d", x.getNome(), x.getFilme(), x.getIdade(), x.getAno()));

    }

    private void atrizMaisPremiada() {
        System.out.println("\n#2 - atriz mais premiada");
        List<String> names = this.readerFemale.getOscarList().stream()
                .map(Oscar::getNome)
                .collect(Collectors.toList());
        this.readerFemale.getOscarList().stream()
                .max(Comparator.comparingInt(o -> Collections.frequency(names, o.getNome())))
                .ifPresent(o -> System.out.println(o.getNome()));
    }
    private void jovemMaisPremiada() {
        System.out.println("#3 - jovem atriz mais premiada");
        Map<String, Long> mapaNomeQtd = this.readerFemale.getOscarList().stream()
                .filter(o -> o.getIdade() >= 20 && o.getAno() <= 30)
                .collect(Collectors.groupingBy(Oscar::getNome, Collectors.counting()));
        mapaNomeQtd
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(e -> mapaNomeQtd.entrySet().stream()
                        .filter(e1 -> e1.getValue().equals(e.getValue()))
                        .forEach(e1 -> System.out.printf("%s - %d%n", e1.getKey(), e1.getValue()))
                );
    }
        private void multiPremiados() {
            System.out.println("#4 - atores ou atrizes com mais de um prêmio");
            List<Oscar> todosList = new ArrayList<>();
            todosList.addAll(this.readerFemale.getOscarList());
            todosList.addAll(this.readerMale.getOscarList());
            System.out.println("São :" + todosList);

    }
}