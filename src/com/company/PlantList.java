package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlantList {
    private ArrayList<Plant> listOfPlants = new ArrayList<>();

    //Method that load items from file
    public void loadFromFile(String inputFile) {
        try (
                Scanner scanner = new Scanner(new BufferedReader(new FileReader(inputFile)))) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                listOfPlants.add(Plant.parsePlant(nextLine));
            }
            //Exception if the file is not found
        } catch (
                FileNotFoundException e) {
            System.err.println("Soubor " + inputFile + " nebyl nalezen" + e.getLocalizedMessage());
            //Exception if the entry items are greater/lower than 5
        } catch (PlantException e) {
            System.err.println("Chyba v souboru " + inputFile + ": " + e.getLocalizedMessage());
        }
    }

    //Method that write into file
    public void writeInFile(String outputFile) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {
            for (Plant p: listOfPlants) {
                writer.println(p.prepareToWriteIn());
            }
        } catch (IOException e) {
            System.err.println("Chyba při zápisu souboru "+outputFile+": "+e.getLocalizedMessage());
        }

    }

    public void addPlant(Plant plant){
        listOfPlants.add(plant);
    }

    public Plant getPlant(int position) {
        return listOfPlants.get(position);
    }

    public void removePlant(int position){
        listOfPlants.remove(listOfPlants.get(position));
    }

    public void clearList(){
        listOfPlants.clear();
    }

    public int sizeOfList(){
        return listOfPlants.size();
    }

}
