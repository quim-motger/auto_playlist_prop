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
        ListSet ls = new ListSet();
        List l = new List();

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
                case 3:
                    r = new SimpleRelation(ss, us, in.next(), in.next());
                    break;
                case 4:
                    try {
                        ss.addSong(new Song(in.next(), in.next(), in.next(), in.nextInt(), Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()), in.nextInt()));
                    } catch (PropException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        us.addUser(new User(in.next(), Gender.valueOf(in.next()), new GregorianCalendar(in.nextInt(),in.nextInt(),in.nextInt())));
                    } catch (PropException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        ls.add(new List(in.next()));
                    } catch (PropException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    Song song = null;
                    try {
                        song = ss.getSong(in.next(), in.next());
                    } catch (PropException e) {
                        e.printStackTrace();
                    }
                    ls.getList(in.next()).addSong(song);
                    break;
                case 8:
                    List l2 = ls.getList(in.next());
                    try {
                        us.getUserByName(in.next()).associate(l2);
                    } catch (PropException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    printInfoComplete();
            }
            //if (i > 0 && i < 9) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
        System.out.print("2:    evaluate()\n");
        System.out.print("3:    r = new SimpleRelation(String attribute, String value): attribute value\n");
        System.out.print("4:    ss.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration): title artist album YYYY id_genre id_subgenre duration\n");
        System.out.print("5:    us.addUser(String name, Gender gender, int year, int month, int day)\n");
        System.out.print("6:    ls.addList(Stirng name): title\n");
        System.out.print("7:    l.addSong(String title, String artist): title artist\n");
        System.out.print("8:   u.associate(List l): username\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
    }
}
