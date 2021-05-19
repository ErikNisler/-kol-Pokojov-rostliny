package com.company;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {

    public static final String INPUT_FILE = "files/kvetiny.txt";
    public static final String OUTPUT_FILE = "files/vystup.txt";

    public static void main(String[] args) {

        Plant plant1 = new Plant("Fialka", "nová",3,LocalDate.of(2021,5,20), LocalDate.of(2021,6,1));
        PlantList plantList = new PlantList();

        System.out.println("VÝJIMKY");
        //Výjimky
        try {
            plant1.setFrequencyOfWatering(-3);
        } catch (PlantException e) {
            System.err.println("Chyba! "+e.getLocalizedMessage());
        }

        try {
            plant1.setWatering(LocalDate.of(2021,5,1));
        } catch (PlantException e) {
            System.err.println("Chyba! " + e.getLocalizedMessage());
        }

        /**Načtěte seznam květin ze souboru kvetiny.txt.*/
        plantList.loadFromFile("files/kvetiny.txt");

        /**Vypište na obrazovku informace o zálivce pro všechny květiny.*/
        System.out.println("Vypiš info o zálivce pro všechny květiny: ");
        for (int i = 0; i < plantList.sizeOfList(); i++) {
            Plant plant = plantList.getPlant(i);
            System.out.println(plant.getWateringInfo());
        }

        System.out.println();
        /**Přidejte dvě nové květiny do seznamu. Jednu květinu odeberte.*/
        plantList.addPlant(new Plant("Rododendron"));
        plantList.addPlant(new Plant("Filodendron","Krásná květina",5, LocalDate.of(2021,5,10), LocalDate.of(2021,5,18)));
        plantList.removePlant(0);

        /**Uložte seznam květin do nového souboru a ověřte, že je jeho obsah správný. Výsledný soubor by měl vypadat takto: vystup.txt.*/

        plantList.writeInFile("files/vystup.txt");

        /**Vyzkoušejte opětovné načtení vygenerovaného souboru.*/

        plantList.loadFromFile("files/vystup.txt");

         }
}
