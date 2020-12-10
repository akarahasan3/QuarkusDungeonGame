package com.codecta.academy2021.disneylands;

import com.codecta.academy2021.disneylands.characters.DisneyCharacter;
import com.codecta.academy2021.disneylands.characters.DummyCharacter;
import com.codecta.academy2021.disneylands.characters.MickeyCharacter;
import com.codecta.academy2021.disneylands.characters.MinnieCharacter;
import com.codecta.academy2021.disneylands.lands.AdventureLand;
import com.codecta.academy2021.disneylands.lands.Disneyland;
import com.codecta.academy2021.disneylands.lands.FantasyLand;
import com.codecta.academy2021.disneylands.lands.MickeyTown;
import com.codecta.academy2021.disneylands.netflix.Movie;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", true, "platform");
        options.addOption("s", true, "genre");

        CommandLineParser parser = new DefaultParser();
        CommandLine cl = null;
        try {
            cl = parser.parse(options, args);
            ObjectMapper mapper = new XmlMapper();
            InputStream inputStream = new FileInputStream("C:\\Users\\ajdin\\Documents\\academy-2021-main\\disneylands\\src\\main\\resources\\movies.xml");
            TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>(){};
            List<Movie> movies = mapper.readValue(inputStream, typeReference);
            switch(cl.getOptionValue("i")){
                case "Netflix":
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Welcome back!\n");
                    boolean introState1 = true;
                    /*for(Movie m : movies){
                        if(!m.getGenre().contains(cl.getOptionValue("s"))) {
                            System.out.println("\nId: " + m.getId()
                                    + "\nTitle: " + m.getTitle()
                                    + "\nYear: " + m.getYear()
                                    + "\nGenre: " + m.getGenre()
                                    + "\nDescription: " + m.getDescription()
                                    + "\nDirector: " + m.getDirector());
                        }
                    }*/
                    do {
                        System.out.printf("Please select one of the options below:\n");
                        System.out.printf("1. Show all movies:\n");
                        System.out.printf("2. Search by genres:\n");
                        System.out.printf("3. Filter out a particular genre:\n");
                        System.out.println();
                        int selection = scanner1.nextInt();

                        switch (selection)
                        {
                            case 1:
                                System.out.printf("Here are our movies:\n");
                                for(Movie m : movies){
                                    if(!m.getGenre().contains(cl.getOptionValue("s"))) {
                                        System.out.println("\nId: " + m.getId()
                                                + "\nTitle: " + m.getTitle()
                                                + "\nYear: " + m.getYear()
                                                + "\nGenre: " + m.getGenre()
                                                + "\nDescription: " + m.getDescription()
                                                + "\nDirector: " + m.getDirector());
                                    }
                                }
                                break;
                            case 2:
                                System.out.printf("Here are our genres:\n");
                                int counter = 1;
                                for(Movie m : movies){
                                    if(!m.getGenre().contains(cl.getOptionValue("s"))) {
                                        System.out.print(counter + ". ");
                                        System.out.println(m.getGenre());
                                        counter++;
                                    }
                                }/*
                                int selection2 = scanner1.nextInt();
                                switch (selection2):*/
                                break;
                            case 3:
                                System.out.printf("Enter a genre that you'd like to filter out: ");
                                String selectedGenre = scanner1.next();
                                System.out.printf("Here are your movies:\n");
                                for(Movie m : movies){
                                    if(!m.getGenre().contains(cl.getOptionValue("s")) && !m.getGenre().contains(selectedGenre)) {
                                        System.out.println("\nId: " + m.getId()
                                                + "\nTitle: " + m.getTitle()
                                                + "\nYear: " + m.getYear()
                                                + "\nGenre: " + m.getGenre()
                                                + "\nDescription: " + m.getDescription()
                                                + "\nDirector: " + m.getDirector());
                                    }
                                }
                                break;
                            default:
                                System.out.println("Goodbye!");
                                introState1 = false;
                                break;
                        }
                    } while (introState1);
                    break;
                case "Disney":
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Welcome, visitor. Please enter your name: ");
                    String visitorName = scanner.next();
                    boolean introState = true;
                    do {
                        System.out.printf("Welcome, %s, please select one of our fun parks:\n", visitorName);

                        List<Disneyland> disneylands = new ArrayList<Disneyland>();
                        disneylands.add(new MickeyTown());
                        disneylands.add(new AdventureLand());
                        disneylands.add(new FantasyLand());
                        for (int i=0; i<disneylands.size(); i++) {
                            System.out.printf("[%d.] Name: %s\nDescription: %s\n", i+1, disneylands.get(i).getName(), disneylands.get(i).getDescription());
                        }
                        System.out.println();
                        int selection = scanner.nextInt();

                        switch (selection)
                        {
                            case 1:
                                System.out.printf("Here are our characters in %s.\n", disneylands.get(selection-1).getName());
                                presentMickeyTownCharacters();
                                break;
                            case 2:
                            case 3:
                            default:
                                System.out.println("Not yet implemented!");
                                System.out.println("You have finished this journey! Visit us again!");
                                introState = false;
                                break;
                        }
                    } while (introState);
                    break;
                default:
                    System.out.println("Invalid Argument!");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void presentMickeyTownCharacters() {
        List<DisneyCharacter> characters = new ArrayList<>();
        characters.add(new MickeyCharacter());
        characters.add(new MinnieCharacter());
        characters.add(new DummyCharacter());
        for (DisneyCharacter character :
                characters) {
            System.out.printf("My name is: %s\nAnd my greeting is: \n%s\nAnd my picture is: %s\n\n", character.getName(), character.welcomes(), character.picture());
        }
    }
    private static void presentAdventureLandCharacters() {
        List<DisneyCharacter> characters = new ArrayList<>();
        characters.add(new MickeyCharacter());
        characters.add(new MinnieCharacter());
        characters.add(new DummyCharacter());
        for (DisneyCharacter character :
                characters) {
            System.out.printf("My name is: %s\nAnd my greeting is: \n%s\nAnd my picture is: %s\n\n", character.getName(), character.welcomes(), character.picture());
        }
    }
    private static void presentFantasyLandCharacters() {
        List<DisneyCharacter> characters = new ArrayList<>();
        characters.add(new MickeyCharacter());
        characters.add(new MinnieCharacter());
        characters.add(new DummyCharacter());
        for (DisneyCharacter character :
                characters) {
            System.out.printf("My name is: %s\nAnd my greeting is: \n%s\nAnd my picture is: %s\n\n", character.getName(), character.welcomes(), character.picture());
        }
    }
}
