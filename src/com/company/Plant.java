package com.company;

import java.time.LocalDate;
import java.util.IllegalFormatException;

public class Plant {

    private static final String FILE_ITEM_DELIMITER = "\t";

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public static Plant parsePlant(String entry) throws PlantException {
        /**Dělení na dílčí části - jednotlivé zadané položky entry (jinak scanner.nextLine) budeme rozdělovat
        tabulátorem a každé postupně ukládat do pole (array) String[], odděleny jsou čárkou
         výstup bude -> {item1, item2, item3, item4, item5}*/
        String[] items = entry.split(FILE_ITEM_DELIMITER);

        if (items.length != 5) { throw new PlantException("Špatný počet položek! Řádek: "+entry); }

        String name = items[0];
        String notes = items[1];
        int frequencyOfWatering = Integer.parseInt(items[2]);
        LocalDate planted = LocalDate.parse(items[3]);
        LocalDate watering = LocalDate.parse(items[4]);

        return new Plant(name, notes, frequencyOfWatering, planted, watering);
    }

    //Method - preparation for writing into the file (output as String)
    public String prepareToWriteIn() {
        return getName() + FILE_ITEM_DELIMITER +
                getNotes() + FILE_ITEM_DELIMITER +
                getFrequencyOfWatering() + FILE_ITEM_DELIMITER +
                getPlanted() + FILE_ITEM_DELIMITER +
                getWatering();
    }

    //Constructor for setting all attributes
    public Plant(String name, String notes, int frequencyOfWatering, LocalDate planted, LocalDate watering){
        this.name = name;
        this.notes = notes;
        this.frequencyOfWatering = frequencyOfWatering;
        this.planted = planted;
        this.watering = watering;
    }

    //Note as blank string, watering set today
    public Plant(String name, LocalDate planted, int frequencyOfWatering){
        this.name = name;
        this.notes = "";
        this.planted = planted;
        this.watering = LocalDate.now();
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //User fill in only "name" of plant
    public Plant(String name){
        this.name = name;
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = 7;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setNotes(String notes) { this.notes = notes;}

    public String getNotes() { return notes;}

    public void setPlanted(LocalDate planted){
        this.planted = planted;
    }

    public LocalDate getPlanted(){
        return planted;
    }
    //Watering must be after the date of planting
    public void setWatering(LocalDate watering) throws PlantException{
        if (watering.isBefore(planted)) {
            throw new PlantException("Neplatné datum! " + getWatering());
        }
        this.watering = watering;
    }
    public LocalDate getWatering(){
        return watering;
    }

    //frequency must be greater than 0
    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) throw new PlantException("Zálivka zadaná na záporný počet nebo nula!");
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public int getFrequencyOfWatering(){
        return frequencyOfWatering;
    }

    public String getWateringInfo(){
        return ("Název květiny: "+getName()+", datum poslední zálivky: "+getWatering()+", datum další doporučené zálivky: "+getWatering().plusDays(7));
    }
}
