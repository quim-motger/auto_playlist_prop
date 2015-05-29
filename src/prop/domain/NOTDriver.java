package prop.domain;

import prop.PropException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * NOTDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 17/04/15
 */
public class NOTDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Complex Relation NOT");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        SongSet ss = new SongSet();
        UserSet us = new UserSet();
        ComplexRelation r = null;
        SimpleRelation r1 = null;
        SimpleRelation r2 = null;
        Song s1 = null;
        Song s2 = null;
        User u = null;
        Calendar birthday = Calendar.getInstance();
        ArrayList<Song> songs;

        printInfo();

        int i = -1;
        while (i!=0) {

            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    r = new NOT(r1,ss);
                    break;
                case 2:
                    songs = r.evaluate();
                    for (Song song : songs) {
                        System.out.println(song.getTitle() + " " + song.getArtist());
                    }
                    break;
                case 3:/*
                    try {
                        System.out.println(r.evaluateUser(u));
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }*/
                    break;
                case 4:
                    try {
                        r1 = new SimpleRelation(ss, us, in.next(), in.next());
                    } catch (PropException e) {
                        printError(e);
                    }
                    break;
                case 5:
                    try {
                        s1 = new Song(in.next(), in.next(), in.next(), in.nextInt(), Genre.valueOf(in.next()),
                                Genre.valueOf(in.next()), in.nextInt());
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        s2 = new Song(in.next(), in.next(), in.next(), in.nextInt(), Genre.valueOf(in.next()),
                                Genre.valueOf(in.next()), in.nextInt());
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    u = new User(in.next(), Gender.valueOf(in.next()), birthday);
                    break;
                case 8:
                    birthday.set(in.nextInt(), in.nextInt(), in.nextInt());
                    break;
                default:
                    printInfo();
            }
        }
    }

    private static void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    private static void printInfo() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    NOT(Relation r1)\n");
        System.out.print("2:    boolean evaluateSongs(Song s1, Song s2)\n");
        System.out.print("3:    boolean evaluateUser(User u)\n");
        System.out.print("4:    r1 = SimpleRelation(String type, String attribute, String value)\n");
        System.out.print("5:    s1 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        System.out.print("6:    s2 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)\n");
        System.out.print("7:    u = new User(String name, Gender gender, Calendar birthdate, CountryCode country)\n");
        System.out.print("8:    birthday.set(int year,int month,int date)\n");
    }
}
