package prop.domain;

import prop.PropException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * AND driver
 * @author joaquim.motger
 */

public class ANDDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("**********************************************************");
        System.out.println("** Complex Relation AND");
        System.out.println("**********************************************************");
        System.out.print("\n");

        Scanner in = new Scanner(System.in);
        ComplexRelation r = null;
        SimpleRelation r1 = null;
        SimpleRelation r2 = null;
        Song s1 = new Song();
        Song s2 = new Song();
        User u = new User();
        ArrayList<Song> songs;
        SongSet ss = new SongSet();
        UserSet us = new UserSet();
        ListSet ls = new ListSet();
        List l = new List();

        int i = -1;
        printInfoComplete();
        while (i!=0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    r = new AND(r1,r2);
                    break;
                case 3:
                    try {
                        songs = r.evaluate();
                        for (Song song : songs) {
                            System.out.println(song.getTitle() + " " + song.getArtist());
                        }
                    } catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    String attribute = in.next();
                    String value = in.next();
                    r1 = new SimpleRelation(ss,us,attribute, value);
                    break;
                case 5:
                    String attribute2 = in.next();
                    String value2 = in.next();
                    r2 = new SimpleRelation(ss,us,attribute2, value2);
                    break;
                case 6:
                    try {
                        ss.addSong(new Song(in.next(), in.next(), in.next(), in.nextInt(), Genre.getGenreById(in.nextInt()),
                                Genre.getGenreById(in.nextInt()), in.nextInt()));
                    }
                    catch (PropException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    us.addUser(new User(in.next(), Gender.valueOf(in.next()), new GregorianCalendar(in.nextInt(),in.nextInt(),in.nextInt())));
                    break;
                case 8:
                    ls.add(new List(in.next()));
                    break;
                case 9:
                    Song song = ss.getSong(in.next(), in.next());
                    ls.getList(in.next()).addSong(song);
                    break;
                case 10:
                    List l2 = ls.getList(in.next());
                    us.getUserByName(in.next()).associate(l2);
                    break;
                default:
                    printInfoComplete();
            }
           // if (i > 0 && i < 11) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
        System.out.print("2:    r = new AND(r1, r2)\n");
        System.out.print("3:    evaluate()\n");
        System.out.print("4:    r1 = new Relation(String attribute, String value): attribute value\n");
        System.out.print("5:    r2 = new Relation(String attribute, String value): attribute value\n");
        System.out.print("6:    ss.addSong(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration): title artist album YYYY id_genre id_subgenre duration\n");
        System.out.print("7:    us.addUser(String name, Gender gender, int year, int month, int day)\n");
        System.out.print("8:    ls.addList(Stirng name): title\n");
        System.out.print("9:    l.addSong(String title, String artist): title artist\n");
        System.out.print("10:   u.associate(List l): username\n");
    }

    private static void printInfoBrief() {
        System.out.print("0:    terminate program\n");
        System.out.print("1:    printInfoComplete()\n");
    }
}
