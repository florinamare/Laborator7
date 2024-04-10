package ex1;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;
public class MainApp {
    public static void main(String[] args) {

        Map<Integer, Carte> map = new HashMap<Integer, Carte>();
        map = readFile();

        System.out.println("\n1. Afisare colectie: ");
        var entrySet =map.entrySet();
        entrySet.stream()
                .forEach(System.out::println);

        System.out.println("\n2. Stergere o carte din colectie ");
        for (Map.Entry<Integer, Carte> entry:entrySet){
            if(entry.getKey()==2){
                map.remove(2);
                System.out.println("A fost stears id= "+2);
                break;
            }
        }

        //map.remove(2);
        entrySet.stream().forEach(System.out::println);


        System.out.println("\n3. Adaugare carte in colectie: ");
        map.putIfAbsent(7,new Carte("Carrie", "Stephen King", 1974));
        entrySet.stream().forEach(System.out::println);

        System.out.println("\n4. Salvare modificari in JSON: ");
        writeFile(map);

        System.out.println("\n5. Extragere cărțile autorului Yual Noah Harari: ");
        Set<Carte> set = map.values().stream()
                .filter(carte -> "Yuval Noah Harari".equals(carte.autorul()))
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("\n6. Ordonare dupa titlu: ");
        map.values().stream()
                .sorted(Comparator.comparing(Carte::titlul))
                .forEach(System.out::println);

        System.out.println("\n7. Cautare a celei mai vechi cărți în toată colecția: ");
        Optional<Carte> carteveche = map.values().stream().min(Comparator.comparing(Carte::anul));
        carteveche.ifPresent(carte2 -> System.out.println("Cea mai veche carte din toată colecția: " + carte2));//ifPresentOrElse

    }


    public static Map<Integer, Carte>readFile(){
        try {
            File file=new File("src/main/resources/carti.json");
            ObjectMapper mapper=new ObjectMapper();
            //mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
            return mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {});
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

   public static void writeFile(Map<Integer, Carte> list){
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/carti.json");
            mapper.writeValue(file,list);
            System.out.println("A fost scris in fisier!");

        }catch (IOException e){
            e.printStackTrace();
        }
   }
}
