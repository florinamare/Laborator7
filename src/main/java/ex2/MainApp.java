package ex2;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import ex1.Carte;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MainApp {
    public static void main(String[] args) {
        Set<InstrumentMuzical> set = new HashSet<>();
        Chitara c1 = new Chitara("YAMAHA", 3500, TipChitara.ACUSTICA, 6);
        Chitara c2 = new Chitara("IBANEZ", 400, TipChitara.ELECTRICA, 7);
        Chitara c3 = new Chitara("ALHAMBRA", 350, TipChitara.CLASICA, 6);

        SetTobe s1 = new SetTobe("RITMICE", 300, TipTobe.ACUSTICE, 8, 5);
        SetTobe s2 = new SetTobe("SONIC", 800, TipTobe.ELECTRONICE, 7, 12);
        SetTobe s3 = new SetTobe("VIBRANTE", 15000, TipTobe.ELECTRONICE, 26, 45);
        SetTobe s5 = new SetTobe("ABABBA", 200, TipTobe.ACUSTICE, 20, 7);


        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s5);

        //2. scriere
        writeFile(set);

        //3.citire
        set = readFile();
        set.forEach(System.out::println);


        System.out.println("\n4. Implementarea folosita pentru Set: "+ set.getClass());

        System.out.println("\n5. Verificare daca permite dublicata:");
        SetTobe s4 = new SetTobe("VIBRANTE", 15000, TipTobe.ELECTRONICE, 26, 45);
        if(set.add(s4))
        {
            System.out.println("\nA fost adaugat");
        }
        else System.out.println("\nSetul de tobe nu a fost adaugat pentru ca a fost gasit duplicat");


        System.out.println("\n6. Stergere pret>3000 :");
        set.removeIf(instrumentMuzical -> instrumentMuzical.getPret()>3000);
        set.stream().forEach(System.out::println);


        System.out.println("\n7. Afisare folosind instanceof: ");
        set.stream()
                .filter(s-> s instanceof Chitara)
                .forEach(System.out::println);


        System.out.println("\n8. Afisare tobe folosind getClass: ");
        set.stream()
                .filter(s-> s.getClass()== SetTobe.class)
                .forEach(System.out::println);

        System.out.println("\n9. Afisare date chitara care are cele mai multe corzi: ");
        Optional<Chitara> mostCorzi = set.stream()
                .filter(s->s.getClass()== Chitara.class)
                .map(m->(Chitara)m)
                .max(Comparator.comparing(s->(s.getNrCorzi())));
                mostCorzi.ifPresent(
                chitara-> System.out.println("\nChitara cu cele mai multe corzi: " + mostCorzi.toString())

        );

        System.out.println("\n10. Afisare date tobe acustice: ");
        set.stream()
                .filter(instrument -> instrument instanceof SetTobe)
                .map(instrument -> (SetTobe) instrument)
                .filter(toba -> toba.getTipTobe() == TipTobe.ACUSTICE)
                .sorted(Comparator.comparingInt(SetTobe::getNrTobe))
                .forEach(System.out::println);




    }

    public static void writeFile(Set<InstrumentMuzical> set) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            File file = new File("instrumente.json");
            mapper.writeValue(file, set);
            System.out.println("Datele au fost scrise în fișierul JSON cu succes.");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișierul JSON: " + e.getMessage());
        }
    }

    public static Set<InstrumentMuzical> readFile() {
        try {
            File file = new File("instrumente.json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new com.fasterxml.jackson.core.type.TypeReference<Set<InstrumentMuzical>>() {});
        } catch (IOException e) {
            System.err.println("Eroare la citirea din fișierul JSON: " + e.getMessage());
        }
        return new HashSet<>();
    }



}
