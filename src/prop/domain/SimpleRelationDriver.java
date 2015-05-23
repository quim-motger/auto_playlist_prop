package prop.domain;

import prop.PropException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Simple Relation driver
 * @author joaquim.motger
 */
public class SimpleRelationDriver {
    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Simple Relation Driver");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        Relation r = null;
        Song s1 = null;
        Song s2 = null;
        User u = null;
        ArrayList<Song> songs;
        UserSet us = new UserSet();
        SongSet ss = new SongSet();

        int i = -1;
        printInfoComplete();
        while (i != 0) {
            i = in.nextInt();
            switch(i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    try {
                        songs = r.evaluate();
                        for (Song song : songs) {
                            System.out.println(song.getTitle() + " " + song.getArtist());
                        }
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:/*
                    try {
                        if (r.evaluateUser(u)) System.out.print("true\n");
                        else System.out.print("false\n");
                    } catch (PropException e) {
                        System.err.println(e.getMessage());
                    }*/
                    break;
                case 4:
                    String type = in.next();
                    String attribute = in.next();
                    String value = in.next();
                    r = new SimpleRelation(ss,us,attribute, value);
                    break;
                case 5:
                    String title = in.next();
                    String artist = in.next();
                    String album = in.next();
                    int y = in.nextInt();
                    Genre genre = Genre.getGenreById(in.nextInt());
                    Genre subgenre = Genre.getGenreById(in.nextInt());
                    int duration = in.nextInt();
                    s1 = new Song(title, artist, album, y, genre, subgenre, duration);
                    break;
                case 6:
                    String title2 = in.next();
                    String artist2 = in.next();
                    String album2 = in.next();
                    int y2 = in.nextInt();
                    Genre genre2 = Genre.getGenreById(in.nextInt());
                    Genre subgenre2 = Genre.getGenreById(in.nextInt());
                    int duration2 = in.nextInt();
                    s2 = new Song(title2, artist2, album2, y2, genre2, subgenre2, duration2);
                    break;
                case 7:
                    String name = in.next();
                    Gender gender = Gender.valueOf(in.next());
                    int day = in.nextInt();
                    int month = in.nextInt();
                    int year = in.nextInt();
                    GregorianCalendar date = new GregorianCalendar(year, month, day);
                    u = new User(name, gender, date);
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 0 && i < 8) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
        System.out.print("2:    evaluateSongs(Song s1, Song s2)\n");
        System.out.print("3:    evaluateUser(User u)\n");
        System.out.print("4:    r = new SimpleRelation(String type, String attribute, String value): SONG/USER attribute value\n");
        System.out.print("5:    s1 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)" +
                ": title artist album YYYY id_genre id_subgenre duration(seconds)\n");
        System.out.print("6:    s2 = new Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)" +
                ": title artist album YYYY id_genre id_subgenre duration(seconds)\n");
        System.out.print("7:    u = new User(String name, Gender gender, Calendar birthdate, CountryCode country)" +
                ": name MALE/FEMALE/OTHER DD MM YYYY country_code\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
    }
}
